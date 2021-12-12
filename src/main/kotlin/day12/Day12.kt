package day12

import util.getInputAsText
import java.util.ArrayDeque
val input = getInputAsText(12).lines().map { it.split("-") }
val adjacencyList = mutableMapOf<String, MutableList<Cave>>()
val paths = ArrayDeque<String>()
var pathCount = 0
var pathCount2 = 0
fun solve1(): Int {
    for(path in input){
        add(path)
    }
    adjacencyList.values.forEach { it.sortBy { it.name } }
    explore(Cave("start", false))
    return pathCount
}
fun solve2(): Int {
    explore2(Cave("start", false),)
    return pathCount2
}
fun explore2(start: Cave,  visited: MutableList<String> = mutableListOf()) {
    if(start.name == "end") {
        pathCount2++
        return
    }
    if(!start.big)
        visited.add(start.name)
    for(end in adjacencyList[start.name]?: emptyList()){
        if(end.name == "start")
            continue
        if(isVisited(end.name, visited)) {
            if(getDoubleCave(visited) == "") {
                explore2(end, visited)
            }
        }
        else {
            explore2(end, visited)
        }
    }
    if(!start.big)
        visited.remove(start.name)
}
private fun isVisited(cave: String, visited: List<String>): Boolean = visited.any { it == cave }
private fun getDoubleCave(visited: List<String>): String {
    for(cave in visited.groupBy{ it }) {
        if (cave.value.size == 2)
            return cave.key
    }
    return ""
}

fun explore(start: Cave, visited: MutableSet<String> = mutableSetOf()) {
    if(start.name == "end") {
        pathCount++
        return
    }
    if(!start.big)
        visited.add(start.name)
    for(end in adjacencyList[start.name]?: emptyList()){
        if(!visited.contains(end.name)) {
            explore(end, visited)
        }
    }
    if(!start.big)
        visited.remove(start.name)
}

private fun add(path: List<String>) {
    val existingPaths = adjacencyList[path[0]]
    if(existingPaths == null)
        adjacencyList[path[0]] = mutableListOf(Cave(path[1], path[1].all { it.isUpperCase() }))
    else {
        existingPaths.add(Cave(path[1], path[1].all { it.isUpperCase() }))
        adjacencyList[path[0]] = existingPaths
    }
    val existingPaths2 = adjacencyList[path[1]]
    if(existingPaths2 == null)
        adjacencyList[path[1]] = mutableListOf(Cave(path[0], path[0].all { it.isUpperCase() }))
    else {
        existingPaths2.add(Cave(path[0], path[0].all { it.isUpperCase() }))
        adjacencyList[path[1]] = existingPaths2
    }
}
data class Cave(val name: String, val big: Boolean)