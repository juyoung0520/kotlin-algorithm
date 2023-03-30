package `kotlin-algorithm`.`0x12_Math`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val h = st.nextToken().toInt()
    val w = st.nextToken().toInt()
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val hor = if (w % (m + 1) == 0) w / (m + 1) else w / (m + 1) + 1
    val ver = if (h % (n + 1) == 0) h / (n + 1) else h / (n + 1) + 1

    println(hor * ver)
}