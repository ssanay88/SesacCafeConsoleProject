package login


fun printEnterIdUI() {
    println("[ 로그인 페이지 ]")
    print("ID 입력 : ")
}

fun printIdNotExistUI() = print("ID가 존재하지 않습니다. 재입력 해주세요 : ")

fun printEnterPwUI() = print("PW 입력 : ")

fun printPwNotCorrectUI() = print("비밀번호가 틀렸습니다. 재입력 해주세요 : ")

fun printLoginSuccessMessage() = println("로그인에 성공했습니다!")