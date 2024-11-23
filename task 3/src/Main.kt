import java.util.*

val alphabet = arrayOf('а',  'б',  'в',  'г',  'д',  'е',  'ё',  'ж',  'з',  'и',  'й',  'к',  'л',  'м',  'н',  'о',  'п',  'р',  'с',  'т',  'у',  'ф',  'х',  'ц',  'ч',  'ш',  'щ',  'ъ', 'ы', 'ь',  'э',  'ю',  'я')

fun main(args: Array<String>) {
    val table = initTable()
    for (shifted in table.values) {
        for (letter in shifted.values) {
            print("$letter \t")
        }
        println()
    }
    println()
    fun printTable() {
        for (shifted in table.values) {
            for (letter in shifted.values) {
                print("$letter \t")
            }
            println()
        }
        println()
    }

    fun encrypt() {
        var phrase: String
        while (true) {
            print("Текст -> ")
            phrase = readln().lowercase(Locale.getDefault())
            if (phrase.any { c -> !alphabet.contains(c) })
                println("Неверный ввод")
            else break
        }

        var keyword: String
        while (true) {
            print("Введите ключ -> ")
            keyword = readln().lowercase(Locale.getDefault())
            if (keyword.any { c -> !alphabet.contains(c) })
                println("Неверный ввод")
            else break
        }

        var keywordLong = ""
        var result = ""
        for (i in phrase.indices) {
            keywordLong += keyword[i % keyword.count()]
            result += table[keywordLong[i]]!![phrase[i]]
        }

        println("Текст: $phrase")
        println("Ключ: $keywordLong")
        println("Итог: $result")
    }

    fun decrypt() {
        var phraseEncrypted: String
        while (true) {
            print("Текст -> ")
            phraseEncrypted = readln().lowercase(Locale.getDefault())
            if (phraseEncrypted.any { c -> !alphabet.contains(c) })
                println("Неверный ввод")
            else break
        }

        var keyword: String
        while (true) {
            print("Введите ключ -> ")
            keyword = readln().lowercase(Locale.getDefault())
            if (keyword.any { c -> !alphabet.contains(c) })
                println("Неверный ввод")
            else break
        }

        var keywordLong = ""
        var result = ""
        for (i in phraseEncrypted.indices) {
            keywordLong += keyword[i % keyword.count()]
            result += table.keys.first { c -> table[c]!![keywordLong[i]] == phraseEncrypted[i] }
        }

        println("Текст: $phraseEncrypted")
        println("Ключ: $keywordLong")
        println("Итог: $result")
    }

    while (true) {
        var selected: Int?
        while (true) {
            print("Введите 1 для защифровки и 2 для расшифровки -> ")
            selected = readln().toIntOrNull()
            if (selected == null)
                println("Введите число")
            else break
        }
        when (selected) {
            1 -> encrypt()
            2 -> decrypt()
            else -> println("Неверное число")
        }
        println()
    }
}

fun initTable(): MutableMap<Char, MutableMap<Char, Char>> {
    val table = mutableMapOf<Char, MutableMap<Char, Char>>()
    for (i in alphabet.indices) {
        val letter = alphabet[i]
        table[letter] = mutableMapOf()
        for (j in alphabet.indices) {
            table[letter]!![alphabet[j]] = alphabet[(i + j) % alphabet.count()]
        }
    }
    return table;
}