package viewmodel.charge

import common.UserData
import model.charge.ChargeRepository
import model.charge.ChargeValidationResult
import view.charge.ChargeMessage

class ChargeUseCase(
    private val repository: ChargeRepository = ChargeRepository()
) {
    fun charge(user: UserData, amount: Int) {
        user.balance += amount
        repository.updateUser(user)
    }

    fun isChargeAmountValid(amount: Int): ChargeValidationResult {
        return when {
            amount < 5000 -> ChargeValidationResult.Invalid(ChargeMessage.AMOUNT_TOO_LOW)
            amount > 50000 -> ChargeValidationResult.Invalid(ChargeMessage.AMOUNT_TOO_HIGH)
            else -> ChargeValidationResult.Valid
        }
    }

}
