package viewmodel.password

import model.UserData

class ChangePasswordManager(
    private val useCase: ChangePasswordUseCase = ChangePasswordUseCase()
) {
    fun changePassword(user: UserData, newPassword: String) {
        useCase.changePassword(user, newPassword)
    }
}