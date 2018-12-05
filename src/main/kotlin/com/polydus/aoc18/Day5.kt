package com.polydus.aoc18

class Day5: Day(5){

    //https://adventofcode.com/2018/day/5

    val chars = input[0].toCharArray().toMutableList()

    init {

        //partOne()
        partTwo()
    }


    fun partOne(){
        println("input is ${String(chars.toCharArray())}")

        var done = false
        while(!done) done = findReactions(chars)

        println("answer is ${String(chars.toCharArray())}\nSize: ${chars.size}")
    }

    private fun findReactions(chars: MutableList<Char>): Boolean{
        for(i in 1 until chars.size){
            if(chars[i].isUpperCase() && !chars[i - 1].isUpperCase()){
                val char2 = chars[i - 1].toUpperCase()
                if(chars[i] == char2){
                    chars.removeAt(i - 1)
                    chars.removeAt(i - 1)
                    return false
                }

            } else if(!chars[i].isUpperCase() && chars[i - 1].isUpperCase()){
                val char2 = chars[i - 1].toLowerCase()
                if(chars[i] == char2){
                    chars.removeAt(i - 1)
                    chars.removeAt(i - 1)
                    return false
                }
            }

        }
        return true
    }

    fun partTwo(){
        var letters = arrayOf(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        )

        var lowestLength = chars.size
        var charIndex = 'a'

        for(l in letters){
            val input = input[0].toCharArray().toMutableList()
            input.removeAll(listOf(l, l.toUpperCase()))

            println("input is ${String(input.toCharArray())}")

            var done = false
            while(!done) done = findReactions(input)

            if(input.size < lowestLength){
                lowestLength = input.size
                charIndex = l
            }

            println("answer is ${String(input.toCharArray())}\nSize: ${input.size}")
        }

        println("----------------------------------------------------")
        println("answer is ${charIndex}\n Size: ${lowestLength}")

    }

}