package model.order

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderItem(
    val menuId: String,
    val menuName: String,
    val quantity: Int
)