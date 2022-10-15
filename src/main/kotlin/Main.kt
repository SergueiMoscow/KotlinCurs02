const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLUE = "\u001B[34m"

class Alphabet {
    var alphabet: String ="" //= mutableListOf<Char>()
    var charRange= mutableMapOf<String, Array<Int>>()
    var userTypedSymbol: Char = '\u0000'

    fun init() {
        //en upper: 65 - 90
        //en lower: 97 - 122
        //ru upper: 1040, 1071
        //ru lower: 1072, 1103
        charRange.put("englishUpper", arrayOf(65, 90))
        charRange.put("englishLower", arrayOf(97, 122))
        charRange.put("russianUpper", arrayOf(1040, 1071))
        charRange.put("russianLower", arrayOf(1072, 1103))
    }

    fun fillSymbols(symbol: Char, value: Array<Int>) {
        if (symbol.code in value[0]..value[1]) {
            for (i in value[0]..value[1]) {
                alphabet += i.toChar()
            }
        }
    }

    fun checkSymbols() {
        println("Введённый символ: $userTypedSymbol, код ${userTypedSymbol.code}")
//        charRange.forEach { key, value -> fillSymbols(symbol, value)}
        charRange.filter{(key, value) -> userTypedSymbol.code in value[0]..value[1]}.forEach { key, value -> fillSymbols(userTypedSymbol, value)}
    }

    fun printAlphabet() = alphabet.forEach() {
        if (userTypedSymbol == it) print(ANSI_BLUE + "$it " + ANSI_RESET)
        else print("$it ")
    }
}
fun main(args: Array<String>) {
    var explanation="""
        Введите символ.
        При вводе русского или английского символа верхнего или нижнего регистра
        отобразится соответсвующий алфавит в соответсвующем регистре
    """.trimIndent()
    println(explanation)
    var userInput = readLine()
    if (userInput == null) {
        println("Пустая строка")
        return
    }
    var alphabet = Alphabet()
    alphabet.userTypedSymbol = userInput.first()
    alphabet.init()
    alphabet.checkSymbols()
    alphabet.printAlphabet()
}