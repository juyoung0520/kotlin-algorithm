package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val arr = Array(n) { i ->
        IntArray(n) { j ->
            if (i == j)
                0
            else
                100
        }
    }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val n1 = st.nextToken().toInt() - 1
        val n2 = st.nextToken().toInt() - 1
        arr[n1][n2] = 1
        arr[n2][n1] = 1
    }

    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                val tmp = arr[i][k] + arr[k][j]
                if (arr[i][j] > tmp) {
                    arr[i][j] = tmp
                }
            }
        }
    }

    var min = Int.MAX_VALUE
    var res = -1
    for (i in 0 until n) {
        var sum = 0
        for (j in 0 until n) {
            sum += arr[i][j]
        }
        if (sum < min) {
            res = i
            min = sum
        }
    }

    println(res + 1)
}