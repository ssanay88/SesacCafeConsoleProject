package common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

/**
 * User Database CURD 처리를 위한 매니저 클래스
 *
 * @constructor Create empty User d b manager
 */

object UserDBManager {

    const val USER_DB_PATH = "src/main/kotlin/database/userDB.json"
    private val userDBFile = File(USER_DB_PATH)

    // 새로운 유저 데이터를 DB에 추가하는 함수
    fun addNewUser(user: UserData) {
        val gson = Gson()
        try {
            val existingUsers = if (userDBFile.exists() && userDBFile.length() > 0) {
                val fileContent = userDBFile.readText() // 파일의 전체 내용을 문자열로 읽어와서 저장
                val listType = object : TypeToken<MutableList<UserData>>() {}.type
                gson.fromJson<MutableList<UserData>>(fileContent, listType) ?: mutableListOf()
            } else {
                mutableListOf()
            }
            existingUsers.add(user)
            userDBFile.writeText(gson.toJson(existingUsers))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}