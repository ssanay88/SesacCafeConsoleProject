package orderhistory.presentation

import common.UserData
import database.UserDBManager

class OrderHistoryPage {

    fun startOrderHistoryPage(user: UserData) {
        println("\n|||| 주문 내역 조회 페이지 ||||")

        val lastestData = UserDBManager.findUserDataById(user.id)

        if (lastestData == null) {
            println("존재하지 않는 유저입니다.")
            return
        }

        if (lastestData.orderHistory.isEmpty()) {
            println("주문 내역이 없습니다.")
            return
        }

        lastestData.orderHistory.forEachIndexed { index, order ->
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
