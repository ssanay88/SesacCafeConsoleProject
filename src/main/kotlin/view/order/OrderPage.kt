package view.order

import common.OutputView
import model.cart.CartItem
import model.UserData
import model.order.Order
import model.order.OrderItem
import view.charge.ChargePage
import viewmodel.cart.CartManager
import viewmodel.order.OrderManager

class OrderPage {

    private val orderView = OrderView()
    private val orderManager = OrderManager()
    private val chargePage = ChargePage()

    fun startOrderPage(user: UserData, items: List<CartItem>) {
        if (items.isEmpty()) {
            OutputView.printDivLine()
            orderView.printEmptyCartMessage()
            OutputView.printDivLine()
            return
        }

        while (true) {
            val totalAmount = items.sumOf { it.menu.price * it.quantity }

            var discount = 0
            var amountToPay = totalAmount

            // 사용자에게 스탬프 사용할지 물어보기
            if (user.stamp >= 10) {
                if (orderView.printAskUsePointsUI()) {
                    discount = 5000
                    amountToPay -= discount
                    orderView.printUsingPointsMessage(discount)
                } else {
                    orderView.printNotUsingPointsMessage()
                }
            }

            OutputView.printDivLine()
            orderView.printOrderPageUI(amountToPay, user)

            // 잔액 부족 시 충전 페이지 이동 후 다시 반복
            if (user.balance < amountToPay) {
                orderView.printNotEnoughBalanceMessage()
                chargePage.startChargePage(user)
                continue
            }

            // 결제 진행
            user.balance -= amountToPay
            if (discount == 5000) user.stamp -= 10
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

            OutputView.printDivSubLine()
            orderView.printOrderCompleteMessage(amountToPay, user)
            OutputView.printDivLine()
            return
        }
    }
}