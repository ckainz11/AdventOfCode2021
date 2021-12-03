package util

fun transpose(matrix: List<String>): List<String> {
    val col = matrix[0].length
    val row = matrix.size
    val transpose = MutableList(col) { MutableList(row) {' '} }
    for (i in 0..row - 1) {
        for (j in 0..col - 1) {
            transpose[j][i] = matrix[i][j]
        }
    }
    return transpose.map { it -> it.joinToString("") }
}