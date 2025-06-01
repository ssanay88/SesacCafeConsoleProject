package view.menu

import common.OutputView
import model.cart.CartItem
import view.cart.CartPage
import model.UserData
import view.ConsoleInput
import view.order.OrderPage
import viewmodel.cart.CartManager

class MenuPage {

    private val menuView = MenuView()

    fun startMenuPage(user: UserData) {

        OutputView.printDivLine()
        menuView.printMenuPageUI()

        Menu.values().forEachIndexed { index, menu ->
            menuView.printMenuItem(index, menu.menuName, menu.price)
        }

        OutputView.printDivSubLine()
        menuView.printMenuSelectionUI()

        val choice = ConsoleInput.consoleReadLine().toInt()
        val selectedMenu = if (choice in 1..Menu.values().size) {
            Menu.values()[choice - 1]
        } else {
            menuView.printInvalidMenuSelectionUI()
            return
        }

        var quantity: Int = 0
        while (true) {
            OutputView.printDivSubLine()
            menuView.printQuantityInputUI(selectedMenu.menuName)
            val input = ConsoleInput.consoleReadLine().toInt()

            if (input !in 1..9) {
                menuView.printInvalidQuantityMessage()
            } else {
                quantity = input
                break
            }
        }
        with(menuView){
            OutputView.printDivSubLine()
            printSelectedMenuMessage(selectedMenu.menuName, quantity)
            OutputView.printDivLine()
            printOrderOptions()
        }

        when (ConsoleInput.consoleReadLine()) {
            "1" -> {
                CartManager.addItem(user.id, CartItem(selectedMenu, quantity))
                OutputView.printDivSubLine()
                menuView.printAddedToCartMessage(selectedMenu.menuName, quantity)

                while (true) {
                    OutputView.printDivLine()
                    menuView.printAfterAddOptions()
                    when (ConsoleInput.consoleReadLine()) {
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
                val orderPage = OrderPage()
                orderPage.startOrderPage(user, listOf(item))
            }
            else -> menuView.printInvalidInputMessage()
        }
    }
}
