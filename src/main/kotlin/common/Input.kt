package common

import model.InputResult

/**
 * 입력에 사용할 함수를 모아둔 파일
 */
object Input {

    fun getStringInput(text: String): String {
        print(text)
        return readlnOrNull()?.trim() ?: ""
    }

    fun getIntInput(text: String): Int? {
        val input = getStringInput(text)
        return input.toIntOrNull()
    }

    fun getOnlyNumInput(): Int {
        val numericRegex = "^[0-9]+$".toRegex()

        while (true) {
            val input = readln().trim()

            if (input.isEmpty()) {
                print(CommonConstants.ERROR_INPUT_NOTHING)
                continue
            }

            if (input.matches(numericRegex)) {
                return input.toInt()
            } else {
                print(CommonConstants.ERROR_INPUT_ONLY_NUMBER)
            }
        }
    }

    fun inputZeroToMax(maxInput: Int): Int {
        var input = getOnlyNumInput()
        while (input !in 0..maxInput) {
            print(String.format(CommonConstants.ERROR_INPUT_ZERO_TO_MAX, maxInput))
            input = getOnlyNumInput()
        }
        return input
    }

    fun inputOneToMax(maxInput: Int): Int {
        var input = getOnlyNumInput()
        while (input !in 1..maxInput) {
            print(String.format(CommonConstants.ERROR_INPUT_ONE_TO_MAX, maxInput))
            input = getOnlyNumInput()
        }
        return input
    }

    fun getUserIdInput(): InputResult {
        val input = readln().trim()
        when {
            input.equals(CommonConstants.GO_BACK_INPUT) -> InputResult.GoBack
            input.isEmpty() -> InputResult.InputIsEmpty(CommonConstants.ERROR_INPUT_ID_IS_EMPTY)
            input.contains(" ") -> InputResult.InputContainsEmpty(CommonConstants.ERROR_INPUT_ID_CONTAINS_EMPTY)
            input.length < CommonConstants.MIN_ID_LENGTH -> InputResult.InputIsShort(CommonConstants.ERROR_INPUT_ID_LENGTH)
            else -> InputResult.Success
        }
    }

    fun getUserPwInput(): String {
        var input = readln()
        while (true) {
            when {
                input.equals(CommonConstants.GO_BACK_INPUT) -> {
                    break
                }
                input.isEmpty() -> {
                    print(CommonConstants.ERROR_INPUT_PW_IS_EMPTY)
                    input = readln()
                }
                input.contains(" ") -> {
                    print(CommonConstants.ERROR_INPUT_PW_CONTAINS_EMPTY)
                    input = readln()
                }
                input.length < CommonConstants.MIN_PW_LENGTH -> {
                    print(CommonConstants.ERROR_INPUT_PW_LENGTH)
                    input = readln()
                }
                else -> break
            }
        }
        return input
    }

    fun printDivLine() {
        println(CommonConstants.DIV_LINE)
    }
}