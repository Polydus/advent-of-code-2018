package com.polydus.aoc19

import kotlin.math.abs
import kotlin.math.floor

class Day3: Day(3) {

    private val intersections = ArrayList<Array<Int>>(0)
    private val intersectionsSteps = HashMap<Pos, Int>()

    private val positions = HashMap<Pos, String>()
    private val positions2 = HashMap<Pos, String>()

    private val positionsSteps = HashMap<Pos, Int>()
    private val positions2Steps = HashMap<Pos, Int>()


    init {
        //partOne()
        partTwo()
    }

    fun partOne(){
        //val offset = 0
        //val size = 100 + offset
        /*val tiles: Array<Array<String>> =
            Array(size){
                Array<String>(size){
                    "."
                }
            }*/

        //tiles[1 + offset][1 + offset] = "o"

        var src1 = input[0]//"R8,U5,L5,D3"
        var src2 = input[1]//"U7,R6,D4,L4"
        //var src1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72"
        //var src2 = "U62,R66,U55,R34,D71,R55,D58,R83"
        //var src1 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"
        //var src2 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"

        src1 += ","
        src2 += ","

        drawPath(src1, "\u001B[36m", positions, positions2, positionsSteps, positions2Steps)
        drawPath(src2, "\u001B[31m", positions2, positions, positions2Steps, positionsSteps)

        /*for(y in size - 1 downTo 0) {
            for (x in 0 until size) {
                print(tiles[y][x])
            }
            print("\n")
        }*/

        for(i in intersections){
            val dist = abs(i[0] - (1)) + abs(i[1] - (1))
            println(dist)
        }

        println(intersections.map { (abs(it[0] - 1) + abs(it[1] - 1)) }.min())


       // println(total)
    }


    private fun getValue(x: Int, y: Int){

    }

    private var steps = 0

    private fun drawPath(src: String, color: String, ownPos: HashMap<Pos, String>,
                         otherPos: HashMap<Pos, String>, ownSteps: HashMap<Pos, Int>, otherSteps: HashMap<Pos, Int>){
        var posX = 1
        var posY = 1
        var i = 0
        var direction = -1
        var lastIndex = 0
        steps = 0
        //val chars = src.toCharArray()
        while(i < src.length){
            when(src[i]){
                'R' -> {
                    direction = 0
                    lastIndex = i
                }
                'L' -> {
                    direction = 1
                    lastIndex = i
                }
                'U' -> {
                    direction = 2
                    lastIndex = i
                }
                'D' -> {
                    direction = 3
                    lastIndex = i
                }
                ',' -> {

                    var amount = src.substring(lastIndex + 1, i).toInt()
                    while(amount > 0){
                        when(direction){
                            0 -> posX++
                            1 -> posX--
                            2 -> posY++
                            3 -> posY--
                        }

                        steps++

                        val pos = Pos(posX, posY)
                        ownPos.put(pos, "${color}-\u001B[0m")
                        ownSteps.put(pos, steps)

                        if(otherPos.contains(pos)){
                            //println("intersection steps $steps")
                            //steps = 0
                            intersections.add(arrayOf(posX, posY))
                            intersectionsSteps[pos] = steps + otherSteps[pos]!!
                        }

                        //if(tiles[posY][posX] != "."){
                        //    intersections.add(arrayOf(posX, posY))
                        //}
                        //tiles[posY][posX] = "${color}-\u001B[0m"
                        amount--
                    }

                }
            }
            i++
        }
    }

    fun partTwo(){
        var src1 = input[0]
        var src2 = input[1]
        //var src1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72"
        //var src2 = "U62,R66,U55,R34,D71,R55,D58,R83"

        src1 += ","
        src2 += ","

        drawPath(src1, "\u001B[36m", positions, positions2, positionsSteps, positions2Steps)
        drawPath(src2, "\u001B[31m", positions2, positions, positions2Steps, positionsSteps)

        for(i in intersectionsSteps){
            println(i)
        }

        println(intersectionsSteps.values.min())

    }

    private class Pos(val x: Int, val y: Int){
        override fun toString(): String {
            return "Pos(x=$x, y=$y)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Pos

            if (x != other.x) return false
            if (y != other.y) return false

            return true
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }

    }

}