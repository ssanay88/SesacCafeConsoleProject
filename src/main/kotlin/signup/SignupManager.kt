package signup

import common.UserData
import database.UserDBManager

class SignupManager {

    fun isUserNameTaken(newUserId: String): Boolean = UserDBManager.isUserDataExists(newUserId)

    fun signupNewUser(newUser: UserData) = UserDBManager.addUser(newUser)

}