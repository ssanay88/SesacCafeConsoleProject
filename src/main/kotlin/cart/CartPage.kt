package cart

import common.UserData
import order.OrderPage.order
import java.time.format.DateTimeFormatter

fun showCartPage(user: UserData) {
    while(true) {
        println("\n=== [ 장바구니 ] ===")
        val cartItems = Cart.getItems()

        if (cartItems.isEmpty()) {
            println("장바구니가 비어있습니다.")
            return
        }

        val grouped = cartItems.groupBy { it.addedTime.toLocalDate() }

        grouped.forEach { (date, items) ->
            println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))

            items.forEachIndexed { index, item ->
                val total = item.menu.price * item.quantity
                println("${index + 1}. ${item.menu.menuName}    ${item.menu.price}      ${item.quantity}      ${total}원")
            }
            println()
        }

        println("1. 메인 메뉴로 돌아가기")
        println("2. 주문하기")
        println("3. 수량 변경")
        println("4. 선택 메뉴 삭제")
        println("5. 전체 메뉴 삭제")
        print("입력: ")

        when (readLine()) {
            "1" -> return
            "2" -> {
                order(user, cartItems)
                return
            }

            "3" -> {
                var input: Int?
                while (true) {
                    print("수량을 변경할 메뉴 번호를 입력하세요: ")
                    input = readLine()?.toIntOrNull()
                    if (input == null || input !in 1..cartItems.size) {
                        println("잘못된 번호입니다. 다시 입력하세요.")
                        continue
                    }
                    break
                }

                var newQty: Int?
                while (true) {
                    print("새로운 수량을 입력하세요(1~9): ")
                    newQty = readLine()?.toIntOrNull()
                    if (newQty == null || newQty !in 1..9) {
                        println("잘못된 수량입니다. 다시 입력하세요.")
                        continue
                    }
                    break
                }

                val itemToUpdate = cartItems[input - 1]
                Cart.updateQuantity(itemToUpdate.menu, newQty)
                println("[${itemToUpdate.menu.menuName}]의 수량이 ${newQty}개로 변경되었습니다.")
            }

            "4" -> {
                var input: Int?
                while (true) {
                    print("삭제할 메뉴 번호를 입력하세요: ")
                    input = readLine()?.toIntOrNull()
                    if (input == null || input !in 1..cartItems.size) {
                        println("잘못된 번호입니다. 다시 입력하세요.")
                        continue
                    }
                    break
                }

                val itemToRemove = cartItems[input - 1]
                Cart.removeItem(itemToRemove.menu)
                println("[${itemToRemove.menu.menuName}]가 장바구니에서 삭제되었습니다.")
            }

            "5" -> {
                print("정말로 장바구니를 비우시겠습니까? (Y/N): ")
                if (readLine()?.uppercase() == "Y") {
                    Cart.clear()
                    println("장바구니가 비워졌습니다.")
                    return
                }
            }

            else -> {
                println("잘못된 입력입니다.")
            }
        }
    }
}
