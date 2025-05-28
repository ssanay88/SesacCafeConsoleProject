package feature.charge

import core.UserData
import core.UserDBManager

class ChargeRepository {
    fun updateUser(user: UserData) {
        UserDBManager.updateUser(user)
    }
}