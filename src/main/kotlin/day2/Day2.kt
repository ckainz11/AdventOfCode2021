package day2

import util.getInputAsText

fun solve1(): Int = getInputAsText(2).replace("down", "+").replace("up", "-").lines()
    .groupBy({ it.split(" ")[0] }, { it.split(" ")[1].toInt() }).mapValues { it.value.sum() }
    .toSortedMap().values.reduceIndexed { index, acc, i -> if (index == 0) acc + i else if (index == 1) acc - i else acc * i }


data class Position(val depth: Int = 0, val pos: Int = 0, val aim: Int = 0) {
    fun calcRes(): Int = depth * pos
}

data class Move(val direction: String, val times: Int)

fun solve2(): Int = getInputAsText(2).lines().map { x -> Move(x.split(" ")[0], x.split(" ")[1].toInt()) }
    .fold(Position(0, 0, 0)) { acc, s ->
        when (s.direction) {
            "forward" -> acc.copy(pos = acc.pos + s.times, depth = acc.depth + (acc.aim * s.times))
            "down" -> acc.copy(aim = acc.aim + s.times)
            "up" -> acc.copy(aim = acc.aim - s.times)
            else -> throw Exception("what the hell happened")
        }
    }.calcRes()
