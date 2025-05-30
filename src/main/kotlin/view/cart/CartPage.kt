package view.cart

import common.UserData
import view.order.OrderPage
import viewmodel.cart.CartManager

class CartPage {

    //private val cartManager = CartManager()
    private val cartView =  CartView()

    fun startCartPage(user: UserData) {
        while(true) {
            val cartItems = CartManager.getItems(user.id)
            cartView.printEnterCartUI()

            if (cartItems.isEmpty()) {
                cartView.printCartEmptyMessage()
                return
            }

            val grouped = cartItems.groupBy { it.addedTime.toLocalDate() }
            grouped.forEach { (date, items) ->
                cartView.printCartDate(date.toString())
                items.forEachIndexed { index, item ->
                    val total = item.menu.price * item.quantity
                    cartView.printCartItem(index + 1, item.menu.menuName, item.menu.price, item.quantity, total)
                }
                println()
            }

            cartView.printCartOptions()

            when (readLine()) {
                "1" -> {
                    OrderPage().startOrderPage(user, cartItems)
                    cartView.printOrderCompleteMessage()
                    return
                }

                "2" -> {
                    cartView.printQuantityChangeUI()
                    val input = readLine()?.toIntOrNull()
                    if (input == null || input !in 1..cartItems.size) {
                        cartView.printInvalidInputMessage()
                        continue
                    }

                    cartView.printQuantityInputUI()
                    val newQty = readLine()?.toIntOrNull()
                    if (newQty == null || newQty !in 1..9) {
                        cartView.printInvalidInputMessage()
                        continue
                    }

                    val menu = cartItems[input - 1].menu
                    CartManager.updateQuantity(user.id, menu, newQty)
                    cartView.printQuantityChangedMessage(menu.menuName, newQty)
                }

                "3" -> {
                    cartView.printDeleteItemUI()
                    val input = readLine()?.toIntOrNull()
                    if (input == null || input !in 1..cartItems.size) {
                        cartView.printInvalidInputMessage()
                        continue
                    }

                    val menu = cartItems[input - 1].menu
                    CartManager.removeItem(user.id, menu)
                    cartView.printItemRemovedMessage(menu.menuName)
                }

                "4" -> {
                    cartView.printClearCartConfirmationUI()
                    if (readLine()?.uppercase() == "Y") {
                        CartManager.clear(user.id)
                        cartView.printCartClearedMessage()
                        return
                    }
                }

                "-1" -> return

                else -> cartView.printInvalidInputMessage()
            }
        }
    }
}
