package model.order

import java.io.Serializable

data class OrderItem(
    val menuId: String,
    val menuName: String,
    val quantity: Int
): Serializable