package com.polydus.aoc18

import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

class Infi{

    //https://aoc.infi.nl/

    val input = Files.lines(Paths.get("input/2018/infi.txt")).toList()

    val map = Array(input.size) {Array<Pos?>(input.size) {null} }

    val shuffledMap = Array(input.size) {Array<Pos?>(input.size) {null} }

    val dayTwo = true



    init {

        //dayOne()
        dayTwo()
    }

    fun dayOne(){

        for(x in 0 until 20){
            for(y in 0 until 20){
                map[x][y] = Pos(x, y, input[map.size - 1 - y][x])
            }
        }

        val openList = HashSet<Pos>()
        val closedList = HashSet<Pos>()

        val origin = map[0][19]!!
        val target = map[19][0]!!

        openList.add(origin)

        origin.distToOrigin = 0
        origin.distToTarget = origin.getDeltaTo(target)

        while(!openList.isEmpty()){
            var tile = openList.elementAt(0)

            for(t in openList){
                if(t.pathCost < tile.pathCost) tile = t
            }
            if(tile == target) break

            openList.remove(tile)
            closedList.add(tile)

            val adjacents = getChoices(tile)

            for(a in adjacents){
                val newDistToOrigin = tile.distToOrigin + 1
                if(newDistToOrigin < a.distToOrigin){
                    openList.remove(a)
                    closedList.remove(a)
                }
                if(!openList.contains(a) && !closedList.contains(a)){
                    a.distToOrigin = newDistToOrigin
                    a.distToTarget = a.getDeltaTo(target)
                    a.parent = tile
                    openList.add(a)
                }
            }
        }

        val result = ArrayList<Pos>()

        var tile: Pos? = target
        while(tile?.parent != null){
            result.add(tile)
            tile = tile.parent
        }

        result.reverse()

        println(result.size)

        for(y in 19 downTo 0){
            for(x in 0 until 20){
                if(result.contains(map[x][y])){
                    print("\u001B[36m" + map[x][y]?.type)

                } else {
                    print("\u001B[0m" + map[x][y]?.type)
                }
            }
            print("\n")
        }

    }

    fun dayTwo(): Boolean{
        var size = input.size

        for(x in 0 until size){
            for(y in 0 until size){
                //map[x][y] = Pos(x, y, input[map.size - 1 - y][x])
                shuffledMap[x][y] = Pos(x, y, input[map.size - 1 - y][x])
            }
        }

        val openList = HashSet<Pos>()
        val closedList = HashSet<Pos>()

        val origin = shuffledMap[0][map.size - 1]!!
        val target = shuffledMap[map.size - 1][0]!!

        openList.add(origin)

        origin.distToOrigin = 0
        origin.distToTarget = origin.getDeltaTo(target)

        val list = ArrayList<Pos>()
        var counter = 0
        var success = false

        list.add(origin)

        while(counter < 2000){
            //println("step is $step")
            counter++

            var tile = list.last()

            val adjacents = getChoices(tile)
                .filterNot { list.contains(it) }
                .sortedBy { it.getDeltaTo(target) }
                //.shuffled()
            if(adjacents.isEmpty()) break

            var next = adjacents[0]
            var alts = adjacents
                .filter { it.getDeltaTo(target) == next.getDeltaTo(target) }
                .shuffled()

            if(alts.isNotEmpty()){
                //println("alts size ${alts.size}")
                if(Math.random() < 0.5f){
                    next = alts.first()
                }
            }





            next = setShuffle(next)
            list.add(next)

            if(next == target){
                success = true
                break
            }

            //var tile = openList.elementAt(0)
        }

        //setShuffle(target)
        //setShuffle(target)

        //println("step is $step")


        /*while(!openList.isEmpty()){
            var tile = openList.elementAt(0)

            for(t in openList){
                if(t.pathCost < tile.pathCost){
                    tile = t
                    println("tile is $tile")
                }
            }

            println("new tile is $tile with steps: ${tile.stepsFromOrigin}")

            if(step != tile.stepsFromOrigin){
                //println("need to shuffle, bc step is $step and tile's step is ${tile.stepsFromOrigin}")

                val type = tile.type

                if(step > tile.stepsFromOrigin){
                    //reverse(tile)
                } else if(step < tile.stepsFromOrigin){
                    //setShuffle(step + 1, tile)
                }

                if(type != tile.type){
                    //println("type changed!!")

                }

            } else {
                //println("no need to shuffle")
            }

            if(tile == target) break


            //setShuffle(step + 1)


            openList.remove(tile)
            closedList.add(tile)

            val adjacents = getChoices(tile)

            for(a in adjacents){
                val newDistToOrigin = tile.distToOrigin + 1

                //println("$newDistToOrigin ${a.distToOrigin}")

                if(newDistToOrigin < a.distToOrigin){
                    openList.remove(a)
                    closedList.remove(a)
                    //counter++
                }
                if(!openList.contains(a) && !closedList.contains(a)){
                    a.distToOrigin = newDistToOrigin
                    a.distToTarget = a.getDeltaTo(target)
                    a.parent = tile

                    a.stepsFromOrigin = tile.stepsFromOrigin + 1
                    openList.add(a)

                    //counter++


                }
            }
        }*/

        //println("tiles count $counter. Step is $step")

        val result = ArrayList<Pos>()

        var tile: Pos? = target
        while(tile?.parent != null){
            result.add(tile)
            tile = tile.parent
        }

        result.reverse()

        //println(result.size)
        //println(map.filter { it.parent != null}.size)

        /*for(y in map.size - 1 downTo 0){
            for(x in 0 until map.size){
                if(x == 1 || y == (map.size - 1)){
                    print("\u001B[36m")
                } else {
                    print("\u001B[0m")
                }
                print(shuffledMap[x][y]?.type)
            }
            print("\n")
        }*/

        //setShuffle(2)

        for(y in map.size - 1 downTo 0){
            for(x in 0 until map.size){
                if(list.contains(shuffledMap[x][y])){
                    print("\u001B[36m" + shuffledMap[x][y]?.type)

                } else if(shuffledMap[x][y]!!.parent != null){
                    print("\u001B[31m" + shuffledMap[x][y]?.type)

                } else {
                    print("\u001B[0m" + shuffledMap[x][y]?.type)
                }
            }
            print("\n\u001B[0m")
        }

        for(l in list){
            //println("${l}")
        }

        //println("\nanswer is ${list.size - 1}")

        if(success){
            println("success!!")
        }

        return success
    }

    var step = 0

    fun setShuffle(currentPos: Pos): Pos{
        //step++

        var result = currentPos

        //for(i in step until step + steps){
            var row = step % 2 == 0
            var index = step % map.size

            if(row){
                index = map.size - index - 1

                var last = shuffledMap[map.size - 1][index]!!.type

                for(j in shuffledMap.size - 1 downTo 1){

                    //if(shuffledMap[j][index] == currentPos){
                     //   result = shuffledMap[j - 1][index]!!
                    //}

                    shuffledMap[j][index]!!.type = shuffledMap[j - 1][index]!!.type
                }

                //if(shuffledMap[0][index]!! == currentPos){
                //    result = shuffledMap[map.size - 1][index]!!
                //}

                shuffledMap[0][index]!!.type = last

                if(currentPos.y == index){
                    if(currentPos.x == map.size - 1){
                        result = shuffledMap[0][currentPos.y]!!
                    } else {
                        result = shuffledMap[currentPos.x + 1][currentPos.y]!!
                    }
                }

            } else {
                var first = shuffledMap[index][0]!!.type

                for(j in 0 until shuffledMap.size - 1){
                    //println("set ${shuffledMap[index][j]!!.type} to ${shuffledMap[index][j + 1]!!.type}")


                    if(shuffledMap[index][j] == result){
                        result = shuffledMap[index][j + 1]!!
                    }

                    shuffledMap[index][j]!!.type = shuffledMap[index][j + 1]!!.type
                }

                //println("set ${shuffledMap[index][map.size - 1]!!.type} to $first")
                if(shuffledMap[index][map.size - 1] == result){
                    result =  shuffledMap[index][0]!!
                }
                shuffledMap[index][map.size - 1]!!.type = first



                if(currentPos.x == index){
                    if(currentPos.y == 0){
                        result = shuffledMap[currentPos.x][map.size - 1]!!
                    } else {
                        result = shuffledMap[currentPos.x][currentPos.y - 1]!!
                    }
                }

            }

        //}
        //step += steps
        step++

        return result
    }



    fun reverse(currentPos: Pos): Pos{
        step--

        var row = step % 2 == 0
        var index = step % map.size

        var result = currentPos

        if(row){
            index = map.size - index - 1
            var first = shuffledMap[0][index]!!.type

            for(j in 0 until shuffledMap.size - 1){
                //println("set ${shuffledMap[index][j]!!.type} to ${shuffledMap[index][j + 1]!!.type}")

                if(shuffledMap[j][index] == result){
                    result = shuffledMap[j + 1][index]!!
                }

                shuffledMap[j][index]!!.type = shuffledMap[j + 1][index]!!.type
            }

            //println("set ${shuffledMap[index][map.size - 1]!!.type} to $first")
            if(shuffledMap[map.size - 1][index] == result){
                result = shuffledMap[0][index]!!
            }
            shuffledMap[map.size - 1][index]!!.type = first


        } else {
            var last = shuffledMap[index][map.size - 1]!!.type

            for(j in shuffledMap.size - 1 downTo 1){

                if(shuffledMap[index][j]!! == result){
                    result = shuffledMap[index][j - 1]!!
                }

                shuffledMap[index][j]!!.type = shuffledMap[index][j - 1]!!.type
            }

            if(shuffledMap[index][0]!! == result){
                result = shuffledMap[index][map.size - 1]!!
            }
            shuffledMap[index][0]!!.type = last
        }


        return result
    }

    fun getChoices(pos: Pos): List<Pos>{
        //║ ╔ ╗ ╠ ╦ ╚ ╝ ╬ ╩ ═ ╣
        val result = ArrayList<Pos?>()
        when(pos.type){
            '║' -> {
                result.add(getPos(pos, pos.x,pos.y + 1))
                result.add(getPos(pos, pos.x,pos.y - 1))
            }
            '╔' -> {
                result.add(getPos(pos, pos.x + 1,pos.y))
                result.add(getPos(pos, pos.x,pos.y - 1))
            }
            '╗' -> {
                result.add(getPos(pos, pos.x - 1,pos.y))
                result.add(getPos(pos, pos.x,pos.y - 1))
            }
            '╠' -> {
                result.add(getPos(pos, pos.x,pos.y + 1))
                result.add(getPos(pos, pos.x,pos.y - 1))
                result.add(getPos(pos, pos.x + 1,pos.y))
            }
            '╦' -> {
                result.add(getPos(pos, pos.x,pos.y - 1))
                result.add(getPos(pos, pos.x - 1,pos.y))
                result.add(getPos(pos, pos.x + 1,pos.y))
            }
            '╚' -> {
                result.add(getPos(pos, pos.x,pos.y + 1))
                result.add(getPos(pos, pos.x + 1,pos.y))
            }
            '╝' -> {
                result.add(getPos(pos, pos.x,pos.y + 1))
                result.add(getPos(pos, pos.x - 1,pos.y))
            }
            '╬' -> {
                result.add(getPos(pos, pos.x - 1,pos.y))
                result.add(getPos(pos, pos.x + 1,pos.y))
                result.add(getPos(pos, pos.x,pos.y + 1))
                result.add(getPos(pos, pos.x,pos.y - 1))
            }
            '╩' -> {
                result.add(getPos(pos, pos.x - 1,pos.y))
                result.add(getPos(pos, pos.x + 1,pos.y))
                result.add(getPos(pos, pos.x,pos.y + 1))
            }
            '═' -> {
                result.add(getPos(pos, pos.x - 1,pos.y))
                result.add(getPos(pos, pos.x + 1,pos.y))
            }
            '╣' -> {
                result.add(getPos(pos, pos.x - 1,pos.y))
                result.add(getPos(pos, pos.x,pos.y + 1))
                result.add(getPos(pos, pos.x,pos.y - 1))
            }
        }

        return result.filter { it != null }.requireNoNulls()
    }

    fun getPos(prevPos: Pos, x: Int, y: Int): Pos?{
        if(x < 0 || y < 0 || x > 19 || y > 19) return null

        val result: Pos
        try {
            if(dayTwo){
                result = shuffledMap[x][y]!!
            } else {
                result = map[x][y]!!
            }
        } catch (e: Exception){
            return null
        }
        
        if(prevPos.x < x){
            when(result.type){
                '╗', '╦', '╝', '╬', '╩', '═', '╣' -> {
                    return result
                }
            }
        } else if(prevPos.x > x){
            when(result.type){
                '╔', '╠', '╦', '╚', '╬', '╩', '═' -> {
                    return result
                }
            }
        } else if(prevPos.y < y){
            when(result.type){
                '║', '╔', '╗', '╠', '╦', '╬', '╣' -> {
                    return result
                }
            }
        } else if(prevPos.y > y){
            when(result.type){
                '║', '╠', '╚', '╝', '╬', '╩', '╣' -> {
                    return result
                }
            }
        }

        return null
    }



}

class Pos(val x: Int, val y: Int, var type: Char){

    var distToOrigin = -1
    var distToTarget = -1

    var pathCost = 0
        get() = distToOrigin + distToTarget

    var parent: Pos? = null

    var stepsFromOrigin = 0



    fun getDeltaTo(destination: Pos): Int{
        if(destination == this) return 0
        return (
                Math.abs(x - destination.x) +
                        Math.abs(y - destination.y)) / 2
    }

    override fun equals(other: Any?): Boolean {
        if(other is Pos){
            return other.x == x && other.y == y
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "${x}x${y}y|${type}"
    }
}