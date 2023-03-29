package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var arr: Array<IntArray>
private var l = 0
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    l = st.nextToken().toInt()
    arr = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }

    var answer = 0
    for (i in 0 until n) {
        // 가로
        if (checkHorizontal(i)) {
            answer++
        }
        // 세로
        if (checkVertical(i)) {
            answer++
        }
    }

    println(answer)
}

private fun checkHorizontal(i: Int): Boolean {
    val isIncline = BooleanArray(arr.size)

    for (j in 0 until  arr.size - 1) {
        val diff = arr[i][j + 1] - arr[i][j]
        if (diff > 1 || diff < -1) return false

        // 증가
        if (diff == 1) {
            if (j + 1 < l) return false

            for (k in j downTo j + 1 - l) {
                if (isIncline[k] || arr[i][k] != arr[i][j]) return false
            }

            for (k in j downTo j + 1 - l) {
                isIncline[k] = true
            }
        } else  if (diff == -1) {
            if (j + l >= arr.size) return false

            for (k in j + 1 .. j + l) {
                if (isIncline[k] || arr[i][k] != arr[i][j] - 1) return false
            }

            for (k in j + 1 .. j + l) {
                isIncline[k] = true
            }
        }
    }

    return true
}

private fun checkVertical(j: Int): Boolean {
    val isIncline = BooleanArray(arr.size)

    for (i in 0 until  arr.size - 1) {
        val diff = arr[i + 1][j] - arr[i][j]
        if (diff > 1 || diff < -1) return false

        // 증가
        if (diff == 1) {
            if (i + 1 < l) return false

            for (k in i downTo i + 1 - l) {
                if (isIncline[k] || arr[k][j] != arr[i][j]) return false
            }

            for (k in i downTo i + 1 - l) {
                isIncline[k] = true
            }
        } else  if (diff == -1) {
            if (i + l >= arr.size) return false

            for (k in i + 1 .. i + l) {
                if (isIncline[k] || arr[k][j] != arr[i][j] - 1) return false
            }

            for (k in i + 1 .. i + l) {
                isIncline[k] = true
            }
        }
    }

    return true
}

