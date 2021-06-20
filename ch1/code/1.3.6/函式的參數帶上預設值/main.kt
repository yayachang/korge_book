fun main() {
    calculatePrice (100)
}

fun calculatePrice(price:Int, discount:Double=0.9){
    println("價錢為:${price*discount}")
}

