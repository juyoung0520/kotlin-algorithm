package `kotlin-algorithm`.programmers

private lateinit var graph: Array<ArrayList<Int>>
private lateinit var arr: IntArray
private var max = 0

private fun solution(info: IntArray, edges: Array<IntArray>): Int {
    arr = info
    graph = Array(info.size) {
        ArrayList()
    }

    for (e in edges) {
        graph[e[0]].add(e[1])
    }

    dfs(0, 0, 0, arrayListOf(0))

    return max
}

private fun dfs(start: Int, s: Int, w: Int, list: ArrayList<Int>) {
    var ns = s
    var nw = w

    if (arr[start] == 0) {
        ns++
    } else {
        nw++
    }

    if (nw >= ns) {
        return
    }

    if (max < ns) {
        max = ns
    }

    val nextList = ArrayList(list)
    nextList.addAll(graph[start])
    nextList.remove(start)

    for (next in nextList) {
        dfs(next, ns, nw, nextList)
    }
}