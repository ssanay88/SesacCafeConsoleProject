package view.login

import common.CommonConstants
import common.InputView
import common.OutputView
import common.UserDBManager
import model.InputResult
import view.home.HomePage
import viewmodel.login.LoginManager


class LoginPage {

    private val loginManager = LoginManager()
    private val homePage = HomePage()

    fun startLogin() {
        // 로그인 시작 텍스트 출력
        print(LoginMessage.ENTER_ID.trimIndent())

        var inputUserId = checkInputResult { InputView.getUserIdInput() }
        if (inputUserId == CommonConstants.GO_BACK_INPUT) return

        // ID 입력 및 확인
        while (!loginManager.isUserIdExists(inputUserId)) {
            print(LoginMessage.ID_NOT_EXIST)
            inputUserId = checkInputResult { InputView.getUserIdInput() }
            if (inputUserId == CommonConstants.GO_BACK_INPUT) return
        }

        // DB에서 입력한 ID와 일치하는 회원의 정보
        val loginUserData = UserDBManager.findUserDataById(inputUserId)

        // 비밀번호 입력 및 확인
        print(LoginMessage.ENTER_PW)
        var inputUserPw = checkInputResult { InputView.getUserPwInput() }
        if (inputUserPw == CommonConstants.GO_BACK_INPUT) return

        while (loginUserData?.password != inputUserPw) {
            print(LoginMessage.PW_NOT_CORRECT)
            inputUserPw = checkInputResult { InputView.getUserPwInput() }
            if (inputUserPw == CommonConstants.GO_BACK_INPUT) return
        }

        // 로그인 정보 저장
        loginManager.loginSuccess(loginUserData)
        println(LoginMessage.LOGIN_SUCCESS_MESSAGE)
        OutputView.printDivLine()
        homePage.startHomePage()
    }

    private fun checkInputResult(getUserInputFun: () -> InputResult): String {
        while (true) {
            when (val inputUserResult = getUserInputFun()) {
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