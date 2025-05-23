package order

import cart.Cart
import cart.CartItem
import common.UserData

object OrderPage{

    fun order(user: UserData, items: List<CartItem>) {
        if (items.isEmpty()) {
            println("장바구니가 비어 있습니다.")
            return
        }

        val totalAmount = items.sumOf { it.menu.price * it.quantity }

        println("\n=== [ 주문 페이지 ] ===")
        println("총 결제 금액: ${totalAmount}원")
        println("보유 포인트: ${user.stamp}점")
        println("계좌 잔액: ${user.balance}원")

        var userPoint = 0
        var amountToPay = totalAmount

        if (user.stamp > 0) {
            print("포인트를 사용하시겠습니까? (Y/N): ")
            when (readLine()?.uppercase()) {
                "Y" -> {
                    userPoint = if (user.stamp >= totalAmount) totalAmount else user.stamp
                    amountToPay -= userPoint
                    println("\n${userPoint}점의 포인트를 사용합니다.")
                }
                else -> println("포인트를 사용하지 않습니다.")
            }
        }

        if (user.balance < amountToPay) {
            // 충전페이지로 이동
            println("잔액 부족. 충전 페이지로 이동")
            return
        }

        user.stamp -= userPoint
        user.balance -= amountToPay
        Cart.clear()

        // CartItem -> OrderItem 변환
        val orderItems = items.map { cartItem ->
            OrderItem(
                menuId = cartItem.menu.name,      // menuId가 Menu 클래스에 있어야 함
                menuName = cartItem.menu.menuName,
                quantity = cartItem.quantity
            )
        }

        // Order 객체 생성 (orderDate는 기본값 사용)
        val order = Order(
            orderItems = orderItems
        )

        // 사용자 주문 내역에 추가
        user.orderHistory.add(order)

        println()
        println("결제가 완료되었습니다.")
        println()
        println("사용된 포인트: ${userPoint}점")
        println("결제된 금액: ${amountToPay}원")
        println("남은 포인트: ${user.stamp}점")
        println("남은 잔액: ${user.balance}원")
    }
}