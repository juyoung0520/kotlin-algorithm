package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = arrayOf(1, 0, -1, 0)
private val dy = arrayOf(0, -1, 0, 1)
private val arr = Array(101) { BooleanArray(101) }

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    repeat(n) {
        val st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        val d = st.nextToken().toInt()
        val g = st.nextToken().toInt()

        drawLine(x, y, d, g)
    }

    var count = 0
    for (i in 0 until arr.size - 1) {
        for (j in 0 until arr.size - 1) {
            if (arr[i][j] && arr[i + 1][j] && arr[i][j + 1] && arr[i + 1][j + 1]) {
                count++
            }
        }
    }

    println(count)
}

private fun drawLine(x: Int, y: Int, d: Int, g: Int) {
    val directions = LinkedList<Int>()
    directions.add(d) // 0세대

    // 1 ~ 9 세대
    repeat(g) {
        for (i in directions.lastIndex downTo 0) {
            directions.add((directions[i] + 1) % 4)
        }
    }

    var xx = x
    var yy = y
    arr[yy][xx] = true

    for (dd in directions) {
        xx += dx[dd]
        yy += dy[dd]
        arr[yy][xx] = true
    }
}