package common

import model.InputResult
import view.ConsoleInput

/**
 * 입력에 사용할 함수를 모아둔 파일
 */
object InputView {

    fun getStringInput(text: String): String {
        print(text)
        return ConsoleInput.consoleReadLine().trim()
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

    // ID 입력 시 유효성 검사
    fun getUserIdInput(): InputResult {
        val input = ConsoleInput.consoleReadLine().trim()
        return when {
            input == CommonConstants.GO_BACK_INPUT -> InputResult.GoBack(CommonConstants.GO_BACK_INPUT)
            input.isEmpty() -> InputResult.InputIsEmpty(CommonConstants.ERROR_INPUT_ID_IS_EMPTY)
            input.contains(" ") -> InputResult.InputContainsEmpty(CommonConstants.ERROR_INPUT_ID_CONTAINS_EMPTY)
            input.length < CommonConstants.MIN_ID_LENGTH -> InputResult.InputIsShort(CommonConstants.ERROR_INPUT_ID_LENGTH)
            else -> InputResult.Success(input)
        }
    }

    // PW 입력 시 유효성 검사
    fun getUserPwInput(): InputResult {
        val input = ConsoleInput.consoleReadLine().trim()
        return when {
            input == CommonConstants.GO_BACK_INPUT -> InputResult.GoBack(CommonConstants.GO_BACK_INPUT)
            input.isEmpty() -> InputResult.InputIsEmpty(CommonConstants.ERROR_INPUT_ID_IS_EMPTY)
            input.contains(" ") -> InputResult.InputContainsEmpty(CommonConstants.ERROR_INPUT_ID_CONTAINS_EMPTY)
            input.length < CommonConstants.MIN_PW_LENGTH -> InputResult.InputIsShort(CommonConstants.ERROR_INPUT_ID_LENGTH)
            else -> InputResult.Success(input)
        }
    }

}