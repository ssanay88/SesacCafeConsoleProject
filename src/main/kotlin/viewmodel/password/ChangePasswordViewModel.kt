package viewmodel.password

import common.UserData

class ChangePasswordViewModel(
    private val useCase: ChangePasswordUseCase = ChangePasswordUseCase()
) {
    fun changePassword(user: UserData, newPassword: String) {
        useCase.changePassword(user, newPassword)
    }
}