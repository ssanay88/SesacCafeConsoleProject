package password.domain

import common.UserData
import password.data.ChangePasswordRepository

class ChangePasswordUseCase(
    private val repository: ChangePasswordRepository = ChangePasswordRepository()
) {
    fun changePassword(user: UserData, newPassword: String) {
        user.password = newPassword
        repository.updateUser(user)
    }
}