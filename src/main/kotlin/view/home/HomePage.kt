package view.home

import common.AuthManager
import common.Input
import view.cart.CartPage
import view.menu.MenuPage
import view.mypage.MyPage
import view.order.OrderPage
import viewmodel.cart.CartManager

class HomePage() {

    private val cartManager = CartManager()
    private val menuPage = MenuPage()
    private val orderPage = OrderPage()
    private val myPage = MyPage()
    private val cartPage = CartPage()

    fun startHomePage() {
        val nowUser = AuthManager.currentUser!!
        while (true) {
            val isCartEmpty = cartManager.getItems(nowUser.id).isEmpty()
            if (isCartEmpty) {
                printShowHomeMenu()
            } else {
                printShowHomeMenuWithCart()
            }

            when (Input.inputZeroToMax(if (isCartEmpty) 3 else 4)) {
                1 -> menuPage.startMenuPage(nowUser)
                2 -> orderPage.startOrderPage(nowUser, cartManager.getItems(nowUser.id))
                3 -> myPage.startMyPage(nowUser)
                4 -> cartPage.startCartPage(nowUser)
                0 -> return
                else -> println("잘못된 입력")
            }
        }

    }

}