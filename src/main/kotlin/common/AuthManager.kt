package common

import model.order.Order

/**
 * Auth manager
 * 로그인한 유저에 대한 정보를 처리할 클래스
 */
object AuthManager {
    private var _currentUser: UserData? = null

    val currentUser: UserData?
        get() = _currentUser

    fun login(user: UserData) {
        _currentUser = user
    }

    fun logout() {
        _currentUser = null
    }

    // 비밀번호 변경
    fun setUserPw(newPw: String) {
        val user = _currentUser
        user?.let {
            val updatedUser = user.copy(password = newPw)
            UserDBManager.updateUser(updatedUser)
            _currentUser = updatedUser
            println("비밀번호가 변경되었습니다.")
        }
    }

    // 잔액 충전
    fun chargeMoney(money: Int) {
        val user = _currentUser
        user?.let {
            val updatedUser = user.copy(balance = user.balance + money)
            UserDBManager.updateUser(updatedUser)
            _currentUser = updatedUser
            println("금액이 충전되었습니다.")
        }
    }

    // 결제 처리
    fun makePayment(payMoney: Int) {
        val user = _currentUser
        user?.let {
            val updatedUser = user.copy(balance = user.balance - payMoney)
            UserDBManager.updateUser(updatedUser)
            _currentUser = updatedUser
            println("${payMoney}원 결제되었습니다.")
        }
    }

    // 주문 내역 추가
    fun addOrder(order: Order) {
        val user = _currentUser
        user?.let {
            val updatedOrderList = user.orderHistory.apply { add(order) }
            val updatedUser = user.copy(orderHistory = updatedOrderList)
            UserDBManager.updateUser(updatedUser)
            _currentUser = updatedUser
            println("새로운 주문 내역이 추가되었습니다.")
        }
    }

    // 스탬프 적립
    fun addStamp(stampCnt: Int) {
        val user = _currentUser
        user?.let {
            val updatedUser = user.copy(stamp = user.stamp + stampCnt)
            UserDBManager.updateUser(updatedUser)
            _currentUser = updatedUser
            println("새로운 스탬프 ${stampCnt}개가 적립되었습니다. [현재 스탬프 : ${updatedUser.stamp}]")
        }
    }

    fun useStamp(stampCnt: Int) {
        val user = _currentUser
        user?.let {
            val updatedUser = user.copy(stamp = user.stamp - stampCnt)
            UserDBManager.updateUser(updatedUser)
            _currentUser = updatedUser
            println("새로운 스탬프 ${stampCnt}개가 적립되었습니다. [현재 스탬프 : ${updatedUser.stamp}]")
        }
    }

    fun isStampOver10(): Boolean {
        return _currentUser?.stamp ?: 0 >= 10
    }

    fun printLoginUserInfo() {
        println("""
            이름 : ${_currentUser?.name}
            ID : ${_currentUser?.id}
            PW : ${_currentUser?.password}
            Balance : ${_currentUser?.balance}
            OrderHistory : ${_currentUser?.orderHistory}
            Stamp : ${_currentUser?.stamp}
        """.trimIndent())
    }

}