package feature.mypage.userinfo

import core.UserData

class UserInfoPage {
    fun startViewUserInfo(user: UserData) {
        println(UserInfoMessage.PAGE_TITLE)
        println(UserInfoMessage.NAME.format(user.name))
        println(UserInfoMessage.ID.format(user.id))
        println(UserInfoMessage.BALANCE.format(user.balance))
        println(UserInfoMessage.STAMP.format(user.stamp))
        println(UserInfoMessage.ORDER_COUNT.format(user.orderHistory.size))
    }
}