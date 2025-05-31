package viewmodel.charge

import model.UserData
import model.charge.ChargeValidationResult

class ChargeManager(
    private val useCase: ChargeUseCase = ChargeUseCase()
) {
    fun charge(user: UserData, amount: Int) {
        useCase.charge(user, amount)
    }

    fun isAmountValid(amount: Int): ChargeValidationResult {
        return useCase.isChargeAmountValid(amount)
    }
}