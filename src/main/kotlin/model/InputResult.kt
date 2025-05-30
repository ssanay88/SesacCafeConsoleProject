package model

sealed class InputResult {
    data class Success(val input: String): InputResult()
    data class GoBack(val goBackMessage: String): InputResult()
    data class InputIsEmpty(val message: String): InputResult()
    data class InputContainsEmpty(val message: String): InputResult()
    data class InputIsShort(val message: String): InputResult()
}