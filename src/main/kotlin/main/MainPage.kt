package main

import cart.Cart
import common.inputOneToMax

class MainPage() {

    fun startMainPage() {
        val isCartEmpty = Cart.getItems().isEmpty()
        if (isCartEmpty) {
            printShowMainMenu()
        } else {
            printShowMainMenuWithCart()
        }

        when (inputOneToMax(if (isCartEmpty) 3 else 4)) {
            1 -> println("메뉴 페이지 시작")
            2 -> println("주문 페이지 시작")
            3 -> println("마이 페이지 시작")
            4 -> println("장바구니 페이지 시작")
            else -> println("잘못된 입력")
        }
    }

}