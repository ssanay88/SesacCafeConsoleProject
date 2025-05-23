package signup

class SignupPage {

    private val signupManager = SignupManager()

    fun startSignup() {
        printEnterNewIdUI()
        var inputNewUserId = readln().trim()

        while (!signupManager.isUserNameTaken(inputNewUserId)) {
            printEnterIdIsTaken()
            inputNewUserId = readln().trim()
        }

        printEnterNewPwUI()
        var inputNewUserPw = readln().trim()
        printEnterPwRepeatUI()
        while (inputNewUserPw != readln().trim()) {
            // 비밀번호 확인 실패
            printFailToCheckPwUI()
        }

        // 신규 유저 등록

    }
}