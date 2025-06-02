package view

import common.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import view.login.LoginPage
import view.signup.SignupPage

object SesacCafeConsoleView {

    private const val START_PG_MESSAGE = """
        [ ☕️ 새싹 카페 주문 프로그램 실행 ☕️ ]
        (1) 로그인 
        (2) 신규 회원 가입
        (0) 프로그램 종료
        원하시는 동작을 입력해주세요 : 
    """
    private const val END_PG_MESSAGE = " 👋 새싹 카페 프로그램 종료합니다. 감사합니다. 👋 "

    init {
        // 프로그램 실행 시 파일에 저장된 데이터를 비동기로 로딩합니다.
        CoroutineScope(Dispatchers.IO).launch {
            UserDBManager.loadUsersFromFile()
        }
    }


    suspend fun startSesacCafePG() {
        while (true) {
            OutputView.printDivLine()
            print(START_PG_MESSAGE.trimIndent())
            val input = InputView.inputZeroToMax(2)
            when (input) {
                1 -> startLoginPage()
                2 -> startSignupPage()
                0 -> break
                else -> println(CommonConstants.ERROR_INVALID_INPUT)
            }
        }

        saveChangesToFile().join()

        println(END_PG_MESSAGE)
    }

    private fun saveChangesToFile() =  CoroutineScope(Dispatchers.IO).launch {
        UserDBManager.saveChangesToFile()
    }

    private fun startLoginPage() {
        OutputView.printDivLine()
        val loginPage = LoginPage()
        loginPage.startLogin()
    }

    private fun startSignupPage() {
        OutputView.printDivLine()
        val signupPage = SignupPage()
        signupPage.startSignup()
    }

}