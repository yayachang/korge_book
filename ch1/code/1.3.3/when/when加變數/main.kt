fun main() {
    var number = 0
    when(number){
        1, 3, 5, 7, 9->{
            println("我是奇數")
        }
        2, 4, 6, 8, 10->{
            println("我是偶數")
        }
        else->{
            println("我是非1到10的數字")
        }
    }
}
