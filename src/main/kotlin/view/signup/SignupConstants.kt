package view.signup

object SignupConstants {
    const val ENTER_NEW_ID = """
    [ 회원가입 페이지 ]
    사용할 ID 입력 (뒤로 가기 - 0번 입력) : 
    """
    const val ENTER_ID_IS_TAKEN = "이미 사용중인 ID 입니다. 다른 ID를 입력해주세요 (뒤로 가기 - 0번 입력) : "
    const val ENTER_NEW_PW = "사용할 PW 입력 (뒤로 가기 - 0번 입력) : "
    const val ENTER_NEW_NAME = "회원님의 이름을 입력 : "
    const val ENTER_NEW_PW_REPEAT = "PW 확인을 위해 한번 더 입력해주세요 : "
    const val FAIL_TO_CHECK_PW = "재입력하신 PW가 다릅니다. 재입력 부탁드립니다 (뒤로 가기 - 0번 입력) : "
    const val SIGNUP_SUCCESS_MESSAGE = "회원 가입에 성공했습니다!"
}