class Book(var name:String, var author:String, var publisher:String, var pageCount:Int, var price:Int){
    
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