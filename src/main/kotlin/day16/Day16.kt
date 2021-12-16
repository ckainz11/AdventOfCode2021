package day16

import util.getInputAsText
import util.hexToBinaryString

val input = getInputAsText(16).lines()
var versionSum = 0
var data = PacketData(0, 0)
fun solve1(): Int {
    for (packet in input) {
        val binary = packet.hexToBinaryString()
        data = processPacket(binary, 0)
    }
    return versionSum
}

fun solve2(): Long = data.value

private fun processPacket(s: String, depth: Int): PacketData {
    val version = s.take(3).toInt(2)
    versionSum += version
    val typeID = s.substring(3, 6).toInt(2)
    if (typeID == 4) {
        return processLiteralPacket(s)
    }
    return processOperatorPacket(typeID, s, depth)
}

private fun processOperatorPacket(typeID: Int, packet: String, depth: Int): PacketData {
    val lengthTypeID = packet[6]
    val subPackets = mutableListOf<Long>()
    var subPacketIndex: Int
    if (lengthTypeID == '0') {
        val subPacketsLength = packet.substring(7, 22).toInt(2)
        subPacketIndex = 22
        while (subPacketIndex != subPacketsLength + 22) {
            val subPacketData = processPacket(packet.substring(subPacketIndex, packet.length), depth + 1)
            subPackets.add(subPacketData.value)
            subPacketIndex += subPacketData.length
        }
    }
    else {
        var totalPacketsCount = packet.substring(7, 18).toInt(2)
        subPacketIndex = 18
        while (totalPacketsCount != 0) {
            val subPacketData = processPacket(packet.substring(subPacketIndex, packet.length), depth + 1)
            subPackets.add(subPacketData.value)
            subPacketIndex += subPacketData.length
            totalPacketsCount--
        }
    }
    return PacketData(getPacketValue(typeID, subPackets), subPacketIndex)
}

private fun getPacketValue(type: Int, packets: List<Long>): Long {
    return when (type) {
        0 -> packets.sum()
        1 -> packets.reduce { acc, i -> acc * i }
        2 -> packets.minOf { it }
        3 -> packets.maxOf { it }
        5 -> if (packets[0] > packets[1]) 1 else 0
        6 -> if (packets[0] < packets[1]) 1 else 0
        7 -> if (packets[0] == packets[1]) 1 else 0
        else -> error("Invalid Packet Type")
    }
}

private fun processLiteralPacket(packet: String): PacketData {
    var i = 6
    var res = ""
    while (packet[i] != '0') {
        res += packet.substring(i + 1, i + 5)
        i += 5
    }
    res += packet.substring(i + 1, i + 5)
    i += 5
    return PacketData(res.toLong(2), i)
}

data class PacketData(val value: Long, val length: Int)
