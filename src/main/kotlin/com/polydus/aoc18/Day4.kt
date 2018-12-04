package com.polydus.aoc18

class Day4: Day(4){

    //https://adventofcode.com/2018/day/4

    init {

        //partOne()
        partTwo()
    }

    fun partOne(){
        val lines = input.sortedBy {
            var min = it.substring(15, 17).toInt()
            min
        }.sortedBy {
            var hour = it.substring(12, 14).toInt()
            hour
        }.sortedBy {
            var day = it.substring(9, 11).toInt()
            day
        }.sortedBy {
            //val chars = it.toCharArray()
            var month = it.substring(6, 8).toInt()
            //var day = it.substring(9, 11).toInt()
            //var hour = it.substring(12, 14).toInt()
            //var min = it.substring(15, 17).toInt()

            month
        }

        for(line in lines){
            //println(line)
        }

        val guards = HashMap<Int, Array<Int>>()
        var currentGuard = 0
        var lastTime = 0
        //var sleeping = false

        lines.forEach {
            val chars = it.toCharArray()

            var mins = 0

            for(i in 0 until it.length){
                if(chars[i] == ':'){
                    mins = it.substring(i + 1, i + 3).toInt()
                }

                if(chars[i] == 'G' && chars[i - 1] == ' '){
                    var counter = i + 6
                    while(chars[counter] != ' '){
                        counter++
                    }
                    currentGuard = it.substring(i + 7, counter).toInt()
                    if(!guards.contains(currentGuard)){
                        guards[currentGuard] = Array<Int>(59){0}
                    }

                    break
                } else if(chars[i] == 'f' && chars[i - 1] == ' '){

                    break
                } else if(chars[i] == 'w' && chars[i - 1] == ' '){

                    for(j in lastTime until mins){
                        guards[currentGuard]!![j]++
                    }

                    break
                }
            }

            lastTime = mins
        }

        var mostIndex = 0
        var mostMins = 0
        var highestMin = 0

        for(g in guards){

            var mins = 0
            var highValue = 0
            var highIndex = 0

            for(i in 0 until g.value.size){
                mins += g.value[i]
                if(highValue < g.value[i]){
                    highValue = g.value[i]
                    highIndex = i
                }
            }

            if(mins > mostMins){
                mostIndex = g.key
                mostMins = mins
                highestMin = highIndex
            }

            println("\n${g.key} awakemins: ${g.value.filter { it == 0 }.size} totalasleepmins: $mins")
            for(i in 0 until g.value.size){
                print("${g.value[i]} ")
            }
        }

        println("\nanswer is #${mostIndex} with $mostMins highest min $highestMin. | ans is: ${mostIndex * highestMin}")
    }

    fun partTwo(){
        val lines = input.sortedBy {
            var min = it.substring(15, 17).toInt()
            min
        }.sortedBy {
            var hour = it.substring(12, 14).toInt()
            hour
        }.sortedBy {
            var day = it.substring(9, 11).toInt()
            day
        }.sortedBy {
            //val chars = it.toCharArray()
            var month = it.substring(6, 8).toInt()
            //var day = it.substring(9, 11).toInt()
            //var hour = it.substring(12, 14).toInt()
            //var min = it.substring(15, 17).toInt()

            month
        }

        for(line in lines){
            //println(line)
        }

        val guards = HashMap<Int, Array<Int>>()
        var currentGuard = 0
        var lastTime = 0
        //var sleeping = false

        lines.forEach {
            val chars = it.toCharArray()

            var mins = 0

            for(i in 0 until it.length){
                if(chars[i] == ':'){
                    mins = it.substring(i + 1, i + 3).toInt()
                }

                if(chars[i] == 'G' && chars[i - 1] == ' '){
                    var counter = i + 6
                    while(chars[counter] != ' '){
                        counter++
                    }
                    currentGuard = it.substring(i + 7, counter).toInt()
                    if(!guards.contains(currentGuard)){
                        guards[currentGuard] = Array<Int>(59){0}
                    }

                    break
                } else if(chars[i] == 'f' && chars[i - 1] == ' '){

                    break
                } else if(chars[i] == 'w' && chars[i - 1] == ' '){

                    for(j in lastTime until mins){
                        guards[currentGuard]!![j]++
                    }

                    break
                }
            }

            lastTime = mins
        }

        var mostIndex = 0
        var mostMins = 0
        var highestMin = 0
        var highestMinValue = 0

        for(g in guards){

            var mins = 0
            var highValue = 0
            var highIndex = 0

            for(i in 0 until g.value.size){
                mins += g.value[i]
                if(highValue < g.value[i]){
                    highValue = g.value[i]
                    highIndex = i
                }
            }

            if(highValue > highestMinValue){
                mostIndex = g.key
                mostMins = mins
                highestMin = highIndex
                highestMinValue = highValue
            }

            println("\n${g.key} awakemins: ${g.value.filter { it == 0 }.size} totalasleepmins: $mins")
            for(i in 0 until g.value.size){
                print("${g.value[i]} ")
            }
        }

        println("\nanswer is #${mostIndex} " +
                "highest min $highestMin. with value $highestMinValue | ans is: ${mostIndex * highestMin}")

    }
}