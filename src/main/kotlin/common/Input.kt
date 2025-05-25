package common

fun getStringInput(text: String): String {
    print(text)
    return readlnOrNull()?.trim() ?: ""
}

fun getIntInput(text: String): Int? {
    val input = getStringInput(text)
    return input.toIntOrNull()
}

fun inputZeroToMax(maxInput: Int): Int {
    var input = readln().toInt()
    while (input !in 0..maxInput) {
        print("0 ~ ${maxInput}까지의 숫자를 입력해주세요 : ")
        input = readln().toInt()
    }
    return input
}

fun inputOneToMax(maxInput: Int): Int {
    var input = readln().toInt()
    while (input !in 1..maxInput) {
        print("1 ~ ${maxInput}까지의 숫자를 입력해주세요 : ")
        input = readln().toInt()
    }
    return input
}
