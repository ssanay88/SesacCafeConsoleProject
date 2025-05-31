package viewmodel.login

import common.AuthManager
import model.UserData
import common.UserDBManager

class LoginManager {

    fun isUserIdExists(userId: String): Boolean = UserDBManager.isUserDataExists(userId)

    fun loginSuccess(loginUser: UserData) {
        // DB에서 id에 해당하는 유저 데이터 불러와서 LoginUserInfo에 저장
        AuthManager.apply {
            login(loginUser)
            printLoginUserInfo()
        }

    }

}