package common

object Input {

    fun getStringInput(text: String): String {
        print(text)
        return readlnOrNull()?.trim() ?: ""
    }

    fun getIntInput(text: String): Int? {
        val input =getStringInput(text)
        return input.toIntOrNull()
    }

}