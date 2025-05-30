package model.order

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class OrderItem(
    val menuId: String,
    val menuName: String,
    val quantity: Int
): Serializable