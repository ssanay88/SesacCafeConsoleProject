package view.signup

import common.UserData
import common.printDivLine
import view.home.HomePage
import viewmodel.login.LoginManager
import viewmodel.signup.SignupManager

class SignupPage {

    private val signupManager = SignupManager()
    private val loginManager = LoginManager()
    private val homePage = HomePage()

    fun startSignup() {
        // Id 입력
        printEnterNewIdUI()
        var inputNewUserId = readln().trim()

        while (signupManager.isUserNameTaken(inputNewUserId)) {
            printEnterIdIsTaken()
            inputNewUserId = readln().trim()
        }

        // 이름 입력
        printEnterNewNameUI()
        val inputNewUserName = readln().trim()

        // PW 입력
        printEnterNewPwUI()
        var inputNewUserPw = readln().trim()
        printEnterPwRepeatUI()
        while (inputNewUserPw != readln().trim()) {
            // 비밀번호 확인 실패
            printFailToCheckPwUI()
        }

        // 신규 유저 등록
        val newUser = UserData(name = inputNewUserName, id = inputNewUserId, password = inputNewUserPw)
        signupManager.signupNewUser(newUser)
        // 로그인 정보 저장
        loginManager.loginSuccess(newUser)
        printSignupSuccessMessage()
        printDivLine()
        homePage.startHomePage()
    }
}