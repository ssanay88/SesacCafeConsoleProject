package charge.domain

import common.UserData
import database.UserDBManager

object ChargeService {
    fun charge(userData: UserData, amount: Int) {
        userData.balance += amount
        UserDBManager.updateUser(userData)
    }
}