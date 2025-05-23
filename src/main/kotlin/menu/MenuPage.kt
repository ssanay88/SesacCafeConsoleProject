package menu

import cart.Cart
import cart.CartItem
import cart.showCartPage
import common.UserData
import order.OrderPage
import kotlin.collections.get

fun showMenuPage(user: UserData) {
    println("\n=== [ 메뉴 페이지 ] ===")
    Menu.values().forEachIndexed { index, menu ->
        println("${index + 1}. ${menu.menuName} (${menu.price}원)")
    }

    print("\n메뉴 번호를 선택하세요: ")
    val choice = readLine()?.toIntOrNull()

    val selectedMenu = if (choice != null && choice in 1..Menu.values().size) {
        Menu.values()[choice - 1]
    } else {
        println("잘못된 선택입니다.")
        return
    }
    println()

    var quantity: Int? = null
    while (true) {
        print("[${selectedMenu.menuName}]의 주문수량을 입력하세요(1~9개): ")
        val input = readLine()?.toIntOrNull()

        if (input == null || input !in 1..9) {
            println("잘못된 수량입니다.")
        } else {
            quantity = input
            break
        }
    }

    println("선택한 메뉴: ${selectedMenu.menuName} x ${quantity}개")
    println()

    println("1. 장바구니에 담기")
    println("2. 주문하기")
    print("입력: ")
    when (readLine()) {
        "1" -> {
            Cart.addItem(CartItem(selectedMenu, quantity))
            println("\n[${selectedMenu.menuName}] ${quantity}개를 장바구니에 담았습니다.")
            println()

            while (true) {
                println("1. 장바구니로 이동하기")
                println("2. 메뉴 페이지로 돌아가기")
                print("입력: ")
                when (readLine()) {
                    "1" -> {
                        showCartPage(user)
                        break
                    }
                    "2" -> {
                        showMenuPage(user)
                        break
                    }
                    else -> println("잘못된 입력입니다. 다시 선택하세요.")
                }
            }
        }
        "2" -> {
//            println("[${selectedMenu.menuName}] ${quantity}개를 주문이 완료되었습니다.")
            val item = CartItem(selectedMenu, quantity)
            OrderPage.order(user, listOf(item))
        }
        else -> println("잘못된 선택입니다.")
    }
}
