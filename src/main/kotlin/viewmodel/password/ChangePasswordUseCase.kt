package viewmodel.password

import model.UserData
import model.password.ChangePasswordRepository

class ChangePasswordUseCase(
    private val repository: ChangePasswordRepository = ChangePasswordRepository()
) {
    fun changePassword(user: UserData, newPassword: String) {
        user.password = newPassword
        repository.updateUser(user)
    }
}