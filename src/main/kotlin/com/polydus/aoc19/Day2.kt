package com.polydus.aoc19

import java.lang.IndexOutOfBoundsException
import kotlin.math.floor


class Day2: Day(2) {

    init {
        partTwo()
    }

    fun partOne(){
        val inputStr = input.first()
        val values = arrayListOf<Int>()
        var lastIndex = 0
        for(i in inputStr.withIndex()){
            if(i.value == ','){
                values.add(inputStr.substring(lastIndex, i.index).toInt())
                lastIndex = i.index + 1
            } else if(i.index == inputStr.length - 1){
                values.add(inputStr.substring(lastIndex, i.index + 1).toInt())
                lastIndex = i.index + 1
            }
        }

        values[1] = 12
        values[2] = 2

        var index = 0
        while (index < values.size){
            val value = values[index]
            //println("$index $value")
            if(value == 1){
                values[values[index + 3]] = values[values[index + 1]] + values[values[index + 2]]
                index += 4
            } else if(value == 2){
                values[values[index + 3]] = values[values[index + 1]] * values[values[index + 2]]
                index += 4
            } else if(value == 99){
                break
            } else  {
                index++
            }
        }

        println(values[0])
    }

    fun partTwo(){
        for(verb in 0 until 101){
            for(noun in 0 until 101){
                val inputStr = input.first()
                val values = arrayListOf<Int>()
                var lastIndex = 0
                for(i in inputStr.withIndex()){
                    if(i.value == ','){
                        values.add(inputStr.substring(lastIndex, i.index).toInt())
                        lastIndex = i.index + 1
                    } else if(i.index == inputStr.length - 1){
                        values.add(inputStr.substring(lastIndex, i.index + 1).toInt())
                        lastIndex = i.index + 1
                    }
                }

                values[1] = noun
                values[2] = verb

                var index = 0
                while (index < values.size){
                    val value = values[index]
                    if(value == 1){
                        values[values[index + 3]] = values[values[index + 1]] + values[values[index + 2]]
                        index += 4
                    } else if(value == 2){
                        values[values[index + 3]] = values[values[index + 1]] * values[values[index + 2]]
                        index += 4
                    } else if(value == 99){
                        //index = values.size + 99999
                        break
                    } else  {
                        index++
                    }
                }

                if(values[0] == 19690720){
                    println("$noun $verb ${values[0]} | ${100 * noun + verb}")
                    break
                }

            }
        }


    }


}