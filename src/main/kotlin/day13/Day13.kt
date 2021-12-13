package day13

import util.Point
import util.getInputAsText
val input = getInputAsText(13).lines()
val points = mutableSetOf<Point>()
val instructions = mutableListOf<String>()

fun solve1(): Int {
    for(line in input){
        if(line.startsWith("fold")){
            instructions.add(line.split(" ")[2])
        }
        else if(line.isNotEmpty()){
            val data = line.split(",")
            val point = Point(data[0].toInt(), data[1].toInt())
            points.add(point)
        }
    }
    val foldPoint = instructions[0].split("=")
    val newPoints = fold(foldPoint[0], foldPoint[1].toInt())
    points.addAll(newPoints)
    return points.size
}
fun solve2(): String {
    for(i in 1 until instructions.size){
        val foldPoint = instructions[i].split("=")
        val newPoints = fold(foldPoint[0], foldPoint[1].toInt())
        points.addAll(newPoints)
    }


    for(y in points.minOf { it.y } .. points.maxOf { it.y }){
        for(x in points.minOf { it.x } .. points.maxOf { it.x }){
            if(points.contains(Point(x,y))) print("#") else print(".")
        }
        println()
    }
    return "RCPLAKHL"
}
private fun fold(axis: String, fold: Int): List<Point>{
    val newPoints = mutableListOf<Point>()
    val iterator = points.iterator()
    while(iterator.hasNext()) {
        val point = iterator.next()
        if(axis == "y" && point.y > fold){
            val offset = point.y - fold
            val newPoint = Point(point.x, fold - offset)
            iterator.remove()
            newPoints.add(newPoint)
        }
        else if(axis == "x" && point.x > fold){
            val offset = point.x - fold
            val newPoint = Point(fold - offset, point.y)
            iterator.remove()
            newPoints.add(newPoint)
        }
    }
    return newPoints
}