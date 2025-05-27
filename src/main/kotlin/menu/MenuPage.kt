package menu

import cart.CartItem
import cart.CartManager
import cart.CartPage
import common.UserData
import order.OrderPage

class MenuPage {

    private val cartManager = CartManager()
    private val menuView = MenuView()

    fun startMenuPage(user: UserData) {

        menuView.printMenuPageUI()

        Menu.values().forEachIndexed { index, menu ->
            menuView.printMenuItem(index, menu.menuName, menu.price)
        }

        menuView.printMenuSelectionUI()

        val choice = readLine()?.toIntOrNull()
        val selectedMenu = if (choice != null && choice in 1..Menu.values().size) {
            Menu.values()[choice - 1]
        } else {
            menuView.printInvalidMenuSelectionUI()
            return
        }

        var quantity: Int? = null
        while (true) {
            menuView.printQuantityInputUI(selectedMenu.menuName)
            val input = readLine()?.toIntOrNull()

            if (input == null || input !in 1..9) {
                menuView.printInvalidQuantityMessage()
            } else {
                quantity = input
                break
            }
        }

        menuView.printSelectedMenuMessage(selectedMenu.menuName, quantity)
        menuView.printOrderOptions()

        when (readLine()) {
            "1" -> {
                cartManager.addItem(user.id, CartItem(selectedMenu, quantity))
                menuView.printAddedToCartMessage(selectedMenu.menuName, quantity)

                while (true) {
                    menuView.printAfterAddOptions()
                    when (readLine()) {
                        "1" -> {
                            CartPage().startCartPage(user)
                            break
                        }
                        "2" -> {
                            startMenuPage(user)
                            break
                        }
                        else -> menuView.printInvalidInputMessage()
                    }
                }
            }
            "2" -> {
                val item = CartItem(selectedMenu, quantity)
                OrderPage.order(user, listOf(item))
            }
            else -> menuView.printInvalidInputMessage()
        }
    }
}
