package mypage

import charge.presentation.ChargePage
import common.Input
import common.UserData
import orderhistory.presentation.OrderHistoryPage
import password.presentation.ChangePasswordPage
import userinfo.presentaion.UserInfoPage

class MyPage {

    private val chargePage = ChargePage()
    private val userInfoPage = UserInfoPage()
    private val orderHistoryPage = OrderHistoryPage()
    private val changePasswordPage = ChangePasswordPage()

    fun show(userData: UserData) {
        while (true) {
            println("\n|||| 마이페이지 ||||")
            println("[1] 금액 충전")
            println("[2] 유저 정보 조회")
            println("[3] 주문 내역 조회")
            println("[4] 비밀번호 변경")
            println("[0] 이전 화면으로 돌아가기")

            val input = Input.getStringInput("메뉴 선택 → ")

            when (input) {
                "1" -> chargePage.chargeAmount(userData)
                "2" -> userInfoPage.viewUserInfo(userData)
                "3" -> orderHistoryPage.viewOrderHistory(userData)
                "4" -> changePasswordPage.changePassword(userData)
                "0" -> {
                    println("이전 화면으로 돌아갑니다.")
                    return
                }
                else -> println("잘못된 입력입니다. 다시 선택해 주세요.")
            }
        }
    }
}