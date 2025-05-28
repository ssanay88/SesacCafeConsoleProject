package feature.mypage.password

import core.UserData
import core.database.UserDBManager

class ChangePasswordRepository {
    fun updateUser(user: UserData) {
        UserDBManager.updateUser(user)
    }
}