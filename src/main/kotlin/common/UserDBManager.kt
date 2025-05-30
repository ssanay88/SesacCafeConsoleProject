package common

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.time.LocalDateTime

/**
 * User Database CURD 처리를 위한 매니저 클래스
 *
 * @constructor Create empty User d b manager
 */

object UserDBManager {

    const val USER_DB_PATH = "userFile/userDB.json"
    const val USER_DB_SER_PATH = "userFile/userDB.ser"
    private val userDBSerFile = File(USER_DB_SER_PATH)
    private val usersInMemory: MutableList<UserData> = mutableListOf()
    private val userDBFile = File(USER_DB_PATH)
    private val moshi by lazy {
        Moshi.Builder()
            .add(LocalDateTime::class.java, LocalDateTimeAdapter)
            .build()
    }
    private val userListAdapter: JsonAdapter<List<UserData>> = moshi.adapter(
        Types.newParameterizedType(List::class.java, UserData::class.java)
    )

    fun init() {
        loadUsersFromFile()
    }

    private fun loadUsersFromFile() = runBlocking {
        launch(Dispatchers.IO) {
            runCatching {
                println("1")
                if (!userDBSerFile.exists()) {
                    userDBSerFile.createNewFile()
                    ObjectOutputStream(FileOutputStream(USER_DB_SER_PATH)).use {
                        it.writeObject(mutableListOf<UserData>())
                    }
                    usersInMemory.clear()
                }

                if (userDBSerFile.length() == 0L) {
                    userDBSerFile.createNewFile()
                    ObjectOutputStream(FileOutputStream(USER_DB_SER_PATH)).use {
                        it.writeObject(mutableListOf<UserData>())
                    }
                    usersInMemory.clear()
                }

                println("2")
                ObjectInputStream(FileInputStream(USER_DB_SER_PATH)).use {
                    usersInMemory.addAll(it.readObject() as List<UserData>)
                    println(usersInMemory)
                }

            }.onSuccess {
                println("파일 불러오기 성공")
            }.onFailure {
                println("파일 불러오기 실패")
            }
        }

    }

    fun saveChangesToFile() = runBlocking {
        launch(Dispatchers.IO) {
            runCatching {
                ObjectOutputStream(FileOutputStream(USER_DB_SER_PATH)).use {
                    it.writeObject(usersInMemory)
                }
            }.onSuccess {
                println("파일 저장하기 성공")
            }.onFailure {
                println("파일 저장하기 실패")
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