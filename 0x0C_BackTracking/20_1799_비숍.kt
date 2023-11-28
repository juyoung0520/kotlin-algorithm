package `kotlin-algorithm`.`0x0C_BackTracking`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var arr: Array<IntArray>
private val dx = intArrayOf(-1, 1, 1, -1)
private val dy = intArrayOf(1, 1, -1, -1)
private var max = 0
private var n = 0
private var nLen = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    n = br.readLine().toInt()
    nLen = n * n
    arr = Array(n) {
        val st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }

    perm(0, 0)
    var res = max
    max = 0
    perm(1, 0)
    res += max

    println(res)
}

private fun perm(idx: Int, count: Int) {
    if (idx >= nLen) {
        if (count > max) {
            max = count
        }
        return
    }

    val x = idx / n
    val y = idx % n
    var tmp = 2
    if (n % 2 == 0) {
        if (y == n - 1) tmp--
        else if (y == n - 2) tmp++
    }

    if (arr[x][y] == 0) {
        perm(idx + tmp, count)
        return
    }

    if (isValid(x, y)) {
        arr[x][y] = 2
        perm(idx + tmp, count + 1)
        arr[x][y] = 1
    }
    perm(idx + tmp, count)
}

private fun isValid(x: Int, y: Int): Boolean {
    for (i in dx.indices) {
        var nx = x
        var ny = y

        while (true) {
            nx += dx[i]
            ny += dy[i]
            if (nx !in arr.indices || ny !in arr[0].indices) break
            if (arr[nx][ny] == 2) return false
        }
    }
    return true
}