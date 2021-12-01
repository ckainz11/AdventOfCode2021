package day1

import util.getInputAsIntArray

fun solve1(): Int = getInputAsIntArray(1).zipWithNext { a, b -> b - a }.count { x -> x > 0 }
fun solve2(): Int = getInputAsIntArray(1).windowed(3).map { it.sum() }.zipWithNext {a, b -> b - a}.count { x -> x > 0 }