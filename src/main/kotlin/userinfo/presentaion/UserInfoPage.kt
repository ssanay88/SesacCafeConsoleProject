package userinfo.presentaion

import common.UserData
import database.UserDBManager

class UserInfoPage {

    fun startViewUserInfo(user: UserData) {
        println("\n|||| 유저 정보 조회 페이지 ||||")

        val lastestData = UserDBManager.findUserDataById(user.id)

        if (lastestData == null) {
            println("존재하지 않는 유저 입니다.")
            return
        }

        println("이름: ${lastestData.name}")
        println("아이디: ${lastestData.id}")
        println("현재 잔액: ${lastestData.balance}원")
        println("보유 스탬프 개수: ${lastestData.stamp}개")
        println("주문 내역 수: ${lastestData.orderHistory.size}건")
    }
}