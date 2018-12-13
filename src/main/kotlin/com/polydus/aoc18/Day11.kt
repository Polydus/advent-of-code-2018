package com.polydus.aoc18

class Day11: Day(11){

    //https://adventofcode.com/2018/day/11

    val map = Array(300) {IntArray(300)}

    init {
        val input = input[0].toInt()

        for(y in 1 until map.size + 1){
            for(x in 1 until map.size + 1){
                //map[y][x] = -1

                if(x == 3 && y == 5){
                    //println()
                }

                val rackId = x + 10
                var powerLevel = rackId * y
                powerLevel += input
                powerLevel *= rackId

                val str = powerLevel.toString()
                //println("hunds digit of $powerLevel is ${str.substring(str.length - 3, str.length - 2).toInt()}")
                powerLevel = str.substring(str.length - 3, str.length - 2).toInt()

                powerLevel -= 5
                map[y - 1][x - 1] = powerLevel
            }
        }

        //var x0 = 33
        //var y0 = 45


        //3628

        /*for(y in y0 until y0 + 3){
            for(x in x0 until x0 + 3){
                val amount = map[y - 1][x - 1]
                if(amount < 0){
                    //print("${amount} ")
                } else {
                    //(" ${amount} ")
                }
            }
            //print("\n")
        }*/


        //partOne()
        partTwo()
    }

    fun partOne(){
        var bestSum = 0
        var bestX = 0
        var bestY = 0

        repeat(map.size - 2){y0 ->
            repeat(map.size - 2){x0->
                var sum = 0
                for(y in y0 until y0 + 3){
                    for(x in x0 until x0 + 3){
                        val amount = map[y][x]
                        if(amount < 0){
                            //print("${amount} ")
                        } else {
                            //print(" ${amount} ")
                        }
                        sum += amount
                    }
                    //print("\n")
                }
                if(sum > bestSum){
                    bestSum = sum
                    bestX = x0 + 1
                    bestY = y0 + 1
                    //println("best sum is $bestSum $bestX $bestY")
                }
            }
        }
        println("best sum is $bestSum | ${bestX},${bestY}")
    }

    fun partTwo(){
        var bestSum = 0
        var bestSize = 0
        var bestX = 0
        var bestY = 0

        //var size = 3

        for(size in 3 until 300){
            repeat(map.size - (size - 1)){y0 ->
                repeat(map.size - (size - 1)){x0->
                    var sum = 0
                    for(y in y0 until y0 + size){
                        for(x in x0 until x0 + size){
                            sum += map[y][x]
                        }
                    }
                    if(sum > bestSum){
                        bestSum = sum
                        bestSize = size
                        bestX = x0 + 1
                        bestY = y0 + 1
                        println("best sum is $bestSum | $bestX,$bestY | $bestSize")
                    }
                }
            }
        }




        println("best sum is $bestSum | ${bestX},${bestY} | $bestSize")

    }
}