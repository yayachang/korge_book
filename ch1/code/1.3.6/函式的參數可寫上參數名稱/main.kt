fun main() {
    calculatePrice (price=500, discount=0.8)
}

fun calculatePrice(price:Int, discount:Double=0.9){
    println("價錢為:${price*discount}")
}
