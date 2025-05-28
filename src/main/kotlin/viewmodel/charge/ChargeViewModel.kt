package viewmodel.charge

import common.UserData

class ChargeViewModel(
    private val useCase: ChargeUseCase = ChargeUseCase()
) {
    fun charge(user: UserData, amount: Int) {
        useCase.charge(user, amount)
    }

    fun isAmountValid(amount: Int): String? {
        return useCase.isChargeAmountValid(amount)
    }
}