package com.polydus.aoc18

import java.util.*

class Day2: Day(2){

    //https://adventofcode.com/2018/day/2

    init {
        //partOne()
        partTwo()
    }

    fun partOne(){
        var doubles = 0
        var triples = 0

        input.forEach {
            val sorted = it.toCharArray()
            Arrays.sort(sorted)

            var containsDouble = false
            var containsTriple = false
            for(char in sorted.withIndex()){
                if(char.index > 1 && !containsTriple){
                    if(char.value == sorted[char.index - 1] && char.value == sorted[char.index - 2]){
                        containsTriple = true
                    }
                }

                if(char.index > 0 && !containsDouble){

                    //if(char.index < sorted.size - 1){
                    if(char.value == sorted[char.index - 1]
                        && (char.index == sorted.size - 1 || char.value != sorted[char.index + 1])
                        && (char.index == 1 || char.value != sorted[char.index - 2])
                    ){
                        containsDouble = true
                    }

                }

                if(containsDouble && containsTriple) break
            }
            if(containsDouble) doubles++
            if(containsTriple) triples++
            //println("${String(sorted)} | $containsDouble $containsTriple")
        }
        //println("$doubles $triples ${doubles * triples}")
        println("answer is ${doubles * triples}")
    }

    fun partTwo(){

        for(string1 in input){
            for(string2 in input){
                if(string1 == string2) break

                val chars = string1.toCharArray()
                var diffs = 0
                for(char in chars.withIndex()){
                    if(char.value != string2[char.index]){
                        diffs++
                    }
                }
                if(diffs == 1){
                    println(string1)
                    println(string2)
                    for(char in chars.withIndex()){
                        if(char.value != string2[char.index]){
                            val answer = string1.substring(0, char.index) + string1.substring(char.index + 1, string1.length)
                            println("answer is $answer")
                        }
                    }
                    return
                }
            }
        }



    }

}