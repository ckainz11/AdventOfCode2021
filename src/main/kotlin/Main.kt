import day9.solve1
import day9.solve2
import kotlin.system.measureTimeMillis

fun main() {
    println("Starting AdventOfCode 2021 - Merry Christmas!")
    val time1 = measureTimeMillis {
        println("Solution for part 1: ${solve1()}")
    }
    println("In ${time1} ms")
    val time2 = measureTimeMillis {
        println("Solution for part 2: ${solve2()}")
    }
    println("In ${time2} ms")
}