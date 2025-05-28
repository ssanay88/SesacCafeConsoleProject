package viewmodel.signup

import common.UserDBManager
import common.UserData

class SignupManager {

    fun isUserNameTaken(newUserId: String): Boolean = UserDBManager.isUserDataExists(newUserId)

    fun signupNewUser(newUser: UserData) = UserDBManager.addUser(newUser)

}