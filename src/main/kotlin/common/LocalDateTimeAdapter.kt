package common

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeAdapter : JsonAdapter<LocalDateTime>() {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun fromJson(reader: JsonReader): LocalDateTime? {
        // null 토큰 처리
        if (reader.peek() == JsonReader.Token.NULL) {
            return reader.nextNull() // null 값을 소비
        }
        // 문자열 토큰만 허용
        if (reader.peek() != JsonReader.Token.STRING) {
            throw com.squareup.moshi.JsonDataException("문자열 토큰만 허용")
        }
        // 문자열을 읽고 파싱
        return LocalDateTime.parse(reader.nextString(), formatter)
    }

    override fun toJson(writer: JsonWriter, value: LocalDateTime?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.value(value.format(formatter))
        }
    }

}