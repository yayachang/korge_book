class EBook:Book(){
    override var name ="${super.name}(電子版)"
    override fun calculatePrice():Int{
        return (super.calculatePrice()*0.9).toInt()
    }
}
