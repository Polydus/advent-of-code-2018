package com.polydus.aoc18

class Day10: Day(10){

    //https://adventofcode.com/2018/day/10

    val map = Array(1000) {IntArray(1000)}

    val vectors = ArrayList<Vector>()

    var lowestX = 0
    var lowestY = 0
    var width = 0
    var height = 0

    var secs = 0

    init {
        repeat(map.size){ y ->
            repeat(map.size){x ->
                map[y][x] = -1
            }
        }

        input.forEach {
            var x = 0
            var y = 0
            var vX = 0
            var vY = 0

            val chars = it.toCharArray()
            for(i in 0 until it.length){

                if(chars[i] == 'n'){
                    var positive = (chars[i + 3] != '-')

                    var counter = i + 2
                    while(chars[counter] != ','){
                        counter++
                    }

                    if(positive){
                        if(chars[i + 3] != ' '){
                            x = it.substring(i + 3, counter).toInt()
                        } else {
                            x = it.substring(i + 4, counter).toInt()
                        }
                    } else {
                        x = it.substring(i + 3, counter).toInt()
                    }

                    positive = chars[i + 7] != '-'
                    //counter = i + 6
                    var start = counter + 1
                    while(chars[counter] != '>'){
                        counter++
                    }
                    if(positive){
                        if(chars[start + 1] != ' '){
                            y = it.substring(start + 2, counter).toInt()
                        } else {
                            y = it.substring(start + 2, counter).toInt()
                        }
                    } else {
                        y = it.substring(start + 1, counter).toInt()
                    }
                }

                if(chars[i] == 'y'){
                    var positive = (chars[i + 3] != '-')

                    var counter = i + 2
                    while(chars[counter] != ','){
                        counter++
                    }

                    if(positive){
                        if(chars[i + 3] != ' '){
                            vX = it.substring(i + 3, counter).toInt()
                        } else {
                            vX = it.substring(i + 4, counter).toInt()
                        }
                    } else {
                        vX = it.substring(i + 3, counter).toInt()
                    }

                    positive = chars[i + 7] != '-'
                    counter = i + 6
                    while(chars[counter] != '>'){
                        counter++
                    }
                    if(positive){
                        if(chars[i + 7] != ' '){
                            vY = it.substring(i + 7, counter).toInt()
                        } else {
                            vY = it.substring(i + 8, counter).toInt()
                        }
                    } else {
                        vY = it.substring(i + 7, counter).toInt()
                    }
                    break
                }

            }

            vectors.add(Vector(Point(x, y), Point(vX, vY)))

            if(x > width) width = x
            if(y > height) height = y
            if(x < lowestX) lowestX = x
            if(y < lowestY) lowestY = y

        }



        var found = false


        while (!found){
            for(v in vectors){
                if(v != vectors[0] &&
                    Math.abs(vectors[0].currentX(secs) - v.currentX(secs)) < 2 &&
                    Math.abs(vectors[0].currentY(secs) - v.currentY(secs)) < 2){

                    println(secs)
                    found = true
                    break
                }
            }
            secs++
        }

        for(v in vectors){
            //v.print(secs)
        }

        println("\n")


        //part 2 is how many secs: will print too once you see the message
        partOne()
    }

    fun partOne(){
        var lastTime = 0L

        var x1 = Integer.MIN_VALUE
        var y1 = Integer.MIN_VALUE
        var y0 = Integer.MAX_VALUE
        var x0 = Integer.MAX_VALUE

        while (true){
            if(lastTime < System.currentTimeMillis() - 5000){
                lastTime = System.currentTimeMillis()

                clear()

                for(v in vectors){
                    writeOnMap(v)
                    if(x0 > v.currentX(secs)) x0 = v.currentX(secs)
                    if(y0 > v.currentY(secs)) y0 = v.currentY(secs)

                    if(x1 < v.currentX(secs) && v.currentX(secs) < 1000) x1 = v.currentX(secs)
                    if(y1 < v.currentY(secs) && v.currentY(secs) < 1000) y1 = v.currentX(secs)

                    //if(x1 < v.pos.x + v.speed.x * secs) x1 = v.pos.x + v.speed.x * secs
                    //if(y0 > v.pos.y + v.speed.y * secs) y0 = v.pos.y + v.speed.y * secs
                    //if(y1 < v.pos.y + v.speed.y * secs) y1 = v.pos.y + v.speed.y * secs
                }
                //if(x0 < 0) x0 = 0
                //if(y0 < 0) y0 = 0

                for(y in y0 until y1/*height + Math.abs(lowestY) + 1*/){
                    for(x in x0 until x1/*width + Math.abs(lowestX) + 1*/){
                        if(map[y][x] == -1){
                            print(".")
                        } else{
                            print("#")
                        }
                    }
                    print("\n")
                }
                println("that was $secs secs")
                secs++
            }
            //break
        }

        //println("$secs $lastLowestWidth $lastLowestHeight")
    }

    fun partTwo(){

    }


    private fun writeOnMap(v: Vector){
        val x = v.currentX(secs)/*Math.abs(lowestX) + *///v.pos.x + v.speed.x * secs
        val y = v.currentY(secs)/*Math.abs(lowestY) + *///v.pos.y + v.speed.y * secs
        //println("$x $y")
        if(x < 0 || y < 0 || x > map.size || y > map.size) return

        map[y][x] = 1
    }

    private fun clear(){
        repeat(map.size){ y ->
            repeat(map.size){x ->
                map[y][x] = -1
            }
        }
    }

    class Vector(val pos: Point, val speed: Point){

        fun currentX(secs: Int): Int{
            return pos.x + speed.x * secs
        }

        fun currentY(secs: Int): Int{
            return pos.y + speed.y * secs
        }

        fun print(secs: Int){
            var result = ""
            var x = currentX(secs)
            var y = currentY(secs)
            if(x < 0){
                result += "${x}x"
            } else {
                result += " ${x}x"
            }
            if(y < 0){
                result += " ${y}y"
            } else {
                result += "  ${y}y"
            }

            println("$pos | $speed | $result")
        }
    }

    class Point(val x: Int, val y: Int){

        override fun toString(): String {
            var result = ""
            if(x < 0){
                result += "${x}x"
            } else {
                result += " ${x}x"
            }
            if(y < 0){
                result += " ${y}y"
            } else {
                result += "  ${y}y"
            }
            return result
        }
    }
}