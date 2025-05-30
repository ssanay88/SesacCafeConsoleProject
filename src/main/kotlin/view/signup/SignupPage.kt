package view.signup

import common.CommonConstants
import common.InputView
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
        var inputNewUserId = InputView.getUserIdInput()
        if (inputNewUserId.equals(CommonConstants.GO_BACK_INPUT)) return

        while (signupManager.isUserNameTaken(inputNewUserId)) {
            print(SignupConstants.ENTER_ID_IS_TAKEN)
            inputNewUserId = InputView.getUserIdInput()
            if (inputNewUserId.equals(CommonConstants.GO_BACK_INPUT)) return
        }

        // 이름 입력
        print(SignupConstants.ENTER_NEW_NAME)
        val inputNewUserName = readln().trim()

        // PW 입력
        print(SignupConstants.ENTER_NEW_PW)
        val inputNewUserPw = InputView.getUserPwInput()
        if (inputNewUserPw.equals(CommonConstants.GO_BACK_INPUT)) return
        print(SignupConstants.ENTER_NEW_PW_REPEAT)
        var inputNewUserPwRepeat = InputView.getUserPwInput()
        while (inputNewUserPw != inputNewUserPwRepeat) {
            // 비밀번호 확인 실패
            print(SignupConstants.FAIL_TO_CHECK_PW)
            inputNewUserPwRepeat = InputView.getUserPwInput()
            if (inputNewUserPwRepeat.equals(CommonConstants.GO_BACK_INPUT)) return
        }

        // 신규 유저 등록
        val newUser = UserData(name = inputNewUserName, id = inputNewUserId, password = inputNewUserPw)
        signupManager.signupNewUser(newUser)
        // 로그인 정보 저장
        loginManager.loginSuccess(newUser)
        println(SignupConstants.SIGNUP_SUCCESS_MESSAGE)
        OutputView.printDivLine()
        homePage.startHomePage()
    }
}