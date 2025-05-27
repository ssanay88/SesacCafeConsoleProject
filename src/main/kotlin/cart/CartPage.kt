package cart

import common.UserData
import order.OrderPage.order
import java.time.format.DateTimeFormatter

class CartPage {

    private val cartManager = CartManager()
    private val cartView =  CartView()

    fun startCartPage(user: UserData) {
        while(true) {
            val cartItems = cartManager.getItems(user.id)
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
                    cartManager.updateQuantity(user.id, menu, newQty)
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
                    cartManager.removeItem(user.id, menu)
                    cartView.printItemRemovedMessage(menu.menuName)
                }

                "4" -> {
                    cartView.printClearCartConfirmationUI()
                    if (readLine()?.uppercase() == "Y") {
                        cartManager.clear(user.id)
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
