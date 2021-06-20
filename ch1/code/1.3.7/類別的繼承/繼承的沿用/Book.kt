open class Book(){
    open var name="用Kotlin做出第一個跨平台小遊戲"
    var price = 600
    open fun calculatePrice():Int{
        return (price*0.9).toInt()
    }

}
