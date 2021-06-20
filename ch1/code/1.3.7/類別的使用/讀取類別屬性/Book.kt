class Book {
    //書本的細節(類別屬性)
    var name: String = "用Kotlin做出第一個跨平台小遊戲"
    var author: String = "Yaya Chang"
    var publisher: String = "博碩出版社"
    var pageCount = 50
    var price = 600

    //書本的行為(類別函式)
    fun printInfo() {
        println("書名:${name}")
        println("作者:${author}")
        println("出版社:${publisher}")
        println("頁數:${pageCount}")
        println("價錢:${price}")
    }

    //計算書本的價錢(類別函式)
    fun calculatePrice(): Int {
        return price
    }
}