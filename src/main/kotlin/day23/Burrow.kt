package day23

import util.*
import day23.Field.*

val empty = "#############\n" +
        "#...........#\n" +
        "###.#.#.#.###\n" +
        "..#.#.#.#.#..\n" +
        "..#.#.#.#.#..\n" +
        "..#.#.#.#.#..\n" +
        "..#########.."
val amphipodColors = setOf('A', 'B', 'C', 'D')
val entrances = setOf(3, 5, 7, 9)

class Burrow(val amphipods: List<Amphipod>) {
    private val burrow = empty.lines().mapIndexed {y, line -> line.mapIndexed {x, col -> if(col == '#') WallField(Point(x,y)) else EmptyField(Point(x,y))}}.toMutableMatrix()

    init {
        for(a in amphipods) {
            burrow[a.pos.y][a.pos.x] = a.copy()
        }
    }

    private fun allAmphipodsCorrectSideRoom(a: Amphipod): Boolean {
        val amphs = amphipods.filter {it.type == a.type && it.pos.x == a.correctSideRoom && it.pos.y > 1}
        return amphs.size == 4
    }

    fun getMoves(a: Amphipod): List<Pair<Point, Int>> {
        if(allAmphipodsCorrectSideRoom(a))
            return emptyList()
        val moves = getLegalMoves(a, a.pos, 0, mutableSetOf(a.pos))
        if(moves.size == 4)
            return moves.takeLast(1)
        return moves
    }
    private fun getLegalMoves(a: Amphipod, current: Point, cost: Int, vis: MutableSet<Point>): List<Pair<Point, Int>> {
        val moves = mutableListOf<Pair<Point, Int>>()
        for(n in possibleNeighbours(current)){
            if(!vis.contains(n))
                vis.add(n)
            else
                continue //skip if visited
            val nextCost = cost + a.energyCost
            if(isLegal(a, n)) {
                moves.add(Pair(n, nextCost))
            }
            moves.addAll(getLegalMoves(a, n, nextCost, vis))
        }
        return moves
    }
    private fun possibleNeighbours(p: Point): List<Point> {
        val neighbours = mutableListOf<Point>()
        val temp = burrow.getAdjacent(p.y, p.x)
        for(f in temp) {
            if(f is EmptyField)
                neighbours.add(f.pos)
        }
        return neighbours
    }
    private fun isLegal(a: Amphipod, move: Point): Boolean {
        if(a.pos.y != 1){ //In a side room
            if(move.y == 1){ // move out the sideroom
                return !entrances.contains(move.x)
            } else return false
        }
        else {
            if(move.y == 1) //when in hallway move into sideroom
                return false
            if(move.x != a.correctSideRoom) //not allowed to move into wrong sideroom
                return false
            else {
                if(sideRoomContainsOtherType(a.correctSideRoom))
                    return false
            }
        }
        return true
    }
    private fun sideRoomContainsOtherType(sideRoom: Int): Boolean {
        return amphipods.filter { it.pos.y > 1 && it.pos.x == sideRoom && it.correctSideRoom != sideRoom }.isNotEmpty()
    }
    fun isSolved(): Boolean {
        return amphipods.none { !(it.pos.y > 1 && it.pos.x == it.correctSideRoom) }
    }
    override fun toString(): String {
        var s = ""
        for (line in burrow) {
            for (col in line) {
                s += col.mapChar
            }
            s += System.lineSeparator()
        }
        return s.trim()
    }
}
sealed class Field {
    abstract val mapChar: Char
    data class Amphipod(var pos: Point, val startingPosition: Point, val type: Char): Field() {

        fun update(newPos: Point) {this.pos = newPos}
        val correctSideRoom: Int
            get() {
                return when (type) {
                    'A' -> 3
                    'B' -> 5
                    'C' -> 7
                    'D' -> 9
                    else -> error("Amphipod has wrong type, check that again")
                }
            }

        val energyCost: Int
            get() {
                return when (type) {
                    'A' -> 1
                    'B' -> 10
                    'C' -> 100
                    'D' -> 1000
                    else -> error("Amphipod has wrong type, check that again")
                }
            }
        override val mapChar = type
    }
    data class EmptyField(val pos: Point): Field() {
        override val mapChar = '.'
    }
    data class WallField(val pos: Point): Field() {
        override val mapChar = '#'
    }
}
