package util

typealias Matrix<T> = List<List<T>>
fun <T> matrixOf(vararg rows: List<T>): Matrix<T> = List(rows.size) {i -> rows[i]}
fun <T> Matrix<T>.getColumn(col: Int): List<T> = getCol(this, col)
fun <T, R> Matrix<T>.mapMatrix(transform: (T) -> R): Matrix<R> = this.map { it.map(transform) }
fun <T> Matrix<T>.matrixToString(): String = this.joinToString("\n") { it.joinToString(", ") }
fun <T: Comparable<T>> Matrix<T>.matrixMax(): T = this.mapNotNull { it.maxOrNull() }.maxOrNull()!!
fun <T: Comparable<T>> Matrix<T>.matrixMin(): T = this.mapNotNull { it.minOrNull() }.minOrNull()!!
fun <T> Matrix<T>.getColNum(): Int = this[0].size
fun <T> Matrix<T>.getRowNum(): Int = this.size
fun <T> Matrix<T>.transposed(times: Int = 1): Matrix<T> = transposeMatrix(this, times)
fun <T> emptyMatrixOf(rows: Int, columns: Int, default: T) = MutableList(rows) {MutableList(columns) {default} }
fun <T> Matrix<T>.count(predicate: (T) -> Boolean) = this.sumOf { it.count(predicate) }
fun <T> Matrix<T>.getAdjacent(row: Int, col: Int): List<T> = this.getAdjacentCoordinates(row, col).map { it -> this[it.y][it.x] }
fun <T> Matrix<T>.getAdjacentCoordinates(row: Int, col: Int): List<Point> {
    val adjacent = mutableListOf<Point>()
    if(col != 0) adjacent.add(Point(col - 1, row))
    if(col != this.getColNum()-1) adjacent.add(Point(col + 1, row))
    if(row != 0) adjacent.add(Point(col, row - 1))
    if(row != this.getRowNum()-1) adjacent.add(Point(col, row + 1))
    return adjacent
}
fun <T> Matrix<T>.getSurroundingCoordinates(row: Int, col: Int): List<Point> {
    val adjacent = mutableListOf<Point>()
    if(col != 0) adjacent.add(Point(col - 1, row))
    if(col != this.getColNum()-1) adjacent.add(Point(col + 1, row))
    if(row != 0) adjacent.add(Point(col, row - 1))
    if(row != this.getRowNum()-1) adjacent.add(Point(col, row + 1))
    if(col != 0 && row != 0) adjacent.add(Point(col-1, row-1))
    if(col != 0 && row != this.getRowNum() - 1) adjacent.add(Point(col-1, row + 1))
    if(col != this.getColNum() - 1 && row != 0) adjacent.add(Point(col + 1, row - 1))
    if(col != this.getColNum() - 1 && row != this.getRowNum() - 1) adjacent.add(Point(col + 1, row + 1))
    return adjacent
}
data class Point(val x: Int, val y: Int)
data class Point3(val x: Int, val y: Int, val z: Int)

/*----- Helper Functions -----*/

private fun <T> transposeMatrix(matrix: Matrix<T>): Matrix<T> = List(matrix.getColNum()) { i -> matrix.getColumn(i) }
private fun <T> transposeMatrix(matrix: Matrix<T>, times: Int): Matrix<T> {
    var newMatrix = matrix
    repeat(times) {
        newMatrix = transposeMatrix(newMatrix)
    }
    return newMatrix
}
/*----- Old functions -----*/

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