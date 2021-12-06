package day6

import util.getInputAsText

fun solve1(): Long = evolveRounds(80)
fun solve2(): Long = evolveRounds(256)
fun evolveRounds(evolutions: Int): Long {
    val input = getInputAsText(6).split(",").map(String::toInt)
    val fishCount = LongArray(9)
    input.forEach {fishCount[it] = fishCount[it] + 1}
    repeat(evolutions) {
        val toBeEvolved = fishCount[0]
        for (i in 1 until fishCount.size) {
            fishCount[i - 1] = fishCount[i]
        }
        fishCount[6] += toBeEvolved
        fishCount[8] = toBeEvolved
    }
    return fishCount.sumOf { it }
}