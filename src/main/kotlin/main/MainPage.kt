package main

import cart.Cart
import common.inputZeroToMax
import database.UserDBManager
import kotlin.system.exitProcess

class MainPage() {

    fun startMainPage() {
        val isCartEmpty = Cart.getItems().isEmpty()
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
            1 -> println("메뉴 페이지 시작")
            2 -> println("주문 페이지 시작")
            3 -> println("마이 페이지 시작")
            4 -> println("장바구니 페이지 시작")
            else -> println("잘못된 입력")
        }
    }

}