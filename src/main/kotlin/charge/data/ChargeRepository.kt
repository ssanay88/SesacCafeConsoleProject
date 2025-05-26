package charge.data

import common.UserData
import database.UserDBManager

class ChargeRepository {
    fun updateUser(user: UserData) {
        UserDBManager.updateUser(user)
    }
}