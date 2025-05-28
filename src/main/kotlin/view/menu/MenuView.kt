package view.menu

class MenuView {

    fun printMenuPageUI() {
        println("\n[ 메뉴 페이지 ]")
    }

    fun printMenuItem(index: Int, name: String, price: Int) {
        println("${index + 1}. $name (${price}원)")
    }

    fun printMenuSelectionUI() {
        print("\n메뉴 번호를 선택하세요: ")
    }

    fun printInvalidMenuSelectionUI() {
        println("잘못된 선택입니다.")
    }

    fun printQuantityInputUI(menuName: String) {
        print("[$menuName]의 주문수량을 입력하세요(1~9개): ")
    }

    fun printInvalidQuantityMessage() {
        println("잘못된 수량입니다.")
    }

    fun printSelectedMenuMessage(menuName: String, quantity: Int) {
        println("선택한 메뉴: $menuName x ${quantity}개")
    }

    fun printOrderOptions() {
        println("1. 장바구니에 담기")
        println("2. 주문하기")
        print("입력: ")
    }

    fun printAddedToCartMessage(menuName: String, quantity: Int) {
        println("\n{$menuName} ${quantity}개를 장바구니에 담았습니다.\n")
    }

    fun printAfterAddOptions() {
        println("1. 장바구니로 이동하기")
        println("2. 메뉴 페이지로 돌아가기")
        print("입력: ")
    }

    fun printInvalidInputMessage() {
        println("잘못된 입력입니다. 다시 선택하세요.")
    }

}