package charge.presentation

import charge.ChargeMessage
import common.UserData
import common.getStringInput

class ChargePage(
    private val viewModel: ChargeViewModel = ChargeViewModel()
) {
    fun startChargePage(user: UserData) {
        println(ChargeMessage.CHARGE_PAGE_TITLE)

        while (true) {
            println(ChargeMessage.CHARGE_GUIDE)
            println(ChargeMessage.GO_BACK_GUIDE)

            val input = getStringInput(ChargeMessage.CHARGE_INPUT_PROMPT)

            if (input == "0") {
                println(ChargeMessage.GOING_BACK)
                break
            }

            val amount = input.toIntOrNull()
            if (amount == null) {
                println(ChargeMessage.INVALID_INPUT)
                continue
            }

            val validation = viewModel.isAmountValid(amount)
            if (validation != null) {
                println(validation)
                continue
            }

            viewModel.charge(user, amount)
            println(ChargeMessage.CHARGE_COMPLETE.format(amount))
            println(ChargeMessage.CURRENT_BALANCE.format(user.balance))
            return
        }
    }
}