package mypage

import charge.presentation.ChargePage
import common.UserData
import common.getStringInput
import orderhistory.presentation.OrderHistoryPage
import password.presentation.ChangePasswordPage
import userinfo.presentaion.UserInfoPage

class MyPage {

    private val chargePage = ChargePage()
    private val userInfoPage = UserInfoPage()
    private val orderHistoryPage = OrderHistoryPage()
    private val changePasswordPage = ChangePasswordPage()

    fun startMyPage(userData: UserData) {
        while (true) {
            println(MyPageMessage.PAGE_TITLE)
            println(MyPageMessage.MENU_CHARGE)
            println(MyPageMessage.MENU_USER_INFO)
            println(MyPageMessage.MENU_ORDER_HISTORY)
            println(MyPageMessage.MENU_PASSWORD)
            println(MyPageMessage.MENU_BACK)

            val input = getStringInput(MyPageMessage.INPUT_PROMPT)

            when (input) {
                "1" -> chargePage.startChargePage(userData)
                "2" -> userInfoPage.startViewUserInfo(userData)
                "3" -> orderHistoryPage.startOrderHistoryPage(userData)
                "4" -> changePasswordPage.startChangePasswordPage(userData)
                "0" -> {
                    println(MyPageMessage.GOING_BACK)
                    return
                }
                else -> println(MyPageMessage.INVALID_INPUT)
            }
        }
    }
}