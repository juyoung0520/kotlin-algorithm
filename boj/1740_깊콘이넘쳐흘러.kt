package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.ceil

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = Array(n) { IntArray(2) }
    var st = StringTokenizer(br.readLine())
    repeat(n) {
        arr[it][0] = st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    repeat(n) {
        arr[it][1] = st.nextToken().toInt()
    }

    arr.sortWith(compareBy({ it[1] }, { it[0] })) // 사용 날짜 짧은 순으로 정렬, 같으면 유효 기간 짧은 순

    var prev = arr[0][1] // 처음 이모티콘 사용 날짜
    var tmp = -1
    var count = 0L

    for (i in arr.indices) {
        // 유효기간이 더 짧은 경우 연장 필요
        if (arr[i][0] < prev) {
            // 이 때 이전 구간의 유효기간과 현재 이모티콘의 사용 날짜 중 긴 것을 채택
            if (prev < arr[i][1]) {
                prev = arr[i][1]
            }
            // 갱신
            val cnt = ceil((prev - arr[i][0]) / 30.0).toInt()
            arr[i][0] += cnt * 30
            count += cnt
        }
        // 현재 구간의 최댓값 갱신
        if (tmp < arr[i][0]) {
            tmp = arr[i][0]
        }
        // 다음 구간 이동 전 최대값 저장
        if (i + 1 < n && arr[i][1] != arr[i + 1][1]) {
            prev = tmp
        }
    }

    println(count)
}