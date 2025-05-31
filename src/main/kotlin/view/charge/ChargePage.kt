package view.charge

import common.CommonConstants
import common.InputView
import common.OutputView.printDivLine
import model.UserData
import model.charge.ChargeValidationResult
import viewmodel.charge.ChargeManager

class ChargePage(
    private val chargeManager: ChargeManager = ChargeManager()
) {
    fun startChargePage(user: UserData) {
        printDivLine()
        println(ChargeMessage.CHARGE_PAGE_TITLE)

        while (true) {
            println(ChargeMessage.CHARGE_GUIDE)
            println(ChargeMessage.GO_BACK_GUIDE)

            val input = InputView.getStringInput(ChargeMessage.CHARGE_INPUT_PROMPT)

            if (input == CommonConstants.USER_INPUT_ZERO) {
                println(ChargeMessage.GOING_BACK)
                break
            }

            val amount = input.toIntOrNull()
            if (amount == null) {
                println(ChargeMessage.INVALID_INPUT)
                continue
            }

            when (val validation = chargeManager.isAmountValid(amount)) {
                is ChargeValidationResult.Invalid -> {
                    println(validation.message)
                    continue
                }
                ChargeValidationResult.Valid -> {
                    println(ChargeMessage.CHARGING)
                    chargeManager.charge(user, amount)
                    println(ChargeMessage.CHARGE_COMPLETE.format(amount))
                    println(ChargeMessage.CURRENT_BALANCE.format(user.balance))
                    return
                }
            }
        }
    }
}