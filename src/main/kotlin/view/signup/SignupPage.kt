package view.signup

import common.Input
import common.UserData
import view.home.HomePage
import viewmodel.login.LoginManager
import viewmodel.signup.SignupManager

class SignupPage {

    private val signupManager = SignupManager()
    private val loginManager = LoginManager()
    private val homePage = HomePage()

    fun startSignup() {
        // Id 입력
        print(SignupConstants.ENTER_NEW_ID.trimIndent())
        var inputNewUserId = readln().trim()

        while (signupManager.isUserNameTaken(inputNewUserId)) {
            print(SignupConstants.ENTER_ID_IS_TAKEN)
            inputNewUserId = readln().trim()
        }

        // 이름 입력
        print(SignupConstants.ENTER_NEW_NAME)
        val inputNewUserName = readln().trim()

        // PW 입력
        print(SignupConstants.ENTER_NEW_PW)
        var inputNewUserPw = readln().trim()
        print(SignupConstants.ENTER_NEW_PW_REPEAT)
        while (inputNewUserPw != readln().trim()) {
            // 비밀번호 확인 실패
            print(SignupConstants.FAIL_TO_CHECK_PW)
        }

        // 신규 유저 등록
        val newUser = UserData(name = inputNewUserName, id = inputNewUserId, password = inputNewUserPw)
        signupManager.signupNewUser(newUser)
        // 로그인 정보 저장
        loginManager.loginSuccess(newUser)
        println(SignupConstants.SIGNUP_SUCCESS_MESSAGE)
        Input.printDivLine()
        homePage.startHomePage()
    }
}