fun main() {
    val toatalPrice = calculatePrice (price=500, discount=0.8) + calculatePrice (price=400, discount=0.8)
    println("價錢為:${ toatalPrice }") //加總兩本不同價錢的書
}

fun calculatePrice(price:Int, discount:Double=0.9):Int{
    return (price*discount).toInt()
}
