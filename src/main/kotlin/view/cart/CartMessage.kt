package view.cart

object CartMessage {
    const val CART_PAGE_TITLE = "[ 장바구니 ]"
    const val CART_EMPTY = "장바구니가 비어있습니다."
    const val CART_DATE = "--- %s ---"
    const val CART_ITEM = "[%d] %s  %d원  %d개    %d원"
    const val CART_OPTIONS = """
        (1) 주문하기
        (2) 수량 변경
        (3) 선택 메뉴 삭제
        (4) 전체 메뉴 삭제
        (0) 이전 화면으로 돌아가기
    """
    const val GOING_BACK = "이전 화면으로 돌아갑니다."
    const val ORDER_COMPLETE = "주문이 완료되었습니다."
    const val QUANTITY_CHANGE_GUIDE = "수량을 변경할 메뉴 번호를 입력해주세요: "
    const val QUANTITY_CHANGE = "%s 수량이 %d개로 변경되었습니다."
    const val DELETE_ITEM_INPUT = "삭제할 메뉴 번호를 입력해주세요: "
    const val ITEM_REMOVE = "%s가 장바구니에서 삭제되었습니다."
    const val CLEAR_CART_CONFIRM = "정말로 장바구니를 비우시겠습니까? (Y/N): "
    const val CART_CLEARED = "장바구니가 비워졌습니다:"
    const val INPUT_NEW_QUANTITY = "새 수량을 입력하세요: (1~9)"
    const val INVALID_INPUT = "잘못된 입력입니다."
}