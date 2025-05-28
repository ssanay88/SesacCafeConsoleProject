package model.password

import common.UserData
import common.UserDBManager

class ChangePasswordRepository {
    fun updateUser(user: UserData) {
        UserDBManager.updateUser(user)
    }
}