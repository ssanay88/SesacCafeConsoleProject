package view.password

import common.CommonConstants
import common.InputView
import common.OutputView.printDivLine
import model.UserData
import viewmodel.password.ChangePasswordManager

class ChangePasswordPage(
    private val changePasswordManager: ChangePasswordManager = ChangePasswordManager()
) {
    fun startChangePasswordPage(user: UserData) {
        printDivLine()
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

            val input = InputView.getStringInput(ChangePasswordMessage.CURRENT_PROMPT)

            if (input == CommonConstants.USER_INPUT_ZERO) {
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

            val input = InputView.getStringInput(ChangePasswordMessage.NEW_PROMPT)

            if (input == CommonConstants.USER_INPUT_ZERO) {
                println(ChangePasswordMessage.GOING_BACK)
                return null
            }

            if (input.length < CommonConstants.MIN_PW_LENGTH) {
                println(ChangePasswordMessage.NEW_TOO_SHORT)
                continue
            }

            return input
        }
    }

    private fun confirmPassword(expected: String): Boolean {
        while (true) {
            println(ChangePasswordMessage.INPUT_CONFIRM)

            val input = InputView.getStringInput(ChangePasswordMessage.CONFIRM_PROMPT)

            if (input != expected) {
                println(ChangePasswordMessage.CONFIRM_NOT_MATCH)
                continue
            }

            return true
        }
    }
}
