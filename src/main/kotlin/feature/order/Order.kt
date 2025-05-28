package feature.order

import java.time.LocalDateTime
import java.util.UUID

data class Order(
    val orderId: String = UUID.randomUUID().toString(),
    val orderDate: LocalDateTime = LocalDateTime.now(),
    val orderItems: List<OrderItem>
)