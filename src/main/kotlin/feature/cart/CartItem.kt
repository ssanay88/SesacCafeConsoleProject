package feature.cart

import feature.menu.Menu
import java.time.LocalDateTime

data class CartItem (
    val menu: Menu,
    val quantity: Int,
    val addedTime: LocalDateTime = LocalDateTime.now()
)