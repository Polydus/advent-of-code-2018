package com.polydus.aoc18

class Day7: Day(7){

    //https://adventofcode.com/2018/day/7

    init {

        //partOne()
        partTwo()
    }

    fun partOne(){
        val map = HashMap<Char, Node>()

        input.forEach {
            val type = it.toCharArray()[36]
            val parent = it.toCharArray()[5]

            if(map[type] == null) map[type] = Node(type)
            if(map[parent] == null) map[parent] = Node(parent)
        }


        var sequence = ""

        input.forEach {
            val type = it.toCharArray()[36]
            val parent = it.toCharArray()[5]
            map[type]!!.parents.add(map[parent]!!)
            map[parent]!!.children.add(map[type]!!)

            map[type]!!.parents.sortBy { it.id }
        }


        while(true){
            val node = map.values.filter { it.canFinish() }.sortedBy { it.id }.firstOrNull() ?: break

            sequence += node.id
            node.finished = true
        }

        println(sequence)


        map.forEach {
            print("${it.value.id} has parents: ")
            for(p in it.value.parents) print("${p.id} ")
            print("\n")
            //print("${it.value.id} has children: ")
            //for(p in it.value.children) print("${p.id} ")
            //print("\n")

            //if(it.value.children.size == 0) end = it.value
        }
    }

    fun partTwo(){
        val map = HashMap<Char, Node>()

        input.forEach {
            val type = it.toCharArray()[36]
            val parent = it.toCharArray()[5]

            if(map[type] == null) map[type] = Node(type)
            if(map[parent] == null) map[parent] = Node(parent)
        }

        var sequence = ""

        input.forEach {
            val type = it.toCharArray()[36]
            val parent = it.toCharArray()[5]
            map[type]!!.parents.add(map[parent]!!)
            map[parent]!!.children.add(map[type]!!)

            map[type]!!.parents.sortBy { it.id }
        }

        val workers = arrayOf(0, 0, 0, 0, 0)
        val workersWork = arrayOf('.', '.', '.', '.', '.')
        var seconds = 0

        while(true){
            for(w in workers.withIndex()){
                if(w.value > 0) workers[w.index]--
                if(workers[w.index] == 0 && workersWork[w.index] != '.'){
                    val n = map[workersWork[w.index]]
                    n!!.beginWorked = false
                    n.finished = true
                    sequence += n.id
                    workersWork[w.index] = '.'
                }
            }


            val nodes = map.values.filter { it.canStart() }.sortedBy { it.id }//.firstOrNull()

            if(nodes.isEmpty() && workers.filter { it == 0 }.size == workers.size){
                break
            }

            if(!nodes.isEmpty() && workers.any { it == 0 }){
                for(node in nodes){
                    if(!workers.any { it == 0 }) break

                    for(w in workers.withIndex()){
                        if(w.value == 0){
                            node.beginWorked = true

                            val time = node.id.toInt() - 64 + 60
                            //println("${node.id} has time $time")
                            workers[w.index] = time
                            workersWork[w.index] = node.id
                            break
                        }
                    }
                }
            }


            /*if(node != null && !node.beginWorked && workers.any { it == 0 }){
                node.beginWorked = true

                for(w in workers.withIndex()){
                    if(w.value == 0){
                        var time = node.id.toInt() - 64
                        //println("${node.id} has time $time")
                        workers[w.index] = time
                        workersWork[w.index] = node.id
                        break
                    }
                }
            }*/

            if(seconds < 10){
                println("0$seconds ${workersWork[0]} ${workersWork[1]} ${workersWork[2]} ${workersWork[3]} ${workersWork[4]} $sequence")
            } else {
                println("$seconds ${workersWork[0]} ${workersWork[1]} ${workersWork[2]} ${workersWork[3]} ${workersWork[4]} $sequence")
            }

            seconds++
        }

        println("answer is ${seconds}s $sequence")

    }

    class Node(val id: Char){
        val parents = ArrayList<Node>()
        val children = ArrayList<Node>()

        fun canFinish(): Boolean{
            if(finished) return false
            for(p in parents){
                if(!p.finished) return false
            }
            return true
        }

        fun canStart(): Boolean{
            if(finished || beginWorked) return false
            for(p in parents){
                if(!p.finished) return false
            }
            return true
        }

        var finished = false
        var beginWorked = false
    }
}