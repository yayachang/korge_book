class Book(){
    var name = "用Kotlin手作出第一個跨平台小遊戲"
}
fun main() {
    var book:Book? = null
    println("書名:${book!!.name}")
}
