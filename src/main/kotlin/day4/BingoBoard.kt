package day4

import util.getCol

class BingoBoard(val init: List<List<String>>) {
    var board = List(5) { index -> init[index].map { Field(it.toInt(), false) } }
    var lastNum = -1
    fun mark(num: Int, flag: Boolean) {
        for(row in board) {
            for(it in row) {
                if(it.num == num)
                    it.marked = flag
            }
        }
    }
    fun assignRow(row: Array<Int> ,rowId: Int) {
        board[rowId].mapIndexed {i, field -> field.num = row[i] }
    }
    fun checkWin(): Boolean {
        for(i in board.indices) {
            if(board[i].filter { it.marked }.size == 5 || getCol(board, i).filter { it.marked }.size == 5)
                return true
        }
        return false
    }
    private fun getUnmarkedSum(): Int = board.fold(0) {acc, list -> acc + list.sumOf { if(it.marked) 0 else it.num } }
    fun getScore(): Int = getUnmarkedSum() * lastNum
}
data class Field(var num: Int, var marked: Boolean)