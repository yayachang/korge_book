fun main() {
    printBookName()
    println("價錢為:${calculatePrice(price = 500, discount = 0.8)}")
}

fun printBookName() = println("用Kotlin做出第一個跨平台小遊戲")
fun calculatePrice(price: Int, discount: Double = 0.9) = (price * discount).toInt()