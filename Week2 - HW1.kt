// 1. class Customer
private var customerCount = 0

class Customer(
    var name: String,
    var age: Int
) {
    private val customerId: Int
    init {
        customerCount++
        customerId = customerCount
    }

    fun getData(): Pair<Int, String> {
        return Pair(customerId, name)
    }
}

fun printCustomers(num: Int, customers: List<Customer>) {
    for (i in 0 until num) {
        println(customers[i].getData())
    }
}

fun main() {
    // 1. class Customer
    val names = listOf(
        Customer("Amy", 20),
        Customer("Brad",20),
        Customer("Cathy", 20),
        Customer("Diego", 20),
        Customer("Elle", 20),
        Customer("Frances", 20),
        Customer("Gustavo", 20),
        Customer("Hendrick", 20),
        Customer("Ismail", 20),
        Customer("John", 20)
    )
    printCustomers(3, names)

    // 2. Flatten list of lists
    val listCompanies = listOf(
        listOf("Walmart", 102.32),
        listOf("Costco", 431.02),
        listOf("Kroger", 43.23),
        listOf("Macys", 321.32)
    )
    println(listCompanies.flatten())

    // 3. zipWithNext()
    val listPeople = listOf("Sam", "Tim", "Usher", "Virgil")
    val pairs = listPeople.zipWithNext()
    println(pairs)

    // 4. List of even numbers
    val numbers = (0..20).toList()
    val even = numbers.filter {it % 2 == 0}
    println(even)

    // 5. List of 10 numbers that contains the multiples of 13
    val multiplesOf13 = List(10) {(it+1)*13 }
    println(multiplesOf13)
}