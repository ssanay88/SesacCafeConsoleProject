package feature.charge

import core.UserData
import core.database.UserDBManager

class ChargeRepository {
    fun updateUser(user: UserData) {
        UserDBManager.updateUser(user)
    }
}