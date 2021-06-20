fun main() {
    val book = Book()

    val eBook = EBook()
    eBook.name = "用Kotlin做出第一個跨平台小遊戲(電子版)"

    val audioBook = AudioBook()
    audioBook.name = "用Kotlin做出第一個跨平台小遊戲(有聲書)"

    //印出書本資訊
    book.printInfo()
    eBook.printInfo()
    audioBook.printInfo()
}