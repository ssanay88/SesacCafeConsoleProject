package model

import model.order.Order
import java.io.Serializable

data class UserData(
    val name: String,
    val id: String,
    var password: String,
    var balance: Int = 0,
    val orderHistory: MutableList<Order> = mutableListOf(),
    var stamp: Int = 0
): Serializable {
    override fun toString(): String =
        "회원명 : ${name} / ID : ${id} / 현재 잔액 : ${balance}원 / 보유 스탬프 : ${stamp}개"
}

