fun main() {
    var test1:String  = "我不是空值"
    var test2:String? = null

    var result1 = test1 ?: "我是空值"
    var result2 = test2 ?: "我是空值"

    println("result1為$result1")
    println("result2為$result2")
}
