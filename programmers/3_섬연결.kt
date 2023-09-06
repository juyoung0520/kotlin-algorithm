package `kotlin-algorithm`.programmers

private lateinit var parent: IntArray

private fun findParent(node: Int): Int {
    // 반영하면서 부모 반환해야 부모 값이 변경됐을 때 자식에게도 반영될 수 있다. (공통 조상 아래 자식들 나열)
    return if (parent[node] == node) node else findParent(node).also {
        parent[node] = it
    }
}

private fun union(node1: Int, node2: Int) {
    val parent1 = findParent(node1)
    val parent2 = findParent(node2)

    if (parent1 < parent2) {
        parent[parent1] = parent1 // 부모의 값(부모) 변경해야 공통 조상 변경
    } else {
        parent[parent2] = parent2
    }
}

fun main() {
    val costs =
        arrayOf(intArrayOf(0, 1, 1), intArrayOf(0, 2, 2), intArrayOf(1, 2, 5), intArrayOf(1, 3, 1), intArrayOf(2, 3, 8))
    val n = 4

    var answer = 0
    parent = IntArray(n) { it }

    costs.sortBy { it[2] }

    for (i in costs.indices) {
        val s = costs[i][0]
        val e = costs[i][1]

        if (findParent(s) == findParent(e)) continue

        union(s, e)

        answer += costs[i][2]
    }

    println(answer)
}