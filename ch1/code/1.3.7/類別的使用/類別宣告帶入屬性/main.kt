fun main() {
    //建立兩本書
    val book1 = Book("用Kotlin做出第一個跨平台小遊戲", "Yaya Chang", "博碩出版社", 500, 600)
    val book2 = Book("用Kotlin做出好幾個小遊戲", "鴨鴨助教", "鴨鴨出版社", 666, 650)

    //印出書本資訊
    book1.printInfo()
    book2.printInfo()
}
