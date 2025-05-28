package feature.mypage.password

import core.UserData

class ChangePasswordViewModel(
    private val useCase: ChangePasswordUseCase = ChangePasswordUseCase()
) {
    fun changePassword(user: UserData, newPassword: String) {
        useCase.changePassword(user, newPassword)
    }
}