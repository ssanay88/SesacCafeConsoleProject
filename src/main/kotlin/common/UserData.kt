package common

data class UserData(
    val name: String,
    val id: String,
    val password: String,
    var balance: Int = 0,
    val orderHistory: MutableList<Order> = mutableListOf(),
    var stamp: Int = 0
)
