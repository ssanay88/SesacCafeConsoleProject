package view.order

import model.UserData

class OrderView {
    fun printOrderPageUI(totalAmount: Int, user: UserData) {
        println("\n[ 주문 페이지 ]")
        println("총 결제 금액: ${totalAmount}원")
        println("보유 스탬프: ${user.stamp}개")
        println("계좌 잔액: ${user.balance}원")
    }

    fun printAskUsePointsUI(): Boolean {
        print("스탬프를 사용하시겠습니까? (Y/N): ")
        return readLine()?.uppercase() == "Y"
    }

    fun printUsingPointsMessage(pointsUsed: Int) {
        println("\n${pointsUsed}점의 스탬프를 사용합니다.")
    }

    fun printNotUsingPointsMessage() {
        println("스탬프를 사용하지 않습니다.")
    }

    fun printNotEnoughBalanceMessage() {
        println("잔액이 부족합니다. 충전 페이지로 이동합니다.")
    }

    fun printOrderCompleteMessage(amountToPay: Int, user: UserData) {
        println("\n결제가 완료되었습니다.")
        println("[ 구매 내역 ]")
        println("결제된 금액: ${amountToPay}원")
        println("남은 스탬프: ${user.stamp}점")
        println("남은 잔액: ${user.balance}원")
    }

    fun printEmptyCartMessage() {
        println("장바구니가 비어 있습니다.")
    }
}