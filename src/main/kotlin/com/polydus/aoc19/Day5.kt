package com.polydus.aoc19

import java.lang.Exception
import kotlin.math.floor

class Day5: Day(5) {

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

        //values[1] = 12
        //values[2] = 2

        //values.clear()
        //values.addAll(arrayOf(1101,100,-1,4,0))
        val input = 1

        var index = 0
        while (index < values.size){
            val value = values[index]
            //println("$index $value")

            val instruction = value.toString()









            if(
                instruction.length >= 2
                && instruction.substring(0, 1) != "-"
                && (instruction.substring(instruction.length - 2, instruction.length) == "01"
                        || instruction.substring(instruction.length - 2, instruction.length) == "02")
                /*&& instruction.substring(0, 1) != "-"
                && (instruction.substring(instruction.length - 1, instruction.length).toInt() == 1 ||
                 instruction.substring(instruction.length - 1, instruction.length).toInt() == 2)*/
            ){
                val opcode = instruction.substring(instruction.length - 2, instruction.length)
                val params = arrayListOf<Int>()
                val paramsStr = instruction.substring(0, instruction.length - 2).toCharArray()

                if(instruction.length - 2 == 0){
                    params.add(0)
                    params.add(0)
                    params.add(0)
                } else if(instruction.length - 3 == 0){
                    params.add(instruction.substring(instruction.length - 3, instruction.length - 2).toInt())
                    params.add(0)
                    params.add(0)
                } else if(instruction.length - 4 == 0){
                    params.add(instruction.substring(instruction.length - 3, instruction.length - 2).toInt())
                    params.add(instruction.substring(instruction.length - 4, instruction.length - 3).toInt())
                    params.add(0)
                } else {
                    params.add(instruction.substring(instruction.length - 3, instruction.length - 2).toInt())
                    params.add(instruction.substring(instruction.length - 4, instruction.length - 3).toInt())
                    params.add(instruction.substring(instruction.length - 5, instruction.length - 4).toInt())
                }

                /*params.add(instruction.substring(instruction.length - 3, instruction.length - 2).toInt())
                try {
                    params.add(instruction.substring(instruction.length - 4, instruction.length - 3).toInt())
                }catch (e: Exception){
                    e.printStackTrace()
                }
                if(instruction.length - 4 == 0){
                    params.add(0)
                } else {
                    //params.add(instruction.substring(instruction.length - 5, instruction.length - 4).toInt())
                }*/

                val param1 = if(params[0] == 0){
                    values[values[index + 1]]
                } else if(params[0] == 1){
                    values[index + 1]
                } else {
                    -1
                }
                val param2 = if(params[1] == 0){
                    values[values[index + 2]]
                } else if(params[1] == 1){
                    values[index + 2]
                } else {
                    -1
                }

                if(opcode == "01"){
                    //addition of first 2 params

                    if(params[2] == 0){
                        values[values[index + 3]] = param1 + param2
                    } else if(params[2] == 1){
                        values[index + 3] = param1 + param2
                    }
                    index += 4
                } else if(opcode == "02"){
                    //multiplication of first 2 params

                    if(params[2] == 0){
                        values[values[index + 3]] = param1 * param2
                    } else if(params[2] == 1){
                        values[index + 3] = param1 * param2
                    }
                    index += 4
                }




            } else if(value == 1){
                //println("$index | 1")
                values[values[index + 3]] = values[values[index + 1]] + values[values[index + 2]]
                index += 4
            } else if(value == 2){
                //println("$index | 2")
                values[values[index + 3]] = values[values[index + 1]] * values[values[index + 2]]
                index += 4
            } else if(value == 3){
                //println("$index | 3")
                values[values[index + 1]] = input
                index += 2
            } else if(value == 4){
                //input = values[values[index + 1]]
                System.err.println("$index | output ${values[values[index + 1]]}")
                index += 2
            } else if(value == 99){
                break
            } else  {
                index++
            }
        }

        for(v in values){
            //println(v)
        }
        //println(values[0])
    }

    fun partTwo(){
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

        /*values.clear()
        values.addAll(arrayOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
            1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
            999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99))*/
        val input = 5

        var index = 0
        while (index < values.size){
            val value = values[index]
            val instruction = value.toString()

            if(
                instruction.length >= 2
                && instruction.substring(0, 1) != "-"
                && (instruction.substring(instruction.length - 2, instruction.length) == "01"
                        || instruction.substring(instruction.length - 2, instruction.length) == "02"
                        || instruction.substring(instruction.length - 2, instruction.length) == "05"
                        || instruction.substring(instruction.length - 2, instruction.length) == "06"
                        || instruction.substring(instruction.length - 2, instruction.length) == "07"
                        || instruction.substring(instruction.length - 2, instruction.length) == "08")

            ){
                val opcode = instruction.substring(instruction.length - 2, instruction.length)
                val params = arrayListOf<Int>()
                val paramsStr = instruction.substring(0, instruction.length - 2).toCharArray()

                if(instruction.length - 2 == 0){
                    params.add(0)
                    params.add(0)
                    params.add(0)
                } else if(instruction.length - 3 == 0){
                    params.add(instruction.substring(instruction.length - 3, instruction.length - 2).toInt())
                    params.add(0)
                    params.add(0)
                } else if(instruction.length - 4 == 0){
                    params.add(instruction.substring(instruction.length - 3, instruction.length - 2).toInt())
                    params.add(instruction.substring(instruction.length - 4, instruction.length - 3).toInt())
                    params.add(0)
                } else {
                    params.add(instruction.substring(instruction.length - 3, instruction.length - 2).toInt())
                    params.add(instruction.substring(instruction.length - 4, instruction.length - 3).toInt())
                    params.add(instruction.substring(instruction.length - 5, instruction.length - 4).toInt())
                }

                val param1 = if(params[0] == 0){
                    values[values[index + 1]]
                } else if(params[0] == 1){
                    values[index + 1]
                } else {
                    -1
                }
                val param2 = if(params[1] == 0){
                    values[values[index + 2]]
                } else if(params[1] == 1){
                    values[index + 2]
                } else {
                    -1
                }

                if(opcode == "01"){
                    //addition of first 2 params

                    if(params[2] == 0){
                        values[values[index + 3]] = param1 + param2
                    } else if(params[2] == 1){
                        values[index + 3] = param1 + param2
                    }
                    index += 4
                } else if(opcode == "02"){
                    //multiplication of first 2 params

                    if(params[2] == 0){
                        values[values[index + 3]] = param1 * param2
                    } else if(params[2] == 1){
                        values[index + 3] = param1 * param2
                    }
                    index += 4
                } else if(opcode == "05"){

                    if(param1 != 0){
                        index = param2
                    } else {
                        index++
                    }

                } else if(opcode == "06"){

                    if(param1 == 0){
                        index = param2
                    } else {
                        index++
                    }

                } else if(opcode == "07"){

                    if(param1 < param2){
                        //values[param3] = 1
                        if(params[2] == 0){
                            values[values[index + 3]] = 1
                        } else if(params[2] == 1){
                            values[index + 3] = 1
                        }
                    } else {
                        //values[param3] = 0
                        if(params[2] == 0){
                            values[values[index + 3]] = 0
                        } else if(params[2] == 1){
                            values[index + 3] = 0
                        }
                    }
                    index += 4

                } else if(opcode == "08"){



                    if(param1 == param2){

                        if(params[2] == 0){
                            values[values[index + 3]] = 1
                        } else if(params[2] == 1){
                            values[index + 3] = 1
                        }

                    } else {
                        //values[param3] = 0
                        if(params[2] == 0){
                            values[values[index + 3]] = 0
                        } else if(params[2] == 1){
                            values[index + 3] = 0
                        }
                    }
                    index += 4
                }




            } else if(value == 1){
                println("$index | 1")
                values[values[index + 3]] = values[values[index + 1]] + values[values[index + 2]]
                index += 4
            } else if(value == 2){
                println("$index | 2")
                values[values[index + 3]] = values[values[index + 1]] * values[values[index + 2]]
                index += 4
            } else if(value == 3){
                println("$index | 3")
                values[values[index + 1]] = input
                index += 2
            } else if(value == 4){
                //input = values[values[index + 1]]
                System.err.println("$index | output ${values[values[index + 1]]}")
                index += 2
            } else if(value == 5){
                println("$index | 5")
                if(values[values[index + 1]] != 0){
                    index = values[values[index + 2]]
                } else {
                    index++
                }

            } else if(value == 6){
                println("$index | 6")
                if(values[values[index + 1]] == 0){
                    index = values[values[index + 2]]
                } else {
                    index++
                }

            } else if(value == 7){
                println("$index | 7")
                if(values[values[index + 1]] < values[values[index + 2]]){
                    values[values[index + 3]] = 1
                } else {
                    values[values[index + 3]] = 0
                }
                index += 4

            } else if(value == 8){
                println("$index | 8")
                if(values[values[index + 1]] == values[values[index + 2]]){
                    values[values[index + 3]] = 1
                } else {
                    values[values[index + 3]] = 0
                }
                index += 4

            } else if(value == 99){
                break
            } else  {
                index++
            }
        }

        for(v in values){
            //println(v)
        }
        //println(values[0])

    }


}