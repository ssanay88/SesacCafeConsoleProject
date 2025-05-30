package view

import java.util.Scanner

class ConsoleInput {
    companion object{
        private lateinit var scanner: Scanner
        fun consoleReadLine() : String {
            if(!this::scanner.isInitialized){
                scanner = Scanner(System.`in`)
            }
            return scanner.nextLine()
        }
    }
}