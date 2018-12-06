package com.polydus.aoc18

class Day6: Day(6){

    //https://adventofcode.com/2018/day/6

    val map: MutableList<Pos> = mutableListOf()

    init {

        //partOne()
        partTwo()
    }

    fun partOne(){
        var highestX = 0
        var highestY = 0

        input.forEach {
            val chars = it.toCharArray()
            var x = 0
            var y = 0

            for(i in 0 until it.length){

                if(chars[i] == ','){
                    x = it.substring(0, i).toInt()
                } else if(chars[i] == ' '){
                    y = it.substring(i + 1, it.length).toInt()
                }
            }
            if(x > highestX) highestX = x + 2
            if(y > highestY) highestY = y + 2

            map.add(Pos(x, y, (map.size + 65).toChar()))

            //println(map.last())
        }

        val twoDMap = Array(highestY + 1) {Array<Pos?>(highestX + 1) {null} }

        val closestMapId = Array(highestY + 1) {Array<Int>(highestX + 1) {-1} }
        val closestMapDist = Array(highestY + 1) {Array<Int>(highestX + 1) {Integer.MAX_VALUE} }

        for(pos in map.withIndex()){
            twoDMap[pos.value.y][pos.value.x] = pos.value

            for(y in 0 until highestY){
                for(x in 0 until highestX){
                    val dist = pos.value.distTo(x, y)
                    if(dist < closestMapDist[y][x]){
                        closestMapDist[y][x] = dist
                        closestMapId[y][x] = pos.index//pos.char.toLowerCase()
                    } else if(dist == closestMapDist[y][x]){
                        closestMapId[y][x] = -1//'.'
                    }
                }
            }
        }

        val sizeCounters = Array<Int>(map.size){1}

        for(y in 0 until highestY){
            for(x in 0 until highestX){
                if(twoDMap[y][x] == null){
                    val id = closestMapId[y][x]
                    if(id == -1){
                        print("\u001B[0m.")
                    } else {
                        if(x == 0 || y == 0 || x == highestX - 1 || y == highestY - 1){
                            sizeCounters[id] = -1
                        } else if(sizeCounters[id] > -1){
                            sizeCounters[id]++
                        }

                        print("\u001B[0m${map[closestMapId[y][x]].char.toLowerCase()}")
                    }

                } else {
                    print("\u001B[36m${twoDMap[y][x]!!.char}")
                }
            }
            print("\n")
        }

        var maxId = 0
        var highest = 0
        for(c in sizeCounters.withIndex()){
            if(c.value > highest){
                maxId = c.index
                highest = c.value
            }
            //println("${map[c.index].char} with ${c.value}")
        }

        println("answer is ${map[maxId].char} with $highest")



    }

    fun partTwo(){
        var highestX = 0
        var highestY = 0

        input.forEach {
            val chars = it.toCharArray()
            var x = 0
            var y = 0

            for(i in 0 until it.length){

                if(chars[i] == ','){
                    x = it.substring(0, i).toInt()
                } else if(chars[i] == ' '){
                    y = it.substring(i + 1, it.length).toInt()
                }
            }
            if(x > highestX) highestX = x + 2
            if(y > highestY) highestY = y + 2

            map.add(Pos(x, y, (map.size + 65).toChar()))

            //println(map.last())
        }

        val twoDMap = Array(highestY + 1) {Array<Pos?>(highestX + 1) {null} }

        for(pos in map.withIndex()){
            twoDMap[pos.value.y][pos.value.x] = pos.value
        }

        var size = 0

        for(y in 0 until highestY){
            for(x in 0 until highestX){
                var dist = 0
                for(m in map){
                    dist += m.distTo(x, y)
                }

                if(dist < 10000){
                    size++
                }

                if(twoDMap[y][x] == null){

                    if(dist < 10000){
                        print("\u001B[0m#")
                    } else {
                        print("\u001B[0m.")
                    }

                } else {
                    print("\u001B[36m${twoDMap[y][x]!!.char}")
                }
            }
            print("\n")
        }

        println("answer is $size")

    }

    class Pos(val x: Int, val y: Int, var char: Char){

        fun distTo(x: Int, y: Int): Int{
            return Math.abs(this.x - x) + Math.abs(this.y - y)
        }

        override fun equals(other: Any?): Boolean {
            if(other is com.polydus.aoc18.Pos){
                return other.x == x && other.y == y
            }
            return super.equals(other)
        }

        override fun toString(): String {
            return "${x}x${y}y"
        }
    }

}