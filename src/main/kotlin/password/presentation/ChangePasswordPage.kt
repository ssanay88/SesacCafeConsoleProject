package password.presentation

import common.UserData
import password.ChangePasswordMessage

class ChangePasswordPage(
    private val viewModel: ChangePasswordViewModel = ChangePasswordViewModel()
) {
    fun startChangePasswordPage(user: UserData) {
        println(ChangePasswordMessage.PAGE_TITLE)

        if (!checkCurrentPassword(user)) return

        val newPassword = getNewPassword() ?: return

        if (!confirmPassword(newPassword)) return

        viewModel.changePassword(user, newPassword)
        println(ChangePasswordMessage.CHANGE_SUCCESS)
    }

    private fun checkCurrentPassword(user: UserData): Boolean {
        while (true) {
            println(ChangePasswordMessage.INPUT_CURRENT)
            println(ChangePasswordMessage.INPUT_ZERO_BACK)
            print(ChangePasswordMessage.CURRENT_PROMPT)
            val input = readln().trim()

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
            val input = readln().trim()

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
            val input = readln().trim()

            if (input != expected) {
                println(ChangePasswordMessage.CONFIRM_NOT_MATCH)
                continue
            }

            return true
        }
    }
}
