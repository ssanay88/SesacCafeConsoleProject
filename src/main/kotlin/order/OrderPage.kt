package order

import cart.CartItem
import cart.CartManager
import common.UserData

class OrderPage{

    private val cartManager = CartManager()
    private val orderView = OrderView()
    private val orderManager = OrderManager()


    fun startOrderPage(user: UserData, items: List<CartItem>) {
        if (items.isEmpty()) {
            orderView.printEmptyCartMessage()
            return
        }

        val totalAmount = items.sumOf { it.menu.price * it.quantity }

        orderView.printOrderPageUI(totalAmount, user)

        var userPoint = 0
        var amountToPay = totalAmount

        if (user.stamp > 0) {
            if (orderView.printAskUsePointsUI()) {
                userPoint = if (user.stamp >= totalAmount) totalAmount else user.stamp
                amountToPay -= userPoint
                orderView.printUsingPointsMessage(userPoint)
            } else {
                orderView.printNotUsingPointsMessage()
            }
        }

        if (user.balance < amountToPay) {
            orderView.printNotEnoughBalanceMessage()
            return
        }

        user.stamp -= userPoint
        user.balance -= amountToPay
        cartManager.clear(user.id)

        val orderItems = items.map { cartItem ->
            OrderItem(
                menuId = cartItem.menu.menuName,
                menuName = cartItem.menu.menuName,
                quantity = cartItem.quantity
            )
        }

        val order = Order(orderItems = orderItems)

        orderManager.saveOrder(user, order)

        orderView.printOrderCompleteMessage(userPoint, amountToPay, user)
    }
}