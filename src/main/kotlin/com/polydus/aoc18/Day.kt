package com.polydus.aoc18

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.streams.toList

abstract class Day(day: Int){

    val input = Files.lines(Paths.get("input/day$day.txt")).toList()

    init {

    }
}