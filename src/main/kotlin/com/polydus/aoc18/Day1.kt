package com.polydus.aoc18

class Day1: Day(1){

    //https://adventofcode.com/2018/day/1

    init {

        //partOne()
        //partTwo()
    }

    fun partOne(){
        var frequency = 0
        input.forEach {
            val positive = it[0] == '+'
            val value = it.substring(1, it.length).toInt()
            if(positive){
                frequency += value
            } else {
                frequency -= value
            }
        }
        println("answer is $frequency")
    }

    fun partTwo(){
        var frequency = 0
        val history = ArrayList<Int>()

        while(true){
            for(line in input){
                val positive = line[0] == '+'
                var value = line.substring(1, line.length).toInt()
                if(!positive) value = -value

                frequency += value
                history.add(frequency)

                println("value $value | frequency $frequency")

                if(history.filter{ it == frequency }.size == 2){
                    println("answer is $frequency")
                    return
                }
            }
        }

    }
}