package com.polydus.aoc19

import kotlin.math.floor

class Day4: Day(4) {

    init {
        partTwo()
    }

    fun partOne(){
        val src = input[0]
        val range0 = src.substring(0, 6).toInt()
        val range1 = src.substring(7, 13).toInt()

        var answers = 0
        for(i in range0..range1){
            val number = i.toString().toCharArray()
            if(number[0] == number[1]
                || number[1] == number[2]
                || number[2] == number[3]
                || number[3] == number[4]
                || number[4] == number[5]){

                var valid = true
                for(j in 1 until number.size){
                    if(number[j].toInt() < number[j - 1].toInt()){
                        valid = false
                    }
                    if(!valid) break
                }
                if(valid) answers++

            }

        }
        println(answers)
    }

    fun partTwo(){
        val src = input[0]
        //val range0 = 111111//src.substring(0, 6).toInt()
        //val range1 = 111122//src.substring(7, 13).toInt()
        val range0 = src.substring(0, 6).toInt()
        val range1 = src.substring(7, 13).toInt()

        var answers = 0
        for(i in range0..range1){
            val number = i.toString().toCharArray()
            if(number[0] == number[1]
                || number[1] == number[2]
                || number[2] == number[3]
                || number[3] == number[4]
                || number[4] == number[5]){

                var valid = true
                for(j in 1 until number.size){
                    if(number[j].toInt() < number[j - 1].toInt()){
                        valid = false
                    }
                    if(!valid) break
                }
                if(valid){

                    var digits = 1
                    var doubles = 0
                    var triples = 0
                    var quads = 0
                    var fives = 0
                    var sixes = 0
                    for(j in 1 until number.size){
                        if(number[j].toInt() == number[j - 1].toInt()){
                            digits++
                        } else {
                            if(digits == 2) doubles++
                            if(digits == 3) triples++
                            if(digits == 4) quads++
                            if(digits == 5) fives++
                            if(sixes == 5) sixes++
                            digits = 1
                        }
                    }
                    if(digits == 2){
                        doubles++
                    }

                    if(doubles > 0){
                        answers++
                    } else {
                        valid = false
                    }

                    if(valid){
                        //println("$i TRUE")
                    } else {
                        //println("$i FALSE")
                    }
                }


            }

        }
        println(answers)

    }


}
