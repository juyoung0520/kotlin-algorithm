package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val menM = ArrayList<Int>(n)
    val menP = ArrayList<Int>(n)
    val womenM = ArrayList<Int>(n)
    val womenP = ArrayList<Int>(n)

    var st = StringTokenizer(br.readLine())
    repeat(n) {
        val num = st.nextToken().toInt()
        if (num < 0) {
            menM.add(-num)
        } else {
            menP.add(num)
        }
    }
    st = StringTokenizer(br.readLine())
    repeat(n) {
        val num = st.nextToken().toInt()
        if (num < 0) {
            womenM.add(-num)
        } else {
            womenP.add(num)
        }
    }

    menM.sortDescending()
    menP.sortDescending()
    womenM.sortDescending()
    womenP.sortDescending()

    var count = 0
    var i = 0
    var j = 0
    while (i < menM.size && j < womenP.size) {
        if (menM[i] > womenP[j]) {
            count++
            i++
            j++
        } else {
            j++
        }
    }
    i = 0
    j = 0
    while (i < menP.size && j < womenM.size) {
        if (menP[i] < womenM[j]) {
            count++
            i++
            j++
        } else {
            i++
        }
    }
    println(count)
}