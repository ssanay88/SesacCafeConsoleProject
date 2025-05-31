package model.charge

import model.UserData
import common.UserDBManager

class ChargeRepository {
    fun updateUser(user: UserData) {
        UserDBManager.updateUser(user)
    }
}