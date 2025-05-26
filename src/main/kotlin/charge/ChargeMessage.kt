package charge

object ChargeMessage {
    const val CHARGE_PAGE_TITLE = "[ 금액 충전 페이지 ]"
    const val CHARGE_GUIDE = "충전하실 금액을 입력해주세요. (최소금액: 5000원, 최대금액: 50000원)"
    const val GO_BACK_GUIDE = "(0)을 입력하면 이전 메뉴로 돌아갑니다."
    const val CHARGE_INPUT_PROMPT = "충전 금액 -> "
    const val INVALID_INPUT = "잘못된 입력입니다."
    const val GOING_BACK = "이전 메뉴로 돌아갑니다."
    const val CHARGE_COMPLETE = "%d원 충전 완료되었습니다."
    const val CURRENT_BALANCE = "현재 잔액: %d원"
    const val AMOUNT_TOO_LOW = "최소 충전 금액은 5000원 입니다."
    const val AMOUNT_TOO_HIGH = "최대 충전 금액은 50000원 입니다."
}