package `kotlin-algorithm`.`0x13_BinarySearch`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private lateinit var arr: Array<IntArray>
private lateinit var sum1: IntArray
private lateinit var sum2: IntArray

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    arr = Array(4) {
        IntArray(n)
    }

    var st: StringTokenizer
    for (i in 0 until n) {
        st = StringTokenizer(br.readLine())
        for (j in arr.indices) {
            arr[j][i] = st.nextToken().toInt()
        }
    }

    sum1 = IntArray(n * n)
    sum2 = IntArray(n * n)
    var idx = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            sum1[idx] = arr[0][i] + arr[1][j]
            sum2[idx++] = arr[2][i] + arr[3][j]
        }
    }

    sum1.sort() // 캐시 시간적 지연성 때문에 하는게 더 빠르다.
    sum2.sort()

//    var count = 0L
//    for (i in sum1.indices) {
//        count += (upperBound(-sum1[i]) - lowerBound(-sum1[i])).toLong() // 여러 개 일 수 있다.
//    }
//
//    println(count)
    println(getZeroCount())
}

private fun upperBound(find: Int): Int {
    var s = 0
    var e = sum2.size
    var mid = 0

    while (s < e) {
        mid = (s + e) / 2

        if (sum2[mid] <= find) {
            s = mid + 1
        } else {
            e = mid
        }
    }

    return s
}

private fun lowerBound(find: Int): Int {
    var s = 0
    var e = sum2.size
    var mid = 0

    while (s < e) {
        mid = (s + e) / 2

        if (sum2[mid] < find) {
            s = mid + 1
        } else {
            e = mid
        }
    }

    return s
}

// two pointer 방식, 여기서는 ab 배열, cd 배열 모두 sort 해야 됨
private fun getZeroCount(): Long {
    var p1 = 0
    var p2 = sum2.size - 1
    var count = 0L
    var sum = 0

    while (p1 < sum1.size && p2 >= 0) {
        sum = sum1[p1] + sum2[p2]
        when {
            sum == 0 -> {
                var c1 = 0L
                var find = sum1[p1]
                while (p1 < sum1.size && sum1[p1] == find) {
                    c1++
                    p1++
                }

                var c2 = 0L
                find = sum2[p2]
                while (p2 >= 0 && sum2[p2] == find) {
                    c2++
                    p2--
                }
                count += c1 * c2
            }
            sum < 0 -> {
                p1++
            }
            else -> {
                p2--
            }

        }
    }
    return count
}