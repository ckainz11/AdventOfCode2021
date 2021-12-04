package day4

import util.getInputAsText

val input = getInputAsText(4).lines().filter { it.isNotBlank() }
val draws = input.take(1)[0].split(",").map(String::toInt)
val boards =
    input.drop(1).chunked(5).map { it -> BingoBoard(it.map { it.split(Regex(" +")).filter { s -> s.isNotBlank() } }) }


fun solve1(): Int {
    val boards1 = boards.toList()
    for (draw in draws) {
        for (board in boards1) {
            board.mark(draw, true)
            board.lastNum = draw
            if (board.checkWin())
                return board.getScore()
        }
    }
    return 0
}

fun solve2(): Int {
    val boards2 = boards.toMutableList()
    var lastWinningBoard = boards[0]
    var lastWinningNum = 0
    for ((index, draw) in draws.withIndex()) {
        val winningBoards = mutableListOf<BingoBoard>()
        for (board in boards2) {
            board.mark(draw, true)
            if (board.checkWin()) {
                winningBoards.add(board)
            }
        }
        boards2.removeAll(winningBoards)
        if (boards2.size == 1) {
            lastWinningNum = index
            lastWinningBoard = boards2[0]
            break
        }
    }
    for(i in lastWinningNum until draws.size) {
        val draw = draws[i]
        lastWinningBoard.mark(draw, true)
        if(lastWinningBoard.checkWin()){
            lastWinningBoard.lastNum = draw
            return lastWinningBoard.getScore()
        }
    }
    return 0
}