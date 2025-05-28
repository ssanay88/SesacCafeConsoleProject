package core.database

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import com.google.gson.reflect.TypeToken
import core.UserData
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * User Database CURD 처리를 위한 매니저 클래스
 *
 * @constructor Create empty User d b manager
 */

object UserDBManager {

    const val USER_DB_PATH = "src/main/kotlin/database/userDB.json"
    private val usersInMemory: MutableList<UserData> = mutableListOf()
    private val userDBFile = File(USER_DB_PATH)
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(
            LocalDateTime::class.java,
            JsonSerializer<LocalDateTime> { src, typeOfSrc, context ->
                JsonPrimitive(src.format(dateTimeFormatter))
            }
        )
        .registerTypeAdapter(
            LocalDateTime::class.java,
            JsonDeserializer<LocalDateTime> { json, typeOfT, context ->
                LocalDateTime.parse(json.asString, dateTimeFormatter)
            }
        )
        .setPrettyPrinting() // JSON 출력을 예쁘게 포맷팅 (선택 사항)
        .create()

    init {
        loadUsersFromFile()
    }

    private fun loadUsersFromFile() {
        if (!userDBFile.exists()) {
            userDBFile.createNewFile()
            userDBFile.writeText("[]")
        }

        try {
            if (userDBFile.length() > 0) {
                val fileContent = userDBFile.readText()
                val listType = object : TypeToken<MutableList<UserData>>() {}.type
                val loadUsers = gson.fromJson<MutableList<UserData>>(fileContent, listType) ?: mutableListOf()
                usersInMemory.addAll(loadUsers ?: mutableListOf())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            usersInMemory.clear()
        }
    }

    fun saveChangesToFile() {
        try {
            userDBFile.writeText(gson.toJson(usersInMemory))
            println("변경된 유저 데이터들이 성공적으로 저장되었습니다.")
        } catch (e: Exception) {
            System.err.println("유저 데이터를 파일에 저장하는 중 오류 발생: ${e.message}")
            e.printStackTrace()
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