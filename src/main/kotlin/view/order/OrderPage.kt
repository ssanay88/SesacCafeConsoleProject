package view.order

import model.cart.CartItem
import common.UserData
import model.order.Order
import model.order.OrderItem
import viewmodel.cart.CartManager
import viewmodel.order.OrderManager

class OrderPage{

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
        CartManager.clear(user.id)

        val orderItems = items.map { cartItem ->
            OrderItem(
                menuId = cartItem.menu.menuName,
                menuName = cartItem.menu.menuName,
                quantity = cartItem.quantity
            )
        }

        val order = Order(orderItems = orderItems)

        orderManager.apply {
            saveOrder(user, order)
            addStamp(user)
        }

        orderView.printOrderCompleteMessage(userPoint, amountToPay, user)
    }
}