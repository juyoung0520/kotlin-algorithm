package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

private var str1 = ""
private var str2 = ""
private lateinit var dp: Array<IntArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    str1 = br.readLine()
    str2 = br.readLine()
    dp = Array(str1.length + 1) {
        IntArray(str2.length + 1) {
            -1
        }
    }

    // top-down
    println(lcs(str1.length, str2.length))

    // bottom-up
//    for (i in 1 until dp.size) {
//        for (j in 1 until dp[0].size) {
//            if (str1[i - 1] == str2[j - 1]) {
//                dp[i][j] = dp[i - 1][j - 1] + 1
//            } else {
//                dp[i][j] = dp[i - 1][j].coerceAtLeast(dp[i][j - 1])
//            }
//        }
//    }
//
//    println(dp[str1.length][str2.length])
}

private fun lcs(x: Int, y: Int): Int {
    if (x == 0 || y == 0) {
        return 0
    }

    if (dp[x][y] == -1) {
        dp[x][y] = 0
        if (str1[x - 1] == str2[y - 1]) {
            dp[x][y] = lcs(x - 1, y - 1) + 1
        } else {
            dp[x][y] = lcs(x - 1, y).coerceAtLeast(lcs(x, y - 1))
        }
    }

    return dp[x][y]
}