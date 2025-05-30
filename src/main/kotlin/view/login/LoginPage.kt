package view.login

import common.CommonConstants
import common.InputView
import common.UserDBManager
import view.home.HomePage
import viewmodel.login.LoginManager


class LoginPage {

    private val loginManager = LoginManager()
    private val homePage = HomePage()

    fun startLogin() {
        // 로그인 시작 텍스트 출력
        print(LoginConstants.ENTER_ID.trimIndent())
        var inputUserId = InputView.getUserIdInput()
        if (inputUserId.equals(CommonConstants.GO_BACK_INPUT)) return
        // ID 입력 및 확인
        while (!loginManager.isUserIdExists(inputUserId)) {
            print(LoginConstants.ID_NOT_EXIST)
            inputUserId = InputView.getUserIdInput()
            if (inputUserId.equals(CommonConstants.GO_BACK_INPUT)) return
        }
        val loginUserData = UserDBManager.findUserDataById(inputUserId)

        // 비밀번호 입력 및 확인
        print(LoginConstants.ENTER_PW)
        var inputUserPw = InputView.getUserPwInput()
        if (inputUserPw.equals(CommonConstants.GO_BACK_INPUT)) return
        while (loginUserData?.password != inputUserPw) {
            print(LoginConstants.PW_NOT_CORRECT)
            inputUserPw = InputView.getUserPwInput()
            if (inputUserPw.equals(CommonConstants.GO_BACK_INPUT)) return
        }

        // 로그인 정보 저장
        loginManager.loginSuccess(loginUserData)
        println(LoginConstants.LOGIN_SUCCESS_MESSAGE)
        OutputView.printDivLine()
        homePage.startHomePage()
    }

}