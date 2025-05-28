package auth.signup

import core.UserData
import core.database.UserDBManager

class SignupManager {

    fun isUserNameTaken(newUserId: String): Boolean = UserDBManager.isUserDataExists(newUserId)

    fun signupNewUser(newUser: UserData) = UserDBManager.addUser(newUser)

}