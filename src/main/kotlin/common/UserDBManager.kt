package common

import kotlinx.coroutines.*
import model.UserData
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * User Database CURD 처리를 위한 매니저 클래스
 *
 * @constructor Create empty User db manager
 */

object UserDBManager {

    const val USER_DB_PATH = "userFile/userDB.ser"

    private val userDBFile = File(USER_DB_PATH)
    private val usersInMemory: MutableList<UserData> = mutableListOf()

    suspend fun loadUsersFromFile() {
        withContext(Dispatchers.IO) {
            runCatching {
                // 파일이 존재하지 않거나 빈 경우 파일 생성 빈 배열을 담은 파일 생성
                if (!userDBFile.exists() || userDBFile.length() == 0L) createEmptyFile()

                ObjectInputStream(FileInputStream(USER_DB_PATH)).use {
                    usersInMemory.addAll(it.readObject() as List<UserData>)
                }
            }.onSuccess {
                // println(CommonConstants.SUCCESS_LOAD_FILE)
            }.onFailure {
                // println(CommonConstants.FAIL_LOAD_FILE)
            }
        }
    }

    private fun createEmptyFile() {
        userDBFile.createNewFile()
        ObjectOutputStream(FileOutputStream(USER_DB_PATH)).use {
            it.writeObject(mutableListOf<UserData>())
        }
        usersInMemory.clear()
    }

    suspend fun saveChangesToFile() {
        withContext(Dispatchers.IO) {
            runCatching {
                ObjectOutputStream(FileOutputStream(USER_DB_PATH)).use {
                    it.writeObject(usersInMemory)
                }
            }.onSuccess {
                println(CommonConstants.SUCCESS_SAVE_FILE)
            }.onFailure {
                println(CommonConstants.FAIL_SAVE_FILE)
            }
        }
    }


    // 새로운 유저 데이터를 DB에 추가하는 함수
    fun addUser(user: UserData) {
        usersInMemory.add(user)
    }

    fun findUserDataById(userId: String): UserData? = usersInMemory.find { it.id == userId }

    fun isUserDataExists(targetUserId: String): Boolean = findUserDataById(targetUserId) != null

    fun updateUser(updatedUser: UserData) {
        val userIndex = usersInMemory.indexOfFirst { it.id == updatedUser.id }

        if (userIndex != -1) {
            usersInMemory[userIndex] = updatedUser
        }
    }

}