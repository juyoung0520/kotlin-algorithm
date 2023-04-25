package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private lateinit var arr: IntArray
private lateinit var visited: BooleanArray
private lateinit var score: Array<IntArray>
private var max = Int.MIN_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    score = Array(n) {
        val st = StringTokenizer(br.readLine())
        IntArray(9) {
            st.nextToken().toInt()
        }
    }

    arr = IntArray(9)
    visited = BooleanArray(9)
    arr[3] = 0
    visited[0] = true

    dfs(0)
    println(max)
}

private fun dfs(idx: Int) {
    if (idx == 3) {
        dfs(idx + 1)
        return
    }
    if (idx == arr.size) {
        var i = 0
        var s = 0
        repeat(score.size) {
            var tmp = 0
            var outCount = 0
            val rou = BooleanArray(3) // 1루, 2루, 3루가 비었는지
            while (outCount < 3) {
                val hit = score[it][arr[i % arr.size]]
                if (hit == 0) {
                    outCount++
                    i++
                    continue
                }
                // home 통과하는 것 점수 추가
                for (j in rou.lastIndex downTo rou.size - hit) {
                    if (j == -1) tmp++
                    else if (rou[j]) tmp++
                }
                // 루 이동
                for (j in rou.lastIndex downTo hit) {
                    rou[j] = rou[j - hit]
                }
                // 타자 이동
                if (hit - 1 in rou.indices) {
                    rou[hit - 1] = true
                }
                // 타자 이전 루는 모두 비었음
                for (j in hit - 2 downTo 0) {
                    rou[j] = false
                }
                i++
            }
            s += tmp
        }
        max = max.coerceAtLeast(s)
        return
    }

    for (i in arr.indices) {
        if (visited[i].not()) {
            visited[i] = true
            arr[idx] = i
            dfs(idx + 1)
            visited[i] = false
        }
    }
}