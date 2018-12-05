package com.polydus.aoc18

import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

class Infi{

    //https://aoc.infi.nl/

    val input = Files.lines(Paths.get("input/infi.txt")).toList()

    val map = Array(20) {Array<Pos?>(20) {null} }

    init {

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
            result = map[x][y]!!
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

class Pos(val x: Int, val y: Int, val type: Char){

    var distToOrigin = -1
    var distToTarget = -1

    var pathCost = 0
        get() = distToOrigin + distToTarget

    var parent: Pos? = null
    
    


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