package view.login

import common.Input
import common.UserDBManager
import view.home.HomePage
import viewmodel.login.LoginManager


class LoginPage {

    private val loginManager = LoginManager()
    private val homePage = HomePage()

    fun startLogin() {
        // 로그인 시작 텍스트 출력
        printEnterIdUI()
        var inputUserId = Input.getUserIdInput()
        // ID 입력 및 확인
        while (!loginManager.isUserIdExists(inputUserId)) {
            printIdNotExistUI()
            inputUserId = Input.getUserIdInput()
        }
        val loginUserData = UserDBManager.findUserDataById(inputUserId)

        // 비밀번호 입력 및 확인
        printEnterPwUI()
        var inputUserPw = Input.getUserPwInput()
        while (loginUserData?.password != inputUserPw) {
            printPwNotCorrectUI()
            inputUserPw = Input.getUserPwInput()
        }

        // 로그인 정보 저장
        loginManager.loginSuccess(loginUserData)
        printLoginSuccessMessage()
        Input.printDivLine()
        homePage.startHomePage()
    }

}