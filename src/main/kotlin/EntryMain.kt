import login.LoginPage
import signup.SignupPage

fun main() {
//    val newUser = UserData("양대리", "pplloo748", "q1w2e3r4")
//    UserDBManager.addNewUser(newUser)
    startSesacCafePG()
}

fun startSesacCafePG() {
    println("[새싹 카페 주문 프로그램 실행]")
    print("로그인을 원하시는 경우 1번, 신규 회원 가입을 원하시는 경우 2번을 입력해주세요. : ")
    when (readln().trim().toInt()) {
        1 -> startLoginPage()
        2 -> startSignupPage()
        else -> println("잘못된 입력입니다.")
    }
}

fun startLoginPage() {
    val loginPage = LoginPage()
    loginPage.startLogin()
    goToMainPage()
}

fun startSignupPage() {
    val signuPage = SignupPage()
    signuPage.startSignup()
    goToMainPage()
}

fun goToMainPage() {

}



