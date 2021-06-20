class EBook:Book(){
    override var name = "用Kotlin做出第一個跨平台小遊戲(電子版)"
    override fun calculatePrice():Int{
        return (price*0.8).toInt()
    }
}
