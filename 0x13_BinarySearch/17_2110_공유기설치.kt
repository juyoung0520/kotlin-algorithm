package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var houses: IntArray
private var c = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    c = st.nextToken().toInt()
    houses = IntArray(n) {
        br.readLine().toInt()
    }

    houses.sort()

    var s = 1
    var e = houses[n - 1]
    var mid = 0

    while (s <= e) {
        mid = (s + e) / 2

        val count = countHouse(mid)
        if (count >= c) {
            s = mid + 1
        } else {
            e = mid - 1
        }
    }

    println(s - 1)
}

private fun countHouse(minDis: Int): Int {
    var count = 1
    var last = 0
    for (i in 1 until houses.size) {
        if (houses[i] - houses[last] >= minDis) {
            count++
            last = i
        }
    }

    return count
}