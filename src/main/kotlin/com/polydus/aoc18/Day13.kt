package com.polydus.aoc18

class Day13: Day(13){

    //https://adventofcode.com/2018/day/13

    val height = input.size
    val width = input.sortedBy { it.length }.last().length

    val map = Array(height) {CharArray(width){ ' '}}

    val carts = arrayListOf<Point>()

    init {
        var i = 0

        input.forEach {
            val chars = it.toCharArray()

            //println(chars.size)

            for(c in 0 until chars.size){
                map[i][c] = chars[c]
                if(chars[c] == '>'){
                    carts.add(Point(c, i, 1))
                    map[i][c] = '-'
                } else if(chars[c] == '<'){
                    carts.add(Point(c, i, 3))
                    map[i][c] = '-'
                } else if(chars[c] == '^'){
                    carts.add(Point(c, i, 0))
                    map[i][c] = '|'
                } else if(chars[c] == 'v'){
                    carts.add(Point(c, i, 2))
                    map[i][c] = '|'
                }
            }
            i++
            //println(it)
        }
        //println("")

        //print()

        //partOne()
        partTwo()
    }

    fun partOne(){
        var running = true
        var tick = 0

        while (running){
            carts.sortWith(compareBy({ it.y }, { it.x }))
            for(c in carts){
                when(c.direction){
                    0 -> c.y--
                    1 -> c.x++
                    2 -> c.y++
                    3 -> c.x--
                }
                setDirection(c)

                for(c2 in carts){
                    if(c != c2 && c.x == c2.x && c.y == c2.y){
                        running = false
                        println("Collision at ${c.x},${c.y}! tick: $tick")
                        break
                    }
                }
            }
            tick++

            carts.sortWith(compareBy({ it.y }, { it.x }))

        }

        for(c in carts.withIndex()){
            println("Cart ${c.index} at ${c.value.x}x${c.value.y}y | ${c.value.direction}d")
        }

        //print()
    }

    fun partTwo(){
        var running = true
        var tick = 0

        while (running){
            carts.sortWith(compareBy({ it.y }, { it.x }))

            for(c in carts){
                if(c.alive){
                    when(c.direction){
                        0 -> c.y--
                        1 -> c.x++
                        2 -> c.y++
                        3 -> c.x--
                    }
                    setDirection(c)

                    for(c2 in carts){
                        if(c2.alive && c != c2 && c.x == c2.x && c.y == c2.y){
                            //running = false
                            println("Collision at ${c.x},${c.y}! tick: $tick")
                            c.alive = false
                            c2.alive = false
                            //break
                        }
                    }
                }
            }
            tick++

            if(carts.filter { it.alive }.size == 1){
                val c = carts.filter { it.alive }.first()
                println("last cart is ${c.x},${c.y}! tick: $tick")
                break
            }


            carts.sortWith(compareBy({ it.y }, { it.x }))

        }

        for(c in carts.withIndex()){
            //println("Cart ${c.index} at ${c.value.x}x${c.value.y}y | ${c.value.direction}d")
        }
    }


    fun setDirection(c: Point){
        when(map[c.y][c.x]){
            '\\' -> {
                when(c.direction){
                    0 ->  c.direction = 3
                    1 ->  c.direction = 2
                    2 ->  c.direction = 1
                    3 ->  c.direction = 0
                }
            }
            '/' -> {
                when(c.direction){
                    0 ->  c.direction = 1
                    1 ->  c.direction = 0
                    2 ->  c.direction = 3
                    3 ->  c.direction = 2
                }
            }
            '+' -> {
                when(c.intersectionCount){
                    0 ->  {
                        c.direction--
                        if(c.direction < 0) c.direction = 3
                    }
                    1 ->  c.direction = c.direction
                    2 ->  {
                        c.direction++
                        if(c.direction > 3) c.direction = 0
                    }
                }
                c.intersectionCount++
                if(c.intersectionCount == 3) c.intersectionCount = 0
            }
        }
    }

    fun print(){
        println("")
        for(y in 0 until height){
            for(x in 0 until width){
                var cart = false
                for(c in carts){
                    if(c.x == x && c.y == y){
                        when(c.direction){
                            0 ->  print('^')
                            1 ->  print('>')
                            2 ->  print('v')
                            3 ->  print('<')
                        }
                        cart = true
                        break
                    }
                }
                if(!cart) print(map[y][x])
            }
            print("\n")
        }
    }

    //  0
    //3   1
    //  2

    class Point(var x: Int, var y: Int, var direction: Int){

        var intersectionCount = 0

        var alive = true


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