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
fun <T> getCol(array: List<List<T>>, col: Int): List<T> {
    val rows = array.size
    val column = mutableListOf<T>()
    (0 until rows).forEach {
        column.add(array[it][col])
    }
    return column
}