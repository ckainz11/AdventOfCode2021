package day3

import util.getInputAsText
import util.transpose
import java.util.*

val originalInput = getInputAsText(3)
fun solve1(): Int =
    transpose(originalInput.lines()).map { Pair(it.count { c -> c == '1' }, it.count { c -> c == '0' }) }
        .fold(Pair("", "")) { acc, s ->
            when (s.first > s.second) {
                true -> acc.copy(acc.first.plus("1"), acc.second.plus("0"))
                false -> acc.copy(acc.first.plus("0"), acc.second.plus("1"))
            }
        }.run {
            this.first.toInt(2) * this.second.toInt(2)
        }


fun solve2(): Int = findRatings(Pair(originalInput.lines(), originalInput.lines()), 0).run { this.first[0].toInt(2) * this.second[0].toInt(2) }
fun findRatings(input: Pair<List<String>, List<String>>, iteration: Int): Pair<List<String>, List<String>> {
    var result = Pair(mutableListOf<String>(), mutableListOf<String>())
    if (input.first.size > 1) {
        val transposedFirst = transpose(input.first)[iteration]
        val max = (transposedFirst.count { it == '1' } >= transposedFirst.count { it == '0' })
        input.first.forEach {
            if (max && it[iteration] == '1')
                result.first.add(it)
            else if (!max && it[iteration] == '0')
                result.first.add(it)
        }
    } else {
        result.first.addAll(input.first)
    }
    if (input.second.size > 1) {
        val transposedSecond = transpose(input.second)[iteration]
        val min = (transposedSecond.count { it == '0' } <= transposedSecond.count { it == '1' })
        input.second.forEach {
            if (min && it[iteration] == '0')
                result.second.add(it)
            else if (!min && it[iteration] == '1')
                result.second.add(it)
        }
    } else {
        result.second.addAll(input.second)
    }

    if (result.first.size > 1 || result.second.size > 1) {
        val next = findRatings(result, iteration + 1)
        result = Pair(next.first.toMutableList(), next.second.toMutableList())
    }
    return result
}
