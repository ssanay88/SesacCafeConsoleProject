package main

fun printShowMainMenu() = print("""
    [ 메인 페이지 ]
    (1) 메뉴 페이지로 이동 / (2) 주문 페이지로 이동 / (3) 마이 페이지로 이동
    이동할 페이지를 선택해주세요 : 
""".trimIndent())

fun printShowMainMenuWithCart() = print("""
    [ 메인 페이지 ]
    (1) 메뉴 페이지로 이동 / (2) 주문 페이지로 이동 / (3) 마이 페이지로 이동 / (4) 장바구니 이동
    이동할 페이지를 선택해주세요 : 
""".trimIndent())