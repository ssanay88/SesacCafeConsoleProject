package feature.order

import core.UserData

class OrderView {

    fun printOrderPageUI(totalAmount: Int, user: UserData) {
        println("\n[ 주문 페이지 ]")
        println("총 결제 금액: ${totalAmount}원")
        println("보유 포인트: ${user.stamp}점")
        println("계좌 잔액: ${user.balance}원")
    }

    fun printAskUsePointsUI(): Boolean {
        print("포인트를 사용하시겠습니까? (Y/N): ")
        return readLine()?.uppercase() == "Y"
    }

    fun printUsingPointsMessage(pointsUsed: Int) {
        println("\n${pointsUsed}점의 포인트를 사용합니다.")
    }

    fun printNotUsingPointsMessage() {
        println("포인트를 사용하지 않습니다.")
    }

    fun printNotEnoughBalanceMessage() {
        println("잔액이 부족합니다. 충전 페이지로 이동합니다.")
    }

    fun printOrderCompleteMessage(userPoint: Int, amountToPay: Int, user: UserData) {
        println()
        println("결제가 완료되었습니다.")
        println("사용된 포인트: ${userPoint}점")
        println("결제된 금액: ${amountToPay}원")
        println("남은 포인트: ${user.stamp}점")
        println("남은 잔액: ${user.balance}원")
    }

    fun printEmptyCartMessage() {
        println("장바구니가 비어 있습니다.")
    }
}