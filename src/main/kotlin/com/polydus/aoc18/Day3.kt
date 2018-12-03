package com.polydus.aoc18


class Day3: Day(3){

    //https://adventofcode.com/2018/day/3

    init {

        //partOne()
        partTwo()
    }

    fun partOne(){
        val map = Array(1000) {IntArray(1000)}

        input.forEach {
            var x = 0
            var y = 0
            var width = 0
            var height = 0

            val chars = it.toCharArray()
            for(i in 0 until it.length){

                if(chars[i] == ','){
                    var counter = i - 1
                    while(chars[counter] != ' '){
                        counter--
                    }
                    x = it.substring(counter + 1, i).toInt()
                    counter = i + 1
                    while(chars[counter] != ':'){
                        counter++
                    }
                    y = it.substring(i + 1, counter).toInt()
                }

                if(chars[i] == 'x'){
                    var counter = i - 1
                    while(chars[counter] != ' '){
                        counter--
                    }
                    width = it.substring(counter + 1, i).toInt()
                    counter = i + 1
                    while(counter < chars.size){
                        counter++
                    }
                    height = it.substring(i + 1, counter).toInt()
                }


            }

            //var a = 0
            for(posX in x until x + width){
                for(posY in y until y + height){
                    map[posX][posY] += 1
                    //a++
                }
            }

           // println("$it | $x $y $width $height")
            //println(a)
            //#1302 @ 546,858: 10x14
        }

        var amount = 0
        for(x in 0 until map.size){
            for(y in 0 until map.size){
                if(map[x][y] >= 2){
                    amount++
                }
                //println("$x $y ${map[x][y]}")
            }
        }
        println("answer is $amount")
    }

    fun partTwo(){
        val map = Array(1000) {IntArray(1000)}

        input.forEach {
            var x = 0
            var y = 0
            var width = 0
            var height = 0

            val chars = it.toCharArray()
            for(i in 0 until it.length){

                if(chars[i] == ','){
                    var counter = i - 1
                    while(chars[counter] != ' '){
                        counter--
                    }
                    x = it.substring(counter + 1, i).toInt()
                    counter = i + 1
                    while(chars[counter] != ':'){
                        counter++
                    }
                    y = it.substring(i + 1, counter).toInt()
                }

                if(chars[i] == 'x'){
                    var counter = i - 1
                    while(chars[counter] != ' '){
                        counter--
                    }
                    width = it.substring(counter + 1, i).toInt()
                    counter = i + 1
                    while(counter < chars.size){
                        counter++
                    }
                    height = it.substring(i + 1, counter).toInt()
                }


            }

            //var a = 0
            for(posX in x until x + width){
                for(posY in y until y + height){
                    map[posX][posY] += 1
                    //a++
                }
            }

            // println("$it | $x $y $width $height")
            //println(a)
            //#1302 @ 546,858: 10x14
        }

        var amount = 0
        for(x in 0 until map.size){
            for(y in 0 until map.size){
                if(map[x][y] >= 2){
                    amount++
                }
                //println("$x $y ${map[x][y]}")
            }
        }

        input.forEach {
            //yes not very dry
            var x = 0
            var y = 0
            var width = 0
            var height = 0

            val chars = it.toCharArray()
            for(i in 0 until it.length){

                if(chars[i] == ','){
                    var counter = i - 1
                    while(chars[counter] != ' '){
                        counter--
                    }
                    x = it.substring(counter + 1, i).toInt()
                    counter = i + 1
                    while(chars[counter] != ':'){
                        counter++
                    }
                    y = it.substring(i + 1, counter).toInt()
                }

                if(chars[i] == 'x'){
                    var counter = i - 1
                    while(chars[counter] != ' '){
                        counter--
                    }
                    width = it.substring(counter + 1, i).toInt()
                    counter = i + 1
                    while(counter < chars.size){
                        counter++
                    }
                    height = it.substring(i + 1, counter).toInt()
                }


            }

            var overlaps = false
            for(posX in x until x + width){
                for(posY in y until y + height){
                    if(map[posX][posY] != 1){
                        overlaps = true
                    }
                }
            }

            if(!overlaps){
                println("$it is the answer")
            }


        }
    }
}