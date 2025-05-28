package password.presentation

import common.UserData
import password.domain.ChangePasswordUseCase

class ChangePasswordViewModel(
    private val useCase: ChangePasswordUseCase = ChangePasswordUseCase()
) {
    fun changePassword(user: UserData, newPassword: String) {
        useCase.changePassword(user, newPassword)
    }
}