package view.mypage

import common.CommonConstants
import common.InputView
import common.OutputView.printDivLine
import view.charge.ChargePage
import common.UserData
import view.orderhistory.OrderHistoryPage
import view.password.ChangePasswordPage
import view.userinfo.UserInfoPage

class MyPage {

    private val chargePage = ChargePage()
    private val userInfoPage = UserInfoPage()
    private val orderHistoryPage = OrderHistoryPage()
    private val changePasswordPage = ChangePasswordPage()

    fun startMyPage(userData: UserData) {
        while (true) {
            printDivLine()
            println(MyPageMessage.PAGE_TITLE)
            println(MyPageMessage.MENU_CHARGE)
            println(MyPageMessage.MENU_USER_INFO)
            println(MyPageMessage.MENU_ORDER_HISTORY)
            println(MyPageMessage.MENU_PASSWORD)
            println(MyPageMessage.MENU_BACK)

            val input = InputView.getStringInput(MyPageMessage.INPUT_PROMPT)

            when (input) {
                CommonConstants.USER_INPUT_ONE  -> chargePage.startChargePage(userData)
                CommonConstants.USER_INPUT_TWO-> userInfoPage.startViewUserInfo(userData)
                CommonConstants.USER_INPUT_THREE -> orderHistoryPage.startOrderHistoryPage(userData)
                CommonConstants.USER_INPUT_FOUR -> changePasswordPage.startChangePasswordPage(userData)
                CommonConstants.USER_INPUT_ZERO -> {
                    println(MyPageMessage.GOING_BACK)
                    return
                }
                else -> println(MyPageMessage.INVALID_INPUT)
            }
        }
    }
}