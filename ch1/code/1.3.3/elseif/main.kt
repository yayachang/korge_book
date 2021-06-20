fun main() {
    var nowTime = 12
    if(nowTime >= 11 && nowTime <= 14){
        //早上營業時間
        println("現在是早上營業時間")
    }else if(nowTime >= 17 && nowTime<=21){
        //晚上營業時間
        println("現在是晚上營業時間")
    }else{
        //打烊時間
        println("現在是打烊時間")
    }
}
