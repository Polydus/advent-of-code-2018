package com.polydus.aoc18

class Day14: Day(14){

    //https://adventofcode.com/2018/day/14

    val number = input.first().toInt()

    var list = arrayListOf<Int>()

    init {
        list.add(3)
        list.add(7)


        //partOne()
        partTwo()
    }

    fun partOne(){
        var index0 = 0
        var index1 = 1

        while (true){
            var new = list[index0] + list[index1]

            if(new < 10){
                list.add(new)
            } else {
                var string =  (list[index0] + list[index1]).toString()
                var new0 = string.substring(0, 1).toInt()
                var new1 = string.substring(1, 2).toInt()

                list.add(new0)
                list.add(new1)


                //println()
            }

            var amount = 1
            if(index0 == list.size - 1){
                amount += list[0]
            } else {
                amount += list[index0]
            }
            index0 += amount
            index0 %= list.size

            amount = 1
            if(index1 == list.size - 1){
                amount += list[0]
            } else {
                amount += list[index1]
            }
            index1 += amount
            index1 %= list.size

            /*for(l in list.withIndex()){
                if(l.index == index0){
                    print(" (${l.value}) ")
                } else if(l.index == index1){
                    print(" [${l.value}] ")
                } else {
                    print("  ${l.value}  ")
                }
            }
            println()*/

            /*if(index0 < index1){
                if(index1 + 9 < list.size) break
            } else {
                if(index0 + 9 < list.size) break
            }*/

            if(list.size > number + 9){
                print("answer is: ")
                for(n in number until number + 10){
                    print("${list[n]}")
                }
                println()
                break
            }


        }
    }

    fun partTwo(){
        var numbersString = number.toString()
        val numbers = Array<Int>(numbersString.length){0}

        for(n in 0 until numbers.size){
            numbers[n] = Integer.valueOf(numbersString.substring(n, n + 1))
        }

        var index0 = 0
        var index1 = 1

        while (true){
            var new = list[index0] + list[index1]

            if(new < 10){
                list.add(new)
            } else {
                var string =  (list[index0] + list[index1]).toString()
                var new0 = string.substring(0, 1).toInt()
                var new1 = string.substring(1, 2).toInt()

                list.add(new0)
                list.add(new1)


                //println()
            }

            var amount = 1
            if(index0 == list.size - 1){
                amount += list[0]
            } else {
                amount += list[index0]
            }
            index0 += amount
            index0 %= list.size

            amount = 1
            if(index1 == list.size - 1){
                amount += list[0]
            } else {
                amount += list[index1]
            }
            index1 += amount
            index1 %= list.size

            var foundProgress = 0
            if(list.size % 1000000 == 0){
                //i know you dont have to go through the whole list, w/e
                for(l in list.withIndex()){
                    if(l.value == numbers[foundProgress]){
                        foundProgress++
                        if(foundProgress == numbers.size){
                            println("answer is: ${l.index - numbers.size + 1}")
                            break
                        }
                    } else {
                        foundProgress = 0
                    }
                }
                //println(list.size)
            }

            if(foundProgress == numbers.size){
                break
            }



        }
    }
}