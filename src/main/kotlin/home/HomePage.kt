package home

import auth.AuthManager
import feature.cart.CartManager
import feature.cart.CartPage
import core.inputZeroToMax
import feature.menu.MenuPage
import feature.mypage.MyPage
import feature.order.OrderPage

class HomePage() {

    //private val cartManager = CartManager()
    private val menuPage = MenuPage()
    private val orderPage = OrderPage()
    private val myPage = MyPage()
    private val cartPage = CartPage()

    fun startHomePage() {
        val nowUser = AuthManager.currentUser!!
        while (true) {
            val isCartEmpty = CartManager.getItems(nowUser.id).isEmpty()
            if (isCartEmpty) {
                printShowHomeMenu()
            } else {
                printShowHomeMenuWithCart()
            }

            when (inputZeroToMax(if (isCartEmpty) 3 else 4)) {
                1 -> menuPage.startMenuPage(nowUser)
                2 -> orderPage.startOrderPage(nowUser, CartManager.getItems(nowUser.id))
                3 -> myPage.startMyPage(nowUser)
                4 -> cartPage.startCartPage(nowUser)
                0 -> return
                else -> println("잘못된 입력")
            }
        }

    }

}