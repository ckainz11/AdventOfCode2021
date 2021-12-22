package day22

import util.getInputAsText
import kotlin.math.max
import kotlin.math.min

val originalRules = getInputAsText(22).lines().map {
    val split = it.split(" ")
    val ranges = split[1].split(",").map { s -> s.drop(2).split("..") }
    Rule(
        split[0] == "on",
        Cube(
            ranges[0][0].toLong(), ranges[0][1].toLong(),
            ranges[1][0].toLong(), ranges[1][1].toLong(),
            ranges[2][0].toLong(), ranges[2][1].toLong()
        )
    )
}

fun solve1(): Long {
    val boundary = Cube(-50, 50, -50, 50, -50, 50)
    val newRules = originalRules.filter { it.cube.intersects(boundary) != null }
    return markAllCubes(newRules)
}

fun solve2(): Long {
    return markAllCubes(originalRules)
}

private fun markAllCubes(rules: List<Rule>): Long {
    var areaCount = mutableMapOf<Cube, Long>()
    for (rule in rules) {
        val updateAreaCount = mutableMapOf<Cube, Long>()
        for (a in areaCount) {

            val intersected = rule.cube.intersects(a.key)
            if (intersected != null) {
                updateAreaCount[intersected] = updateAreaCount.getOrDefault(intersected, 0) - a.value
            }
        }
        if (rule.on) {
            updateAreaCount[rule.cube] = updateAreaCount.getOrDefault(rule.cube, 0) + 1
        }
        areaCount = updateCount(areaCount, updateAreaCount)
    }
    return areaCount.map { it.key.volume * it.value }.sum()
}

private fun updateCount(origin: Map<Cube, Long>, update: Map<Cube, Long>): MutableMap<Cube, Long> {
    val new = origin.toMutableMap()
    for (entry in update) {
        val current = new[entry.key]
        if (current == null)
            new[entry.key] = entry.value
        else
            new[entry.key] = current + entry.value
    }
    return new
}

data class Rule(val on: Boolean, val cube: Cube)
data class Cube(val xLow: Long, val xHigh: Long, val yLow: Long, val yHigh: Long, val zLow: Long, val zHigh: Long) {
    val volume = ((xHigh - xLow + 1) * (yHigh - yLow + 1) * (zHigh - zLow + 1))

    fun intersects(other: Cube): Cube? {
        val x0 = max(this.xLow, other.xLow)
        val x1 = min(this.xHigh, other.xHigh)
        val y0 = max(this.yLow, other.yLow)
        val y1 = min(this.yHigh, other.yHigh)
        val z0 = max(this.zLow, other.zLow)
        val z1 = min(this.zHigh, other.zHigh)
        return if (x0 <= x1 && y0 <= y1 && z0 <= z1) Cube(x0, x1, y0, y1, z0, z1)
        else null
    }
}