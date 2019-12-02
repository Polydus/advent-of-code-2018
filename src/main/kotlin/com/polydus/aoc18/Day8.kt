package com.polydus.aoc18

class Day8: Day(8){

    //https://adventofcode.com/2018/day/8

    init {

        //partOne()
        partTwo()
    }

    fun partOne(){
        val list = ArrayList<Int>()

        input.forEach {
            println()
            val chars = it.toCharArray()
            var i = 0

            while (i < chars.size){
                var end = i
                for(j in i until chars.size){
                    if(chars[j] == ' ' || j == chars.size - 1){
                        end = j
                        if(j == chars.size - 1) end++
                        break
                    }
                }
                list.add(it.substring(i, end).toInt())
                i = end + 1
            }
        }

        val root = Node(list[0], list[1], null, "a")
        var current = root

        var i = 2
        while (i < list.size){
            if(current.children.size < current.childrenSize){
                current.children.add(Node(list[i], list[i + 1], current, i.toString()))
                current = current.children.last()
                i += 2
            } else if(current.entries.size < current.entriesSize){
                current.entries.add(list[i])
                i++
            }

            while(current.full()){
                current = current.parent?: break
            }
        }

        var value = 0
        var node: Node? = root

        while(node != null){

            if(node.index < node.children.size){
                node.index++
                node = node.children[node.index - 1]
            } else {
                value += node.entries.sum()
                node = node.parent?: break
            }
        }

        println("answer is $value")
    }

    fun partTwo(){
        val list = ArrayList<Int>()

        input.forEach {
            println()
            val chars = it.toCharArray()
            var i = 0

            while (i < chars.size){
                var end = i
                for(j in i until chars.size){
                    if(chars[j] == ' ' || j == chars.size - 1){
                        end = j
                        if(j == chars.size - 1) end++
                        break
                    }
                }
                list.add(it.substring(i, end).toInt())
                i = end + 1
            }
        }

        var nodes = 0

        val root = Node(list[0], list[1], null, "0")
        var current = root

        var i = 2
        while (i < list.size){
            if(current.children.size < current.childrenSize){
                nodes++
                current.children.add(Node(list[i], list[i + 1], current, nodes.toString()))
                current = current.children.last()
                i += 2
            } else if(current.entries.size < current.entriesSize){
                current.entries.add(list[i])
                i++
            }

            while(current.full()){
                current = current.parent?: break
            }
        }

        var node: Node? = root

        while(node != null){
            //println("node start $node index ${node.index} value ${node.value}")

            if(node.childrenSize > 0){
                if(node.index < node.children.size){
                    node.index++
                    node = node.children[node.index - 1]
                } else {
                    for(j in 0 until node.entries.size){
                        val index = node.entries[j]
                        if(index < node.children.size + 1){
                            //value += node.children[entryValue].entries.sum()
                            node.value += node.children[index - 1].value//node[]
                            //node = node.children[entryValue - 1]
                        }
                    }

                    //println("node $node set to ${node.value}")

                    node = node.parent?: break
                }
            } else {
                node.value = node.entries.sum()
                //println("node $node set to ${node.value}")
                node = node.parent?: break
            }


        }

        println("answer is ${root.value}")

    }

    class Node(val childrenSize: Int, val entriesSize: Int,
               val parent: Node?, val name: String){

        val children = ArrayList<Node>()
        val entries = ArrayList<Int>()

        var index = 0

        fun header(): Int{
            return entriesSize + childrenSize
        }

        fun full(): Boolean{
            return (header()) == children.size + entries.size
        }

        var value = 0

        override fun toString(): String {
            return name
        }

    }
}