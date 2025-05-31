package view.orderhistory

import model.UserData

class OrderHistoryPage {

    fun startOrderHistoryPage(user: UserData) {
        println(OrderHistoryMessage.PAGE_SEPARATOR)
        println(OrderHistoryMessage.ORDER_HISTORY_PAGE_TITLE)

        if (user.orderHistory.isEmpty()) {
            println(OrderHistoryMessage.ORDER_HISTORY_EMPTY)
            return
        }

        user.orderHistory.forEachIndexed { index, order ->
            println(OrderHistoryMessage.ORDER_NUMBER_PREFIX.format(index + 1, order.orderId))
            println(OrderHistoryMessage.ORDER_DATE_PREFIX.format(order.orderDate))
            println(OrderHistoryMessage.ORDER_ITEMS_TITLE)
            order.orderItems.forEach {
                println("${it.menuName} x ${it.quantity}")
            }
            if (user.orderHistory.size > 1 && index != user.orderHistory.lastIndex) {
                println(OrderHistoryMessage.ORDER_SEPARATOR)
            }
        }
    }
}
