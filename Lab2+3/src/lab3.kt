fun squareSum(list: List<Int?>): Int {
    var sum = 0
    for (number in list) {
        sum += (number ?: 0) * (number ?: 0)
    }
    return sum
}

fun main() {
    val numbers = listOf(1, 2, 3, null, 4)
    println(squareSum(numbers))
}