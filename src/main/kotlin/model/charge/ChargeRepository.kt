package model.charge

import common.UserData
import common.UserDBManager

class ChargeRepository {
    fun updateUser(user: UserData) {
        UserDBManager.updateUser(user)
    }
}