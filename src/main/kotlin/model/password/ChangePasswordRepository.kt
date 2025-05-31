package model.password

import model.UserData
import common.UserDBManager

class ChangePasswordRepository {
    fun updateUser(user: UserData) {
        UserDBManager.updateUser(user)
    }
}