package com.polydus.aoc18

class Day12: Day(12){

    //https://adventofcode.com/2018/day/12

    var initState = input[0].substring(15)

    val keys = arrayListOf<CharArray>()
    val states = arrayListOf<Char>()

    init {
        for(i in 2 until input.size){
            keys.add(input[i]!!.substring(0, 5).toCharArray())
            states.add(input[i]!!.substring(9).toCharArray()[0])
        }

        partOne()
        //partTwo()
    }

    fun partOne(){
        var startIndex = 0

        println(" ${0}: $initState")

        for(i in 0 until 20){
            var current = ".....$initState....."
            var result = ""
            startIndex += 3

            val chars = current.toCharArray()
            var firstPlant = -1
            var lastPlant = -1

            for(j in 2 until current.length - 2){
                var found = false

                var target = '.'

                for(k in 0 until keys.size){
                    val key = keys[k]
                    if(key[2] == getChar(chars, j) &&
                        key[3] == getChar(chars, j + 1) &&
                        key[4] == getChar(chars, j + 2) &&
                        key[1] == getChar(chars, j - 1) &&
                        key[0] == getChar(chars, j - 2)){
                        found = true
                        target = states[k]
                        break
                    }
                }

                if(found){
                    result += target
                    if(target == '#' && firstPlant == -1) firstPlant = j
                    if(target == '#' && lastPlant < j) lastPlant = j
                } else {
                    result += "."
                }
            }
            initState = result
            //try {
                println(" ${i + 1}: ${result.substring(firstPlant, lastPlant + 1)}")
            //} catch (e: Exception){

            //}
        }

        val chars = initState.toCharArray()
        var score = 0
        for(i in 0 until initState.length){
            if(chars[i] == '#'){
                score += i - startIndex
           }
        }
        println("answer is $score")

    }

    fun getChar(charArray: CharArray, pos: Int): Char{
        return try {
            charArray[pos]
        } catch (e: Exception){
            '.'
        }
    }

    fun partTwo(){
        var startIndex = 0

        println(" ${0}: $initState")

        var score = 0
        var delta = 0

        for(i in 0 until 200){
            var current = ".....$initState....."
            var result = ""
            startIndex += 3

            val chars = current.toCharArray()
            var firstPlant = -1
            var lastPlant = -1

            for(j in 2 until current.length - 2){
                var found = false

                var target = '.'

                for(k in 0 until keys.size){
                    val key = keys[k]
                    if(key[2] == getChar(chars, j) &&
                        key[3] == getChar(chars, j + 1) &&
                        key[4] == getChar(chars, j + 2) &&
                        key[1] == getChar(chars, j - 1) &&
                        key[0] == getChar(chars, j - 2)){
                        found = true
                        target = states[k]
                        break
                    }
                }

                if(found){
                    result += target
                    if(target == '#' && firstPlant == -1) firstPlant = j
                    if(target == '#' && lastPlant < j) lastPlant = j
                } else {
                    result += "."
                }
            }
            initState = result

            var newScore = 0

            val chars2 = initState.toCharArray()
            for(l in 0 until initState.length){
                if(chars2[l] == '#'){
                    newScore += l - startIndex
                }
            }
            println("${i + 1}: $newScore score | ${newScore - score} delta")
            delta = newScore - score
            score = newScore
        }

        var answer = ((50000000000L - 200) * delta) + score

        println("answer is $answer")

    }
}