package view.charge

import common.Input
import common.UserData
import viewmodel.charge.ChargeManager


class ChargePage(
    private val chargeManager: ChargeManager = ChargeManager()
) {
    fun startChargePage(user: UserData) {
        println(ChargeMessage.PAGE_SEPARATOR)
        println(ChargeMessage.CHARGE_PAGE_TITLE)

        while (true) {
            println(ChargeMessage.CHARGE_GUIDE)
            println(ChargeMessage.GO_BACK_GUIDE)

            val input = Input.getStringInput(ChargeMessage.CHARGE_INPUT_PROMPT)

            if (input == ChargeMessage.ZERO) {
                println(ChargeMessage.GOING_BACK)
                break
            }

            val amount = input.toIntOrNull()
            if (amount == null) {
                println(ChargeMessage.INVALID_INPUT)
                continue
            }

            val validation = chargeManager.isAmountValid(amount)
            if (validation != null) {
                println(validation)
                continue
            }

            chargeManager.charge(user, amount)
            println(ChargeMessage.CHARGE_COMPLETE.format(amount))
            println(ChargeMessage.CURRENT_BALANCE.format(user.balance))
            return
        }
    }
}