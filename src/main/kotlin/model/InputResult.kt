package model

sealed class InputResult {
    object Success: InputResult()
    object GoBack: InputResult()
    data class InputIsEmpty(val message: String): InputResult()
    data class InputContainsEmpty(val message: String): InputResult()
    data class InputIsShort(val message: String): InputResult()
}