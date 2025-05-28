package charge.domain

import charge.data.ChargeRepository
import charge.ChargeMessage
import common.UserData

class ChargeUseCase(
    private val repository: ChargeRepository = ChargeRepository()
) {
    fun charge(user: UserData, amount: Int) {
        user.balance += amount
        repository.updateUser(user)
    }

    fun isChargeAmountValid(amount: Int): String? {
        return when {
            amount < 5000 -> ChargeMessage.AMOUNT_TOO_LOW
            amount > 50000 -> ChargeMessage.AMOUNT_TOO_HIGH
            else -> null
        }
    }

}
