package home

import auth.AuthManager
import feature.cart.CartManager
import feature.cart.CartPage
import core.inputZeroToMax
import core.database.UserDBManager
import feature.menu.MenuPage
import feature.mypage.MyPage
import feature.order.OrderPage
import kotlin.system.exitProcess

class HomePage() {

    private val cartManager = CartManager()
    private val menuPage = MenuPage()
    private val orderPage = OrderPage()
    private val myPage = MyPage()
    private val cartPage = CartPage()

    fun startHomePage() {
        val nowUser = AuthManager.currentUser!!
        val isCartEmpty = cartManager.getItems(nowUser.id).isEmpty()
        if (isCartEmpty) {
            printShowMainMenu()
        } else {
            printShowMainMenuWithCart()
        }

        when (inputZeroToMax(if (isCartEmpty) 3 else 4)) {
            0 -> {
                UserDBManager.saveChangesToFile()
                println("새싹 카페 프로그램 종료합니다. 감사합니다.")
                exitProcess(0)
            }
            1 -> menuPage.startMenuPage(nowUser)
            2 -> orderPage.startOrderPage(nowUser, cartManager.getItems(nowUser.id))
            3 -> myPage.startMyPage(nowUser)
            4 -> cartPage.startCartPage(nowUser)
            else -> println("잘못된 입력")
        }
    }

}