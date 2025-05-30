package view

import common.*
import view.login.LoginPage
import view.signup.SignupPage

object SesacCafeConsoleView {

    private const val START_PG_MESSAGE = """
        [ 새싹 카페 주문 프로그램 실행 ]
        (1) 로그인 
        (2) 신규 회원 가입
        원하시는 동작을 입력해주세요 : 
    """
    private const val END_PG_MESSAGE = "새싹 카페 프로그램 종료합니다. 감사합니다."

    fun startSesacCafePG() {
        print(START_PG_MESSAGE.trimIndent())
        val input = Input.inputOneToMax(2)
        when (input) {
            1 -> startLoginPage()
            2 -> startSignupPage()
            else -> println(CommonConstants.ERROR_INVALID_INPUT)
        }
        UserDBManager.saveChangesToFile()
        println(END_PG_MESSAGE)
    }

    fun startLoginPage() {
        Input.printDivLine()
        val loginPage = LoginPage()
        loginPage.startLogin()
    }

    fun startSignupPage() {
        Input.printDivLine()
        val signupPage = SignupPage()
        signupPage.startSignup()
    }

}