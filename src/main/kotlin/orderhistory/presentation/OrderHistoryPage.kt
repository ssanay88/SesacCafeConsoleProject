package orderhistory.presentation

import common.UserData

class OrderHistoryPage {

    fun viewOrderHistory(user: UserData) {
        println("\n|||| 주문 내역 조회 페이지 ||||")

        if (user.orderHistory.isEmpty()) {
            println("주문 내역이 없습니다.")
            return
        }

        user.orderHistory.forEachIndexed { index, order ->
            println("[${index + 1}] 주문번호: ${order.orderId}")
            println("주문 날짜: ${order.orderDate}")
            println("주문 항목:")
            order.orderItems.forEach { (menu, qty) ->
                println("   - $menu x $qty")
            }
            println("--------------------------------")
        }
    }
}
