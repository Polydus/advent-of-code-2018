package com.polydus.aoc18

class Day15: Day(15){

    //https://adventofcode.com/2018/day/15

    val height = input.size
    val width = input.sortedBy { it.length }.last().length

    val map = Array(height) {CharArray(width){ ' '}}

    val mapVecs = Array(height) {Array<Vec?>(width) {null} }


    val goblins = arrayListOf<Unit>()
    val elves = arrayListOf<Unit>()

    val units = arrayListOf<Unit>()

    var lastPath = arrayListOf<Vec>()

    init {
        var i = 0

        input.forEach {
            val chars = it.toCharArray()

            for(c in 0 until chars.size){
                map[i][c] = chars[c]
                if(chars[c] == 'G'){
                    goblins.add(Unit(c, i, 0))
                    units.add(goblins.last())
                    map[i][c] = '.'
                } else if(chars[c] == 'E'){
                    elves.add(Unit(c, i, 1))
                    units.add(elves.last())
                    map[i][c] = '.'
                }

                mapVecs[i][c] = Vec(c, i)
            }
            i++

        }

        print()
        partOne()
        //partTwo()
    }

    fun partOne(){
        var running = true
        var tick = -1

        while (running){
            goblins.sortWith(compareBy({ it.y }, { it.x }))
            elves.sortWith(compareBy({ it.y }, { it.x }))
            units.sortWith(compareBy({ it.y }, { it.x }))

            tick++

            for(u in units){
                if(u.alive){
 //else {
                    var adj = getAdj(u)
                    if(!(adj.contains(0) && u.type == 1) && !(adj.contains(1) && u.type == 0)){

                        val targets = units
                            .filter { it.type != u.type }
                            .filter { it.alive }
                            //.sortedBy { Math.abs(it.x - u.x) + Math.abs(it.y - u.y) }

                        val options = arrayListOf<Vec>()
                        for(t in targets){
                            val adjacent = getAdj(t)
                            for(a in adjacent.withIndex()){
                                if(a.value == 100){
                                    when(a.index){
                                        0 -> options.add(mapVecs[t.y - 1][t.x]!!)
                                        1 -> options.add(mapVecs[t.y][t.x + 1]!!)
                                        2 -> options.add(mapVecs[t.y + 1][t.x]!!)
                                        3 -> options.add(mapVecs[t.y][t.x - 1]!!)
                                    }
                                }
                            }
                        }
                        options.removeIf { !canReach(u, it)}
                        options.sortBy { it.length }
                        //options.sortBy { (Math.abs(it.x - u.x) + Math.abs(it.y - u.y)) }
                        //options.removeIf {
                        //    (Math.abs(it.x - u.x) + Math.abs(it.y - u.y)) !=
                        //    (Math.abs(options.first().x - u.x) + Math.abs(options.first().y - u.y))
                        //}
                        options.removeIf { it.length != options.first().length }
                        //options.sortWith(compareBy({ it.y }, { it.x }))

                        if(options.size > 1){
                            options.sortWith(compareBy({ it.y }, { it.x }))
                        }

                        if(!options.isEmpty()){
                            //move to options.first

                            //just check for a sec if there is a prioritized way to get to the pos

                            canReach(u, options.first())
                            val length = lastPath.size
                            val target = lastPath.last()

                            //if(!(target.x == u.x && u.y - 1 == target.y)){
                                canReach(u, mapVecs[options.first().y - 1][options.first().x]!!)
                                var lengthFrom0 = lastPath.size + 1

                                canReach(u, mapVecs[options.first().y][options.first().x - 1]!!)
                                var lengthFrom3 = lastPath.size + 1

                                canReach(u, mapVecs[options.first().y][options.first().x + 1]!!)
                                var lengthFrom1 = lastPath.size + 1

                                canReach(u, mapVecs[options.first().y + 1][options.first().x]!!)
                                var lengthFrom2 = lastPath.size + 1

                                println()



                            //}


                            println("moving unit from ${u.x}x ${u.y}y to $target")
                            u.x = target.x
                            u.y = target.y

                            /*var dists = arrayOf(999999, 999999, 999999, 999999)

                            if(adj[0] == 100) dists[0] = Math.abs((u.x) - target.x) + Math.abs((u.y - 1) - target.y)
                            if(adj[1] == 100) dists[1] = Math.abs((u.x + 1) - target.x) + Math.abs((u.y) - target.y)
                            if(adj[2] == 100) dists[2] = Math.abs((u.x) - target.x) + Math.abs((u.y + 1) - target.y)
                            if(adj[3] == 100) dists[3] = Math.abs((u.x - 1) - target.x) + Math.abs((u.y) - target.y)

                            //var lowest = 0
                            var lowestDist = 999999
                            for(d in dists.withIndex()){
                                if(d.value != 999999 && d.value < lowestDist){
                                    lowestDist = d.value
                                    //lowest = d.index
                                }
                            }*/

                            /*if(lowestDist == 999999){
                                println("$u did nothing")
                            } else {
                                if(dists[0] == lowestDist){
                                    u.y--
                                } else if(dists[3] == lowestDist){
                                    u.x--
                                } else if(dists[1] == lowestDist){
                                    u.x++
                                } else if(dists[2] == lowestDist){
                                    u.y++
                                }
                                println("$u moved")
                            }*/




                        } else {
                            println("$u did nothing")
                        }


                        //println()

                           // .sortWith((Math.abs(it.x - u.x) + Math.abs(it.y - u.y)))
                            //.sortWith { (Math.abs(it.x - unit.x) + Math.abs(it.y - unit.y)) }
                            //.sortWith(compareBy({ it.y }, { it.x }))
                        /*

                        var moved = false

                        for(t in targets.withIndex()){
                            var closestIndex = -1
                            var dist = 999999999

                            if(adj[2] == 100 &&
                                (Math.abs(t.value.x - u.x) + Math.abs(t.value.y - (u.y + 1))) <= dist){
                                closestIndex = 2
                                dist = (Math.abs(t.value.x - u.x) + Math.abs(t.value.y - (u.y + 1)))
                            }
                            if(adj[1] == 100 &&
                                (Math.abs(t.value.x - (u.x + 1)) + Math.abs(t.value.y - (u.y))) <= dist){
                                closestIndex = 1
                                dist = (Math.abs(t.value.x - (u.x + 1)) + Math.abs(t.value.y - (u.y)))
                            }
                            if(adj[3] == 100 &&
                                (Math.abs(t.value.x - (u.x - 1)) + Math.abs(t.value.y - (u.y))) <= dist){
                                closestIndex = 3
                                dist = (Math.abs(t.value.x - (u.x - 1)) + Math.abs(t.value.y - (u.y)))
                            }
                            if(adj[0] == 100 &&
                                (Math.abs(t.value.x - u.x) + Math.abs(t.value.y - (u.y - 1))) <= dist){
                                closestIndex = 0
                                dist = (Math.abs(t.value.x - u.x) + Math.abs(t.value.y - (u.y - 1)))
                            }

                            if(t.value == targets.last() && closestIndex > -1){
                                //move to this tile
                                when(closestIndex){
                                    0 -> u.y--
                                    1 -> u.x++
                                    2 -> u.y++
                                    3 -> u.x--
                                }
                                moved = true
                                break
                            }
                        }

                        if(moved){
                            println("$u moved")
                        } else {
                            println("$u did nothing")
                        }*/
                    }

                    adj = getAdj(u)
                    if(adj.contains(0) && u.type == 1){
                        val units = getAdjUnits(u)
                        val target = units.first()
                        target.hp -= 3
                        if(target.hp < 0) target.alive = false

                        println("$u hit a target: $target ${target.type}(${target.hp}) was hit")

                    } else if(adj.contains(1) && u.type == 0){
                        val units = getAdjUnits(u)
                        val target = units.first()
                        target.hp -= 3
                        if(target.hp < 0) target.alive = false

                        println("$u hit a target: $target ${target.type}(${target.hp}) was hit")

                    }

                }



                if(goblins.none { it.alive }){
                    running = false

                    //tick++
                    println("After $tick rounds:")
                    print()
                    break
                } else if(elves.none { it.alive }){
                    running = false

                    //tick++
                    println("After $tick rounds:")
                    print()
                    break
                }

                /*when(c.direction){
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
                }*/
            }

            //if(running) tick++
            println("After $tick rounds:")
            print()

            //if(tick == 3) break

            //carts.sortWith(compareBy({ it.y }, { it.x }))

        }

        var answer = 0
        for(u in units){
            if(u.alive) answer += u.hp
        }
        answer *= tick

        println("done. turn: $tick. Answer: $answer")

        //for(c in carts.withIndex()){
            //println("Cart ${c.index} at ${c.value.x}x${c.value.y}y | ${c.value.direction}d")
        //}
    }

    fun partTwo(){


    }

    fun canReach(unit: Unit, target: Vec): Boolean{
        //lastPath.clear()
        val origin = mapVecs[unit.y][unit.x]
        if(origin == target) return false

        for(y in 0 until height){
            for(x in 0 until width){
                mapVecs[y][x]?.resetPathVars()
            }
        }

        val openList = HashSet<Vec>()
        val closedList = HashSet<Vec>()

        openList.add(origin!!)
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

            val adjacents = tile.getPassableAdjacents(mapVecs, map, units)

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

        val result = ArrayList<Vec>()

        var tile: Vec? = target
        while(tile?.parent != null){
            result.add(tile)
            tile = tile.parent
        }

        lastPath.clear()
        lastPath.addAll(result)

        if(result.size > 0){
            target.length = result.size
        } else {
            target.length = 99999
        }


        return (result.size > 0)
    }

    fun print(){
        println("")
        for(y in 0 until height){
            var units = arrayListOf<Unit>()

            for(x in 0 until width){
                var cart = false
                for(c in this.units){
                    if(c.alive && c.x == x && c.y == y){
                        when(c.type){
                            0 ->  print('G')
                            1 ->  print('E')
                        }
                        cart = true
                        units.add(c)
                        break
                    }
                }
                if(!cart) print(map[y][x])
            }

            if(units.size > 0){
                print("  ")
                for(u in units){
                    if(u.type == 0){
                        print(" G(${u.hp})")
                    } else {
                        print(" E(${u.hp})")
                    }
                }
            }
            print("\n")
        }
    }

    fun getAdj(unit: Unit): Array<Int>{
        val result = Array<Int>(4){-1}
        result[0] = type(unit.x, unit.y - 1)
        result[1] = type(unit.x + 1, unit.y)
        result[2] = type(unit.x, unit.y + 1)
        result[3] = type(unit.x - 1, unit.y)
        return result
    }

    fun getAdjUnits(unit: Unit): List<Unit>{
        //val result = arrayListOf<Unit>()
        val units = this.units.filter { it.type != unit.type }
            .filter { (Math.abs(it.x - unit.x) + Math.abs(it.y - unit.y)) == 1 }
            .filter { it.alive }
            .sortedWith(compareBy({ it.y }, { it.x }))
            .sortedBy { it.hp }
        return units
    }


    fun type(x: Int, y: Int): Int{
        try {
            var result = -1
            val type = map[y][x]
            when(type){
                '#' -> result = 101
                '.' -> result = 100
            }
            if(result == 100){
                for(u in units){
                    if(u.alive && u.x == x && u.y == y){
                        result = u.type
                        break
                    }
                }
            }

            return result
        }catch (e: Exception){
            return -1
        }
    }


    class Unit(var x: Int, var y: Int, val type: Int){

        var intersectionCount = 0

        var alive = true

        var hp = 200

        override fun toString(): String {
            var result = ""
            if(x < 0){
                result += "${x}x"
            } else {
                result += " ${x}x"
            }
            if(y < 0){
                result += "${y}y"
            } else {
                result += " ${y}y"
            }
            if(type == 0){
                result += " G($hp)"
            } else {
                result += " E($hp)"
            }
            return result
        }
    }

    class Vec(val x: Int, val y: Int){

        var distToOrigin = -1
        var distToTarget = -1

        var pathCost = 0
            get() = distToOrigin + distToTarget

        var parent: Vec? = null

        var length = 99999

        fun getPassableAdjacents(//excludeType: Int?,
                                 mapVecs: Array<Array<Vec?>>,
                                 map: Array<CharArray>,
                                 units: ArrayList<Unit>): List<Vec>{
            return listOf(
                getTile(y + 1, x, mapVecs), getTile(y, x + 1, mapVecs),
                getTile(y - 1, x, mapVecs), getTile(y, x - 1, mapVecs))
                .filterNot { it == null }
                .filter { type(it!!.x, it!!.y, map, units) == 100 }
                //.filterNot { it?.terrainType == excludeType }
                .requireNoNulls()
        }

        fun getTile(y: Int, x: Int, mapVecs: Array<Array<Vec?>>): Vec?{
            if(y < 0 || x < 0) return null //can't have negative indices
            return try {
                mapVecs[y][x]!!
            } catch (e: NullPointerException){
                null
            }
        }

        fun getDeltaTo(destination: Vec): Int{
            if(destination == this) return 0
            return (Math.abs(y - destination.y) +
                    Math.abs(x - destination.x))
        }

        fun type(x: Int, y: Int, map: Array<CharArray>, units: ArrayList<Unit>): Int{
            try {
                var result = -1
                val type = map[y][x]
                when(type){
                    '#' -> result = 101
                    '.' -> result = 100
                }
                if(result == 100){
                    for(u in units){
                        if(u.alive && u.x == x && u.y == y){
                            result = u.type
                            break
                        }
                    }
                }

                return result
            }catch (e: Exception){
                return -1
            }
        }

        fun resetPathVars(){
            distToOrigin = -1
            distToTarget = -1
            parent = null
            //length = 99999
        }

        override fun toString(): String {
            var result = ""
            if(x < 0){
                result += "${x}x"
            } else {
                result += " ${x}x"
            }
            if(y < 0){
                result += "${y}y"
            } else {
                result += " ${y}y"
            }
            return result
        }
    }
}