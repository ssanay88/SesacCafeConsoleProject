package view.login

import common.UserDBManager
import common.getUserIdInput
import common.getUserPwInput
import common.printDivLine
import view.home.HomePage
import viewmodel.login.LoginManager


class LoginPage {

    private val loginManager = LoginManager()
    private val homePage = HomePage()

    fun startLogin() {
        // 로그인 시작 텍스트 출력
        printEnterIdUI()
        var inputUserId = getUserIdInput()
        // ID 입력 및 확인
        while (!loginManager.isUserIdExists(inputUserId)) {
            printIdNotExistUI()
            inputUserId = getUserIdInput()
        }
        val loginUserData = UserDBManager.findUserDataById(inputUserId)

        // 비밀번호 입력 및 확인
        printEnterPwUI()
        var inputUserPw = getUserPwInput()
        while (loginUserData?.password != inputUserPw) {
            printPwNotCorrectUI()
            inputUserPw = getUserPwInput()
        }

        // 로그인 정보 저장
        loginManager.loginSuccess(loginUserData)
        printLoginSuccessMessage()
        printDivLine()
        homePage.startHomePage()
    }

}