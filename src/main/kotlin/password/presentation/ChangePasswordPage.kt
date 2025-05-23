package password.presentation

import common.UserData


class ChangePasswordPage {

    fun changePassword(user: UserData) {
        println("\n|||| 비밀번호 수정 페이지 ||||")

        val currentPassword = inputCurrentPassword(user) ?: return
        val newPassword = inputNewPassword() ?: return
        val confirmPassword = inputConfirmPassword(newPassword) ?: return

        user.password = newPassword
        println("✅ 비밀번호가 성공적으로 변경되었습니다.")
    }

    private fun inputCurrentPassword(user: UserData): String? {
        while (true) {
            println("현재 비밀번호를 입력해 주세요.")
            println("[0] 입력 시 이전 화면으로 돌아갑니다.")
            print("현재 비밀번호 -> ")
            val input = readln().trim()

            if (input == "0") {
                println("이전 화면으로 돌아갑니다.")
                return null
            }

            if (input != user.password) {
                println("비밀번호가 일치하지 않습니다. 다시 입력해 주세요.\n")
                continue
            }

            return input
        }
    }

    private fun inputNewPassword(): String? {
        while (true) {
            println("새 비밀번호를 입력해 주세요. (4자리 이상)")
            println("[0] 입력 시 이전 화면으로 돌아갑니다.")
            print("새 비밀번호 -> ")
            val input = readln().trim()

            if (input == "0") {
                println("이전 화면으로 돌아갑니다.")
                return null
            }

            if (input.length < 4) {
                println("4자리 이상 입력해 주세요.\n")
                continue
            }

            return input
        }
    }

    private fun inputConfirmPassword(expected: String): String? {
        while (true) {
            println("새 비밀번호를 다시 입력해 주세요.")
            print("새 비밀번호 확인 -> ")
            val input = readln().trim()

            if (input != expected) {
                println("비밀번호가 일치하지 않습니다. 다시 입력해 주세요.\n")
                continue
            }

            return input
        }
    }
}
