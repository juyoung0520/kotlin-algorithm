package `kotlin-algorithm`.programmers

fun main() {
    val n = 5000
    println(recursive(n))
}

private fun recursive(n: Int): Int {
    if (n == 1) {
        return 1
    }
    return if (n % 2 == 0) {
        recursive(n / 2)
    } else {
        recursive(n / 2) + 1
    }
}