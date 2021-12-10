package day10

import java.util.ArrayDeque
import util.getInputAsText

val opening = setOf('(', '[', '{', '<')
val syntaxPoints = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137,
)
val completionPoints = mapOf(
    '(' to 1,
    '[' to 2,
    '{' to 3,
    '<' to 4,
)

fun solve1(): Int {
    var sum = 0
    for (line in getInputAsText(10).lines()) {
        val stack = ArrayDeque<Char>()
        for (c in line) {
            if (opening.contains(c))
                stack.push(c)
            else if (getClosing(stack.pop()) != c) {
                sum += syntaxPoints[c] ?: 0
                break
            }
        }
    }
    return sum
}
fun solve2(): Long {
    val sum = mutableListOf<Long>()
    outer@ for (line in getInputAsText(10).lines()) {
        val stack = ArrayDeque<Char>()
        for (c in line) {
            if (opening.contains(c))
                stack.push(c)
            else if (getClosing(stack.pop()) != c) continue@outer //skip corrupted lines
        }
        sum.add(calcScore(stack.toCharArray()))
    }
    return sum.sorted()[sum.size / 2]
}
private fun calcScore(completion: CharArray): Long = completion.fold(0) {acc, c ->  acc * 5 + (completionPoints[c] ?: 0) }
private fun getClosing(c: Char): Char =
    when (c) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        else -> '>'
    }
