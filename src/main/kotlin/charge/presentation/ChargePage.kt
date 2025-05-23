package charge.presentation

import charge.domain.ChargeService
import common.Input
import common.UserData

class ChargePage {
    fun chargeAmount(user: UserData) {
        println("\n|||| 금액 충전 페이지 ||||")

        while (true) {
            println("충전하실 금액을 입력해주세요. (최소금액: 5000원, 최대금액: 50000원)")
            println("[0]을 입력하면 이전 메뉴로 돌아갑니다.")

            val input = Input.getStringInput("충전 금액 -> ")

            if (input == "0") {
                println("이전 메뉴로 돌아갑니다.")
                break
            }

            val amount = input.toIntOrNull()

            when {
                amount == null -> println("숫자만 입력해주세요.")

                amount < 5000 -> println("최소 충전 금액은 5000원입니다.")

                amount > 50000 -> println("최대 충전 금액은 50000원입니다.")

                else -> {
                    ChargeService.charge(
                        userData = user,
                        amount = amount
                    )
                    println("${amount}원 충전 완료되었습니다.")
                    println("현재 잔액: ${user.balance}원")
                    break
                }
            }
        }
    }
}