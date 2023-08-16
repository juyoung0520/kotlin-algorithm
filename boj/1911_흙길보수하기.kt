package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
    웅덩이 시작점에 맞춰 널빤지 붙이는게 최적
    마지막 널빤지 붙인 곳이 다음 웅덩이에 겹치면 그 다음부터 이어붙이기
    안겹치면 다음 시작점부터 다시 널빤지 붙이기
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val l = st.nextToken().toInt()

    val lines = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(2) {
            st.nextToken().toInt()
        }
    }

    lines.sortBy { it[0] }

    var count = 0
    var tmp = 0
    for (line in lines) {
        if (tmp < line[0]) {
            tmp = line[0]
        }
        while (tmp < line[1]) {
            tmp += l
            count++
        }
    }

//    for (line in lines) {
//        if (tmp >= line[1]) continue // tmp가 끝점을 넘는지 확인
//        if (tmp < line[0]) {
//            tmp = line[0]
//        }
//        val len = line[1] - tmp
//        val plus = len / l
//        tmp += plus * l
//        count += plus
//        if (len % l != 0) {
//            tmp += l
//            count++
//        }
//    }

    println(count)
}