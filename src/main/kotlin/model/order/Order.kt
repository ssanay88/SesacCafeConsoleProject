package model.order

import com.squareup.moshi.JsonClass
import java.time.LocalDateTime
import java.util.UUID

@JsonClass(generateAdapter = true)
data class Order(
    val orderId: String = UUID.randomUUID().toString(),
    val orderDate: LocalDateTime = LocalDateTime.now(),
    val orderItems: List<OrderItem>
)