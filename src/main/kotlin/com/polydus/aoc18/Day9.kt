package com.polydus.aoc18

import java.util.*

class Day9: Day(9){

    //https://adventofcode.com/2018/day/9

    var players = 0
    var lastMarblePoints = 0L

    init {
        val string = input[0]
        val chars = string.toCharArray()
        var startedIndex = -1
        var foundPlayers = false
        for(s in 0 until string.length){
            if(!foundPlayers && chars[s] == ' '){
                foundPlayers = true
                players = string.substring(0, s).toInt()
            }
            if(s > 3 && chars[s - 2] == 'h'){
                startedIndex = s
            }
            if(startedIndex > 0 && chars[s] == ' '){
                lastMarblePoints = string.substring(startedIndex, s).toLong()
                break
            }
        }

        //partOne()
        partTwo()
    }

    fun partOne(){
        val marbles = LinkedList<Long>()
        val scores = ArrayList<Long>()
        repeat(players){ scores.add(0) }

        var currentPlayer = 0

        for(marbleIndex in 0 until lastMarblePoints + 1){
            if(marbleIndex != 0L && marbleIndex % 23L == 0L){

                for(j in 6 downTo 0){
                    val first = marbles.first
                    marbles.removeAt(0)
                    marbles.addLast(first)
                }

                scores[currentPlayer] += marbleIndex + marbles.first().toLong()
                marbles.removeFirst()

                val last = marbles.last()
                marbles.removeLast()
                marbles.add(0, last)
            } else {
                if(marbles.size != 0){
                    val last = marbles.last()
                    marbles.removeLast()
                    marbles.add(0, last)
                }
                marbles.add(0, marbleIndex)
            }

            currentPlayer++
            //if(currentPlayer == 35) break
            if(currentPlayer == players) currentPlayer = 0

            /*print("\n[$marbleIndex] ")
            for(m in marbles.withIndex()){
                if(m.index == 0){
                    print("(${m.value})")
                } else {
                    print(" ${m.value} ")
                }
            }*/
        }

        var highestIndex = 0
        var highestValue = 0L

        for(s in scores.withIndex()){
            if(s.value > highestValue){
                highestValue = s.value
                highestIndex = s.index
            }
            //println("player ${s.index} has ${s.value}")
        }

        println("answer is player $highestIndex with $highestValue")
    }

    fun partTwo(){
        lastMarblePoints *= 100
        partOne()
    }
}