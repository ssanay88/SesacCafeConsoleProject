package cart

import menu.Menu

class CartManager {

    private val userCart = mutableMapOf<String, MutableList<CartItem>>()

    // 추가
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

    // 수량 변경
    fun updateQuantity(userId: String, menu: Menu, newQuantity: Int) {
        val cart = userCart[userId] ?: return
        val index = cart.indexOfFirst { it.menu == menu }
        if (index != -1) {
            val oldItem = cart[index]
            cart[index] = oldItem.copy(quantity = newQuantity)
        }
    }

    // 아이템 삭제
    fun removeItem(userId: String, menu: Menu) {
        userCart[userId]?.removeIf { it.menu == menu }
    }

    // 전체 삭제
    fun clear(userId: String) {
        userCart[userId]?.clear()
    }

    fun getItems(userId: String): List<CartItem> {
        return userCart[userId]?.toList() ?: emptyList()
    }

}