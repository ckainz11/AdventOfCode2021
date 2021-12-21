package day21

import util.getInputAsText
import java.math.BigInteger

var currentDiceVal = 1
var diceCount = 0
val poss = generatePoss()
fun solve2(): Long {
    val (field1, field2) = getInputAsText(21).lines().map { it.last().toString().toInt() }
    return playRecursiveGame(field1, field2)
}
fun solve1(): Int {
    var (field1, field2) = getInputAsText(21).lines().map { it.last().toString().toInt() }
    var p1score = 0
    var p2score = 0
    var player1 = true
    while (p1score < 1000 && p2score < 1000) {
        val temp = rollDeterministic() + rollDeterministic() + rollDeterministic()
        if (player1) {
            var newField = (field1 + temp).toString().last().toString().toInt()
            if (newField == 0) newField = 10
            p1score += newField
            field1 = newField
        } else {
            var newField = (field2 + temp).toString().last().toString().toInt()
            if (newField == 0) newField = 10
            p2score += newField
            field2 = newField
        }
        player1 = !player1
    }

    return if (p1score < 1000) p1score * diceCount
    else return p2score * diceCount
}
fun playRecursiveGame(startP1: Int, startP2: Int): Long {

    val possible3Rolls = poss.map { it.sum() }.groupingBy { it }.eachCount()
    fun play(pos: List<Int>, scores: List<Int>, turn: Int): Pair<BigInteger, BigInteger> {
        return possible3Rolls.map { (roll, count) ->
            val newPos = pos.toMutableList()
            val newScores = scores.toMutableList()

            newPos[turn] = (pos[turn] + roll) % 10
            newScores[turn] = scores[turn] + newPos[turn] + 1

            if (newScores[turn] >= 21) {
                if (turn == 0)
                    count.toBigInteger() to BigInteger.ZERO
                else
                    BigInteger.ZERO to count.toBigInteger()
            } else {
                val nextResult = play(newPos, newScores, (turn + 1) % 2)
                nextResult.first * count.toBigInteger() to nextResult.second * count.toBigInteger()
            }
        }.reduce { acc, p -> acc.first + p.first to acc.second + p.second }
    }

    val result = play(listOf(startP1 - 1, startP2 - 1), listOf(0, 0), 0)
    return maxOf(result.first, result.second).toLong()
}
private fun generatePoss(): List<List<Int>> {
    val poss = mutableListOf<List<Int>>()
    for(i in 1..3){
        for(j in 1..3){
            for(k in 1..3){
                poss.add(listOf(i,j,k))
            }
        }
    }
    return poss
}
private fun rollDeterministic(): Int {
    val current = currentDiceVal
    currentDiceVal++
    if (currentDiceVal > 100) currentDiceVal = 1
    diceCount++
    return current
}