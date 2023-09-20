package `kotlin-algorithm`.programmers

fun main() {
    val targets = arrayOf(intArrayOf(4,5), intArrayOf(4,8), intArrayOf(10,14), intArrayOf(11,13), intArrayOf(5,12), intArrayOf(3,7), intArrayOf(1,4))
    var answer: Int = 0

    targets.sortBy { it[1] } // 끝점으로만 정렬

    var end = - 1

    for (t in targets) {
        // 시작점이 현재 끝점보다 작으면 같이 요격 가능, 같거나 크면 따로 요격 이 때 끝점은 무조건 같거나 크므로 신경쓰지 않는다.
        if (t[0] >= end) {
            answer++
            end = t[1]
        }
    }

    print(answer)
}