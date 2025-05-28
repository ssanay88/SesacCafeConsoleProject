import core.inputOneToMax
import core.printDivLine
import auth.login.LoginPage
import auth.signup.SignupPage

fun main() {
    startSesacCafePG()
}

fun startSesacCafePG() {
    print("""
        [ 새싹 카페 주문 프로그램 실행 ]
        (1) 로그인 
        (2) 신규 회원 가입
        원하시는 동작을 입력해주세요 : 
    """.trimIndent())
    val input = inputOneToMax(2)
    when (input) {
        1 -> startLoginPage()
        2 -> startSignupPage()
        else -> println("잘못된 입력입니다.")
    }
    printDivLine()
}

fun startLoginPage() {
    val loginPage = LoginPage()
    loginPage.startLogin()
}

fun startSignupPage() {
    val signupPage = SignupPage()
    signupPage.startSignup()
}
