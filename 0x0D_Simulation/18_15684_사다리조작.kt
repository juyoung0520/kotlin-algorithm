package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private lateinit var arr: Array<IntArray>
private var n = 0
private var m = 0
private var h = 0
private var min = -1

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    h = st.nextToken().toInt()
    arr = Array(h + 2) { IntArray(n + 2) }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt() // a 번째 줄
        val b = st.nextToken().toInt() // 시작점 b
        arr[a][b] = 1
    }

    for (i in 0 .. 3) {
        dfs(0, i)
        if (min != -1) {
            println(min)
            return
        }
    }

    println(-1)
}

private fun dfs(idx: Int, max: Int) {
    if (idx == max) {
        if (isSatisfyLadder()) {
            min = max
        }
        return
    }

    for (j in 1 .. n) {
        var i = 1
        while (i <= h) {
            if (arr[i][j] == 1 || arr[i][j - 1] == 1 || arr[i][j + 1] == 1) {
                i++
                continue
            }
            arr[i][j] = 1
            dfs(idx + 1, max)
            arr[i][j] = 0
            // 없어도 되는데 시간이 2초나 준다??
            while (i <= h && arr[i][j - 1] == 0 && arr[i][j + 1] == 0) i++
        }
    }


}

private fun isSatisfyLadder(): Boolean {
    for (j in 1 .. n) {
        var col = j
        for (i in 1 .. h) {
            if (arr[i][col] == 1) col++ // 다음 열로 이동
            else if (arr[i][col - 1] == 1) col--
        }

        if (col != j) return false
    }

    return true
}