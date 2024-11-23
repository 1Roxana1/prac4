fun main(args: Array<String>) {
    val variants = arrayOf("камень", "ножницы", "бумага")

    for (i in variants.indices) {
        println("${i+1} - ${variants[i]}")
    }
    var move: Int?
    while (true) {
        print("Введите номер -> ")
        move = readln().toIntOrNull()
        if (move == null || move-1 >= variants.count()) {
            println("Неправильное значение")
        } else break
    }
    val userChoice = variants[move!!-1]
    val compChoice = variants.random()
    println("Игрок выбрал $userChoice")
    println("Компьютер выбрал $compChoice")
    println(process(userChoice, compChoice))
}

fun process(userChoice: String, compChoice: String) =
    when (userChoice) {
        "камень" -> when (compChoice) {
            "камень" -> "Ничья"
            "ножницы" -> "Игрок победил"
            "бумага" -> "Компьютер победил"
            else -> "что-то не так"
        }
        "ножницы" -> when (compChoice) {
            "камень" -> "Компьютер победил"
            "ножницы" -> "Ничья"
            "бумага" -> "Игрок победил"
            else -> "что-то не так"
        }
        "бумага" -> when (compChoice) {
            "камень" -> "Игрок победил"
            "ножницы" -> "Компьютер победил"
            "бумага" -> "Ничья"
            else -> "что-то не так"
        }
        else -> "что-то не так"
    }