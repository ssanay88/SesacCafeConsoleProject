package charge.domain

import common.UserData

object ChargeService {
    fun charge(userData: UserData, amount: Int) {
        userData.balance += amount
    }
}