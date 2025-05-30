package view.cart

import common.UserData
import view.order.OrderPage
import viewmodel.cart.CartManager

class CartPage {

    fun startCartPage(user: UserData) {
        while(true) {
            val cartItems = CartManager.getItems(user.id)
            println(CartMessage.CART_PAGE_TITLE)

            if (cartItems.isEmpty()) {
                println(CartMessage.CART_EMPTY)
                return
            }

            val grouped = cartItems.groupBy { it.addedTime.toLocalDate() }
            grouped.forEach { (date, items) ->
                println(String.format(CartMessage.CART_DATE, date.toString()))
                items.forEachIndexed { index, item ->
                    val total = item.menu.price * item.quantity
                    println(String.format(CartMessage.CART_ITEM, index + 1, item.menu.menuName, item.menu.price, item.quantity, total))
                }
                println()
            }

            println(CartMessage.CART_OPTIONS.trimIndent())

            when (readLine()) {
                "1" -> {
                    OrderPage().startOrderPage(user, cartItems)
                    println(CartMessage.ORDER_COMPLETE)
                    return
                }

                "2" -> {
                    println(CartMessage.QUANTITY_CHANGE_GUIDE)
                    val input = readLine()?.toIntOrNull()
                    if (input == null || input !in 1..cartItems.size) {
                        println(CartMessage.INVALID_INPUT)
                        continue
                    }

                    println(CartMessage.INPUT_NEW_QUANTITY)
                    val newQty = readLine()?.toIntOrNull()
                    if (newQty == null || newQty !in 1..9) {
                        println(CartMessage.INVALID_INPUT)
                        continue
                    }

                    val menu = cartItems[input - 1].menu
                    CartManager.updateQuantity(user.id, menu, newQty)
                    println(String.format(CartMessage.QUANTITY_CHANGE, menu.menuName, newQty))
                }

                "3" -> {
                    println(CartMessage.DELETE_ITEM_INPUT)
                    val input = readLine()?.toIntOrNull()
                    if (input == null || input !in 1..cartItems.size) {
                        println(CartMessage.INVALID_INPUT)
                        continue
                    }

                    val menu = cartItems[input - 1].menu
                    CartManager.removeItem(user.id, menu)
                    println(String.format(CartMessage.ITEM_REMOVE, menu.menuName))
                }

                "4" -> {
                    println(CartMessage.CLEAR_CART_CONFIRM)
                    if (readLine()?.uppercase() == "Y") {
                        CartManager.clear(user.id)
                        println(CartMessage.CART_CLEARED)
                        return
                    }
                }

                "0" -> {
                    println(CartMessage.GOING_BACK)
                    return
                }

                else -> println(CartMessage.INVALID_INPUT)
            }
        }
    }
}
