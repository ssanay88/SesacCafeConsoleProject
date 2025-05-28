package feature.order

import core.UserData

class OrderManager {

    fun saveOrder(user: UserData, order: Order) {
        user.orderHistory.add(order)
    }

    fun addStamp(user: UserData) {
        user.stamp += 1
    }

    // 특정 사용자 주문 내역 조회
    fun getOrdersByUser(user: UserData): List<Order> {
        return user.orderHistory.toList()
    }
}