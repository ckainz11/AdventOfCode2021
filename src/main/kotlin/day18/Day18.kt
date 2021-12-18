package day18

import day18.SnailFishNumber.Number
import day18.SnailFishNumber.Regular
import util.getInputAsText
import util.permutations
import kotlin.math.ceil
import kotlin.math.floor

val input = getInputAsText(18).lines()

fun solve1(): Long {
    return magnitude(input.map { parse(it) }.reduce { acc, snailFishNumber -> reduce(acc + snailFishNumber) })
}
fun solve2(): Long {
    return permutations(input, 2).maxOf {
        magnitude(reduce(parse(it[0]) + parse(it[1])))
    }
}


fun findExploding(num: SnailFishNumber, depth: Int = 1): Number? {
    return when (num) {
        is Regular -> null
        is Number -> {
            if (depth == 5) {
                num
            } else {
                findExploding(num.left, depth + 1) ?: findExploding(num.right, depth + 1)
            }
        }
    }
}

fun findSplitting(num: SnailFishNumber): Regular? {
    return when (num) {
        is Regular -> if (num.value >= 10) num else null
        is Number -> {
            findSplitting(num.left) ?: findSplitting(num.right)
        }
    }
}

fun explode(num: Number) {
    firstNonSideParent(num, Number::left)?.let { rightMost(it.left) }?.apply {
        value += (num.left as Regular).value
    }
    firstNonSideParent(num, Number::right)?.let { leftMost(it.right) }?.apply {
        value += (num.right as Regular).value
    }

    num.parent?.replace(num, Regular(0))
}

fun firstNonSideParent(num: SnailFishNumber, side: Number.() -> SnailFishNumber): Number? {
    var current = num

    while (current.parent != null) {
        if (current.parent!!.side() !== current) {
            return current.parent
        } else {
            current = current.parent!!
        }
    }

    return null
}

fun rightMost(num: SnailFishNumber): Regular {
    return when (num) {
        is Regular -> num
        is Number -> rightMost(num.right)
    }
}

fun leftMost(num: SnailFishNumber): Regular {
    return when (num) {
        is Regular -> num
        is Number -> leftMost(num.left)
    }
}

fun split(num: Regular) {
    val l = floor(num.value / 2.0).toInt()
    val r = ceil(num.value / 2.0).toInt()
    val newNum = Number(Regular(l), Regular(r))

    num.parent?.replace(num, newNum)
}


fun parse(str: String): SnailFishNumber {
    if (str.startsWith("[")) {
        var bracketCount = 0
        var commaIndex = 0
        for ((i, c) in str.withIndex()) {
            if (c == '[') bracketCount++
            else if (c == ']') bracketCount--
            else if (c == ',' && bracketCount == 1) {
                commaIndex = i
                break
            }
        }

        val left = str.take(commaIndex).drop(1)
        val right = str.drop(commaIndex + 1).dropLast(1)

        return Number(parse(left), parse(right))
    } else {
        return Regular(str.toInt())
    }
}

fun magnitude(num: SnailFishNumber): Long {
    return when (num) {
        is Regular -> num.value.toLong()
        is Number -> magnitude(num.left) * 3 + magnitude(num.right) * 2
    }
}

fun reduce(num: SnailFishNumber): SnailFishNumber {
    while (true) {
        val canExplode = findExploding(num)
        if (canExplode != null) {
            explode(canExplode)
            continue
        }
        val canSplit = findSplitting(num)
        if (canSplit != null) {
            split(canSplit)
            continue
        }
        break
    }
    return num
}
sealed class SnailFishNumber(var parent: Number? = null) {

    operator fun plus(other: SnailFishNumber): SnailFishNumber {
        return Number(this, other)
    }

    data class Number(var left: SnailFishNumber, var right: SnailFishNumber) : SnailFishNumber() {
        init {
            left.parent = this
            right.parent = this
        }

        fun replace(old: SnailFishNumber, new: SnailFishNumber) {
            if (left === old) {
                left = new
            } else {
                right = new
            }
            new.parent = this
        }
    }

    data class Regular(var value: Int) : SnailFishNumber()
}