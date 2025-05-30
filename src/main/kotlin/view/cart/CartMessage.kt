package view.cart

object CartMessage {
    const val CART_PAGE_TITLE = "[ 장바구니 ]"
    const val CART_EMPTY = "장바구니가 비어있습니다."
    const val CART_DATE
    const val CART_ITEM
    const val CART_OPTIONS = """
        1. 주문하기
        2. 수량 변경
        3. 선택 메뉴 삭제
        4. 전체 메뉴 삭제
        -1. 메인 메뉴로 돌아가기
    """
    const val ORDER_COMPLETE = "주문이 완료되었습니다."
    const val QUANTITY_CHANGE_GUIDE = "수량을 변경할 메뉴 번호를 입력해주세요. "
    const val QUANTITY_CHANGE =
    const val DELETE_ITEM_INPUT = "삭제할 메뉴 번호를 입력해주세요. "
    const val ITEM_REMOVE =
    const val CLEAR_CART_CONFIRM = "정말로 장바구니를 비우시겠습니까? (Y/N): "
    const val CART_CLEARED = "장바구니가 비워졌습니다."
    const val INPUT_NEW_QUANTITY = "새 수량을 입력하세요. (1~9)"
    const val INVALID_INPUT = "잘못된 입력입니다."
}