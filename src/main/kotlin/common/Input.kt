package common

/**
 * 입력에 사용할 함수를 모아둔 파일
 */
fun getStringInput(text: String): String {
    print(text)
    return readlnOrNull()?.trim() ?: ""
}

fun getIntInput(text: String): Int? {
    val input = getStringInput(text)
    return input.toIntOrNull()
}

fun getOnlyNumInput() : Int {
    val numericRegex = "^[0-9]+$".toRegex()

    while (true) {
        val input = readln().trim()

        if (input.isEmpty()) {
            print("에러 : 아무것도 입력되지 않았습니다. 숫자를 입력해주세요 : ")
            continue
        }

        if (input.matches(numericRegex)) {
            return input.toInt()
        } else {
            print("에러 : 숫자만 입력 가능합니다. 다시 입력해주세요 : ")
        }
    }
}

fun inputZeroToMax(maxInput: Int): Int {
    var input = getOnlyNumInput()
    while (input !in 0..maxInput) {
        print("0 ~ ${maxInput}까지의 숫자를 입력해주세요 : ")
        input = getOnlyNumInput()
    }
    return input
}

fun inputOneToMax(maxInput: Int): Int {
    var input = getOnlyNumInput()
    while (input !in 1..maxInput) {
        print("1 ~ ${maxInput}까지의 숫자를 입력해주세요 : ")
        input = getOnlyNumInput()
    }
    return input
}


fun getUserIdInput(): String {
    var input = readln()
    while (true) {
        if (input.isEmpty()) {
            print("에러 : ID를 입력해주세요. ID 재입력 : ")
            input = readln()
        } else if (input.contains(" ")) {
            print("에러 : ID에 공백을 포함할 수 없습니다. ID 재입력 : ")
            input = readln()
        } else {
            break
        }
    }
    return input
}

fun getUserPwInput(): String {
    var input = readln()
    while (true) {
        if (input.isEmpty()) {
            print("에러 : PW를 입력해주세요. PW 재입력 : ")
            input = readln()
        } else if (input.contains(" ")) {
            print("에러 : PW에 공백을 포함할 수 없습니다. PW 재입력 : ")
            input = readln()
        } else if (input.length < 4) {
            print("에러 : PW는 4자리 이상이여야 합니다. PW 재입력 : ")
            input = readln()
        } else {
            break
        }
    }
    return input
}

fun printDivLine() {
    println("====================================================")
}