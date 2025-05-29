package view.password

import common.Input
import common.UserData
import viewmodel.password.ChangePasswordManager

class ChangePasswordPage(
    private val changePasswordManager: ChangePasswordManager = ChangePasswordManager()
) {
    fun startChangePasswordPage(user: UserData) {
        println(ChangePasswordMessage.PAGE_TITLE)

        if (!checkCurrentPassword(user)) return

        val newPassword = getNewPassword() ?: return

        if (!confirmPassword(newPassword)) return

        changePasswordManager.changePassword(user, newPassword)
        println(ChangePasswordMessage.CHANGE_SUCCESS)
    }

    private fun checkCurrentPassword(user: UserData): Boolean {
        while (true) {
            println(ChangePasswordMessage.INPUT_CURRENT)
            println(ChangePasswordMessage.INPUT_ZERO_BACK)
            print(ChangePasswordMessage.CURRENT_PROMPT)
            val input = Input.getStringInput(ChangePasswordMessage.INPUT_CURRENT)

            if (input == "0") {
                println(ChangePasswordMessage.GOING_BACK)
                return false
            }

            if (input != user.password) {
                println(ChangePasswordMessage.CURRENT_NOT_MATCH)
                continue
            }

            return true
        }
    }

    private fun getNewPassword(): String? {
        while (true) {
            println(ChangePasswordMessage.INPUT_NEW)
            println(ChangePasswordMessage.INPUT_ZERO_BACK)
            print(ChangePasswordMessage.NEW_PROMPT)
            val input = Input.getStringInput(ChangePasswordMessage.INPUT_NEW)

            if (input == "0") {
                println(ChangePasswordMessage.GOING_BACK)
                return null
            }

            if (input.length < 4) {
                println(ChangePasswordMessage.NEW_TOO_SHORT)
                continue
            }

            return input
        }
    }

    private fun confirmPassword(expected: String): Boolean {
        while (true) {
            println(ChangePasswordMessage.INPUT_CONFIRM)
            print(ChangePasswordMessage.CONFIRM_PROMPT)
            val input = Input.getStringInput(ChangePasswordMessage.INPUT_CONFIRM)

            if (input != expected) {
                println(ChangePasswordMessage.CONFIRM_NOT_MATCH)
                continue
            }

            return true
        }
    }
}
