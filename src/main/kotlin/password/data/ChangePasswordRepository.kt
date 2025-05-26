package password.data

import common.UserData
import database.UserDBManager

class ChangePasswordRepository {
    fun updateUser(user: UserData) {
        UserDBManager.updateUser(user)
    }
}