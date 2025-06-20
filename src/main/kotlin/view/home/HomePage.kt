package view.home

import common.AuthManager
import common.CommonConstants
import common.InputView
import view.cart.CartPage
import view.menu.MenuPage
import view.mypage.MyPage
import view.order.OrderPage
import viewmodel.cart.CartManager

class HomePage() {

    private val menuPage = MenuPage()
    private val orderPage = OrderPage()
    private val myPage = MyPage()
    private val cartPage = CartPage()

    fun startHomePage() {
        val nowUser = AuthManager.currentUser!!
        while (true) {
            val isCartEmpty = CartManager.getItems(nowUser.id).isEmpty()
            if (isCartEmpty) {
                print(HomeMessage.SHOW_HOME_MENU.trimIndent())
            } else {
                print(HomeMessage.SHOW_HOME_MENU_WITH_CART.trimIndent())
            }

            when (InputView.inputZeroToMax(if (isCartEmpty) 3 else 4)) {
                1 -> menuPage.startMenuPage(nowUser)
                2 -> orderPage.startOrderPage(nowUser, CartManager.getItems(nowUser.id))
                3 -> myPage.startMyPage(nowUser)
                4 -> cartPage.startCartPage(nowUser)
                0 -> return
                else -> println(CommonConstants.ERROR_INVALID_INPUT.trimIndent())
            }
        }

    }

}