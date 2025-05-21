package userinfo.presentaion

import common.UserData

class UserInfoPage {

    fun viewUserInfo(user: UserData) {
        println("\n|||| 유저 정보 조회 페이지 ||||")
        println("이름: ${user.name}")
        println("아이디: ${user.id}")
        println("현재 잔액: ${user.balance}원")
        println("보유 스탬프 개수: ${user.stamp}개")
        println("주문 내역 수: ${user.orderHistory.size}건")
    }
}