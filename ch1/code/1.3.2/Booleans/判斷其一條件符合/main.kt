fun main() {
    val hasIdentityCard = true
    val hasHealthInsuranceCard = false
    
    val isValidUser = hasIdentityCard || hasHealthInsuranceCard
    println("isValidUser:${ isValidUser }")
}
