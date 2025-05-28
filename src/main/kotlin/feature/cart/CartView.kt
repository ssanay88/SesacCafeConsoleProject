package feature.cart

class CartView {
    fun printEnterCartUI() {
        println("\n[ 장바구니 ]")
    }

    fun printCartEmptyMessage() {
        println("장바구니가 비어있습니다.")
    }

    fun printCartDate(date: String) {
        println(date)
    }

    fun printCartItem(index: Int, name: String, price: Int, quantity: Int, total: Int) {
        println("${index}. ${name}    ${price}      ${quantity}      ${total}원")
    }

    fun printCartOptions() {
        println("1. 주문하기")
        println("2. 수량 변경")
        println("3. 선택 메뉴 삭제")
        println("4. 전체 메뉴 삭제")
        println("-1. 메인 메뉴로 돌아가기")
        print("입력: ")
    }

    fun printOrderCompleteMessage() {
        println("주문이 완료되었습니다.")
    }

    fun printQuantityChangeUI() {
        print("수량을 변경할 메뉴 번호를 입력하세요: ")
    }

    fun printQuantityChangedMessage(menuName: String, quantity: Int) {
        println("[$menuName] 수량이 ${quantity}개로 변경되었습니다.")
    }

    fun printDeleteItemUI() {
        print("삭제할 메뉴 번호를 입력하세요: ")
    }

    fun printItemRemovedMessage(menuName: String) {
        println("[$menuName]가 장바구니에서 삭제되었습니다.")
    }

    fun printClearCartConfirmationUI() {
        print("정말로 장바구니를 비우시겠습니까? (Y/N): ")
    }

    fun printCartClearedMessage() {
        println("장바구니가 비워졌습니다.")
    }

    fun printQuantityInputUI() {
        print("새로운 수량을 입력하세요(1~9): ")
    }

    fun printInvalidInputMessage() {
        println("잘못된 입력입니다.")
    }
}