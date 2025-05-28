package feature.mypage.password

import core.UserData

class ChangePasswordUseCase(
    private val repository: ChangePasswordRepository = ChangePasswordRepository()
) {
    fun changePassword(user: UserData, newPassword: String) {
        user.password = newPassword
        repository.updateUser(user)
    }
}