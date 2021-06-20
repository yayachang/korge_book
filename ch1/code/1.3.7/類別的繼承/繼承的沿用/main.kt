fun main() {
    val book = Book()
    val eBook = EBook()

    //印出書名
    println("一般書書名:${book.name}")
    println("電子書書名:${eBook.name}")
    //印出價錢
    println("一般書價錢:${book.calculatePrice()}")
    println("電子書價錢:${eBook.calculatePrice()}")
}
