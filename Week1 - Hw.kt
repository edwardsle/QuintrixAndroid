// 1. class DecimalsClass
class DecimalsClass(d: Double){
    private val dVal: Double
    init {
        dVal = d
    }

    override fun toString(): String {
        return dVal.toString()
    }
}

// 2. class PlanesTrainsAutomobiles
class PlanesTrainsAutomobiles(
    var timeToDestination: Int,
    var steveMartinIrritationIndex: Int
){
    fun incrementsTimeToDestination(){
        timeToDestination += 1
    }

    fun incrementsSteveMartinIrritationIndex(){
        val randomNum = (1..3).random()
        steveMartinIrritationIndex += randomNum
    }
}

// 3. class RewardMiles
class RewardMiles(
	val nameOfCustomer: String,
	val miles: Int
) {
    /*TODO*/
    override fun toString(): String {
        return "RewardMiles(nameOfCustomer='$nameOfCustomer', miles=$miles)"
    }
}

// 5. class Customer (Map)
class Customer(
    val checking: Int,
    val saving: Int,
    val ira: Int
) {
    private val account = mapOf(
        "Checking" to checking,
        "Saving" to saving,
        "IRA" to ira
    )
    
    fun totalNetWorth(): Int = checking + saving + ira
}

fun main(args: Array<String>) {
    // 1. class DecimalsClass
    val test1 = DecimalsClass(2.5)
    println(test1.toString())

    // 2. class PlanesTrainsAutomobiles
    val test2 = PlanesTrainsAutomobiles(1, 1)
    test2.incrementsTimeToDestination()
    test2.incrementsSteveMartinIrritationIndex()
    println(test2.timeToDestination)
    println(test2.steveMartinIrritationIndex)
    
    // 3. class RewardMiles
    val customer = RewardMiles("Thomas", 10000)
	println(customer)
    
    // 4. string list
    var myList = mutableListOf<String>()
    myList.add("12")
    myList.add("1.2")
    myList.add("1,2")
    myList.add("1.2e0")
    myList.add("1.2e1")
    myList.add("1.2e2")
    myList.add("1.2e3")
    myList.add("1.2e10")
    myList.add("12.3e10")
    myList.add("1.2e-1")
    myList.add("1.2e-10")
    
    println(myList)
    
    // convert myList to double
    myList.forEach{
        try {
    		println(it.toDouble())
        } catch (e: NumberFormatException) {
            println("Couldn't convert to double")
        }
        
    }    
    
    // 5. class Customer (Map)
    val tom = Customer(13266, 5206, 7448)
    val harry = Customer(17820, 10548, 1472)
    val sally = Customer(14210, 7672, 12808)
    
    println("Total net worth of Tom is " + tom.totalNetWorth())
    println("Total net worth of Harry is " + harry.totalNetWorth())
    println("Total net worth of Sally is " + sally.totalNetWorth())
}