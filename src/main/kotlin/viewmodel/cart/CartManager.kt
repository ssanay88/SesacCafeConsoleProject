package viewmodel.cart

import model.cart.CartItem
import view.menu.Menu


object CartManager {

    private val userCart = mutableMapOf<String, MutableList<CartItem>>()

    fun addItem(userId: String, item: CartItem) {
        val cart = userCart.getOrPut(userId) { mutableListOf() }
        val existing = cart.find {
            it.menu == item.menu && it.addedTime.toLocalDate() == item.addedTime.toLocalDate()
        }

        if (existing != null) {
            val updated = existing.copy(quantity = existing.quantity + item.quantity)
            cart[cart.indexOf(existing)] = updated
        } else {
            cart.add(item)
        }
    }

    fun updateQuantity(userId: String, menu: Menu, newQuantity: Int) {
        val cart = userCart[userId] ?: return
        val index = cart.indexOfFirst { it.menu == menu }
        if (index != -1) {
            val oldItem = cart[index]
            cart[index] = oldItem.copy(quantity = newQuantity)
        }
    }

    fun removeItem(userId: String, menu: Menu) {
        userCart[userId]?.removeIf { it.menu == menu }
    }

    fun clear(userId: String) {
        userCart[userId]?.clear()
    }

    fun getItems(userId: String): List<CartItem> {
        return userCart[userId]?.toList() ?: emptyList()
    }

}