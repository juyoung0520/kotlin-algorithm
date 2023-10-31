package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

private lateinit var arr: Array<IntArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    arr = Array(n) {
        val st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }

    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (arr[i][k] == 1 && arr[k][j] == 1) {
                    arr[i][j] = 1
                }
            }
        }
    }

    val sb = StringBuilder()
    for (i in arr.indices) {
        for (j in arr[0].indices) {
            sb.append("${arr[i][j]} ")
        }
        sb.appendLine()
    }

    println(sb.toString())
}
