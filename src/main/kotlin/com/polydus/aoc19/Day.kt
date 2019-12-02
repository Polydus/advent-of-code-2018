package com.polydus.aoc19

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

abstract class Day(day: Int){

    val input = Files.lines(Paths.get("input/2019/day$day.txt")).toList()

}