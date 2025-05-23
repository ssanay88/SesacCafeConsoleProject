package signup

fun printEnterNewIdUI() {
    println("[ 회원가입 페이지 ]")
    print("사용할 ID 입력 : ")
}

fun printEnterIdIsTaken() = print("이미 사용중인 ID 입니다. 다른 ID를 입력해주세요 : ")

fun printEnterNewPwUI() = print("사용할 PW 입력 : ")

fun printEnterPwRepeatUI() = print("PW 확인을 위해 한번 더 입력해주세요 : ")

fun printFailToCheckPwUI() = print("재입력하신 PW가 다릅니다. 재입력 부탁드립니다 : ")