package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/*
O(h * log n)
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val h = st.nextToken().toInt()
    val top = IntArray(n / 2)
    val bottom = IntArray(n / 2)

    repeat(n) {
        val num = br.readLine().toInt()
        if(it % 2 == 0) {
            bottom[it / 2] = num
        } else {
            top[it / 2] = num
        }
    }

    top.sort()
    bottom.sort()

    var count = Int.MAX_VALUE
    var ans = 0

    for (i in 1 .. h) {
        val c= binarySearch(i, bottom) + binarySearch(h - i + 1, top)
        if (c < count) {
            count = c
            ans = 1
        } else if (c == count) {
            ans++
        }
    }

    println("$count $ans")
}

private fun binarySearch(height: Int, arr: IntArray): Int {
    var end = arr.size - 1
    var start = 0

    while (start <= end) {
        val mid = (start + end) / 2

        if (arr[mid] >= height) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }

    return arr.size - start
}