package common

data class Order(
    val orderId: String,
    val date: String,
    val orderItems: MutableList<OrderItem>
)
