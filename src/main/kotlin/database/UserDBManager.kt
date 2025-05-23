package database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import common.UserData
import java.io.File

/**
 * User Database CURD 처리를 위한 매니저 클래스
 *
 * @constructor Create empty User d b manager
 */

object UserDBManager {

    const val USER_DB_PATH = "src/main/kotlin/database/userDB.json"
    private val userDBFile = File(USER_DB_PATH)
    private val gson = Gson()

    init {
        if (!userDBFile.exists()) {
            userDBFile.createNewFile()
            userDBFile.writeText("[]")
        }
    }

    fun readAllUsers(): MutableList<UserData> {
        return try {
            if (userDBFile.exists() && userDBFile.length() > 0) {
                val fileContent = userDBFile.readText()
                val listType = object : TypeToken<MutableList<UserData>>() {}.type
                gson.fromJson(fileContent, listType) ?: mutableListOf()
            } else {
                mutableListOf()
            }
        } catch (e: Exception) {
            System.err.println("Error reading userDB.json: ${e.message}")
            e.printStackTrace()
            mutableListOf() // 오류 발생 시 빈 리스트 반환
        }
    }

    fun updateUserDB(updatedUsers: List<UserData>) {
        userDBFile.writeText(gson.toJson(updatedUsers))
    }

    // 새로운 유저 데이터를 DB에 추가하는 함수
    fun addNewUser(newUser: UserData) {
        val users = readAllUsers()
        users.add(newUser)
        updateUserDB(users)
    }

    fun findUserDataById(userId: String): UserData? = readAllUsers().find { it.id == userId }

    fun isUserDataExists(targetUserId: String): Boolean = findUserDataById(targetUserId) != null

    fun updateUser(updatedUser: UserData) {
        val users = readAllUsers()
        val userIndex = users.indexOf(updatedUser)
        if (userIndex != -1) {
            users[userIndex] = updatedUser
            updateUserDB(users)
        }
    }


}