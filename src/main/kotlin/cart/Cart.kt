package cart

import menu.Menu

object Cart {
    private val items = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        val existing = items.find {
            it.menu == item.menu && it.addedTime.toLocalDate() == item.addedTime.toLocalDate()
        }
        if (existing != null) {
            val updated = existing.copy(quantity = existing.quantity + item.quantity)
            items[items.indexOf(existing)] = updated
        } else {
            items.add(item)
        }
    }

    fun updateQuantity(menu: Menu, newQuantity: Int) {
        val index = items.indexOfFirst { it.menu == menu }
        if (index != -1) {
            val oldItem = items[index]
            items[index] = oldItem.copy(quantity = newQuantity)
        }
    }

    // 특정 메뉴 아이템 삭제
    fun removeItem(menu: Menu) {
        items.removeIf { it.menu == menu }
    }

    fun clear() {
        items.clear()
    }

    fun getItems(): List<CartItem> = items.toList()
}