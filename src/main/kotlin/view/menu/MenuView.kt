package view.menu

class MenuView {

    fun printMenuPageUI() {
        println("[ 메뉴 페이지 ]")
    }

    fun printMenuItem(index: Int, name: String, price: Int) {
        println("${index + 1}. $name (${price}원)")
    }

    fun printMenuSelectionUI() {
        print("메뉴 번호를 선택하세요: ")
    }

    fun printInvalidMenuSelectionUI() {
        println("잘못된 선택입니다.")
    }

    fun printQuantityInputUI(menuName: String) {
        print("\"$menuName\"의 주문 수량을 입력하세요(1~9개): ")
    }

    fun printInvalidQuantityMessage() {
        println("잘못된 수량입니다.")
    }

    fun printSelectedMenuMessage(menuName: String, quantity: Int) {
        println("선택한 메뉴: $menuName x ${quantity}개")
    }

    fun printOrderOptions() {
        println("(1) 장바구니에 담기")
        println("(2) 주문하기")
        print("원하시는 동작을 입력해주세요 : ")
    }

    fun printAddedToCartMessage(menuName: String, quantity: Int) {
        println("\"$menuName\" ${quantity}개를 장바구니에 담았습니다.")
    }

    fun printAfterAddOptions() {
        println("(1) 장바구니로 이동")
        println("(2) 메뉴 페이지로 이동")
        print("원하시는 동작을 입력해주세요: ")
    }

    fun printInvalidInputMessage() {
        println("잘못된 입력입니다. 다시 선택하세요.")
    }

}