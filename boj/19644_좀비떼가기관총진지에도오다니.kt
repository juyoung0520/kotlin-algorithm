package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val l = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val ml = st.nextToken().toInt()
    val mk = st.nextToken().toInt()
    var c = br.readLine().toInt()

    val zombie = IntArray(l + 1)
    for (i in 1 .. l) {
        zombie[i] = br.readLine().toInt()
    }

    val arr = IntArray(l + 1) // 누적합
    for (i in 1 .. l) {
        val last = 0.coerceAtLeast(i - ml)
        val prev = arr[i - 1] - arr[last] // 직전 값

        if (zombie[i] <= prev + mk) {
            arr[i] = arr[i - 1] + mk
        } else {
            if (c > 0) {
                c--
                arr[i] = arr[i - 1]
            } else {
                println("NO")
                return
            }
        }
    }

    println("YES")
}