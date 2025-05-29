package common

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.File
import java.time.LocalDateTime

/**
 * User Database CURD 처리를 위한 매니저 클래스
 *
 * @constructor Create empty User d b manager
 */

object UserDBManager {

    const val USER_DB_PATH = "userFile/userDB.json"
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

    init {
        loadUsersFromFile()
    }

    private fun loadUsersFromFile() {
        runCatching {
            if (!userDBFile.exists()) {
                userDBFile.createNewFile()
                userDBFile.writeText("[]") // 빈 JSON 배열로 초기화
                usersInMemory.clear()
                return
            }

            if (userDBFile.length() == 0L) {
                userDBFile.writeText("[]") // 깔끔하게 빈 JSON 배열로 덮어쓰기
                usersInMemory.clear()
                return
            }

            val fileContent = userDBFile.readText() // 파일 내용을 문자열로 읽기
            val loadedUsers = userListAdapter.fromJson(fileContent) // Moshi로 역직렬화
            // 불러온 데이터가 null이 아닐 경우에만 추가
            usersInMemory.addAll(loadedUsers ?: emptyList())
        }.onSuccess {
            println("파일 불러오기 성공")
        }.onFailure {
            println("파일 불러오기 실패")
        }
    }

    fun saveChangesToFile() {
        runCatching {
            val json = userListAdapter.toJson(usersInMemory) // Moshi로 직렬화
            userDBFile.writeText(json) // JSON 문자열을 파일에 쓰기
        }.onSuccess {
            println("파일 저장하기 성공")
        }.onFailure {
            println("파일 저장하기 실패")
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