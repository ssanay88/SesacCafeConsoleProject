package view.signup

import common.CommonConstants
import common.InputView
import common.OutputView
import common.UserData
import model.InputResult
import view.home.HomePage
import viewmodel.login.LoginManager
import viewmodel.signup.SignupManager

class SignupPage {

    private val signupManager = SignupManager()
    private val loginManager = LoginManager()
    private val homePage = HomePage()

    fun startSignup() {
        // Id 입력
        print(SignupMessage.ENTER_NEW_ID.trimIndent())
        var inputUserId = checkIdInputResult()
        if (inputUserId == CommonConstants.GO_BACK_INPUT) return

        while (signupManager.isUserNameTaken(inputUserId)) {
            print(SignupMessage.ENTER_ID_IS_TAKEN)
            inputUserId = checkIdInputResult()
            if (inputUserId == CommonConstants.GO_BACK_INPUT) return
        }

        // 이름 입력
        print(SignupMessage.ENTER_NEW_NAME)
        val inputNewUserName = readln().trim()

        // PW 입력
        print(SignupMessage.ENTER_NEW_PW)
        val inputNewUserPw = InputView.getUserPwInput()
        if (inputNewUserPw.equals(CommonConstants.GO_BACK_INPUT)) return
        print(SignupMessage.ENTER_NEW_PW_REPEAT)
        var inputNewUserPwRepeat = InputView.getUserPwInput()
        while (inputNewUserPw != inputNewUserPwRepeat) {
            // 비밀번호 확인 실패
            print(SignupMessage.FAIL_TO_CHECK_PW)
            inputNewUserPwRepeat = InputView.getUserPwInput()
            if (inputNewUserPwRepeat.equals(CommonConstants.GO_BACK_INPUT)) return
        }

        // 신규 유저 등록
        val newUser = UserData(name = inputNewUserName, id = inputUserId, password = inputNewUserPw)
        signupManager.signupNewUser(newUser)
        // 로그인 정보 저장
        loginManager.loginSuccess(newUser)
        println(SignupMessage.SIGNUP_SUCCESS_MESSAGE)
        OutputView.printDivLine()
        homePage.startHomePage()
    }

    private fun checkIdInputResult(): String {
        while (true) {
            when (val inputUserResult = InputView.getUserIdInput()) {
                is InputResult.Success -> {
                    return inputUserResult.input
                }
                is InputResult.GoBack -> {
                    return inputUserResult.goBackMessage
                }
                is InputResult.InputIsEmpty -> print(inputUserResult.message)
                is InputResult.InputContainsEmpty -> print(inputUserResult.message)
                is InputResult.InputIsShort -> print(inputUserResult.message)
            }
        }
    }


}