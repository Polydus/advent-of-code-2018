package com.polydus.aoc19

import kotlin.math.floor

class Day1: Day(1) {

    init {
        partTwo()
    }

    fun partOne(){
        var total = 0
        input.forEach {
            val mass = (floor(it.toDouble() / 3f) - 2).toInt()
            total += mass
        }

        println(total)
    }

    fun partTwo(){
        var total = 0
        input.forEach {
            var currentMass = it.toInt()
            var fuel = 0
            while(currentMass > 0){
                val addedFuel = getFuel(currentMass)
                fuel += addedFuel
                currentMass = addedFuel
            }
            total += fuel
        }


        println(total)

    }

    fun getFuel(mass: Int): Int{
        return (floor(mass.toDouble() / 3f) - 2).toInt().coerceAtLeast(0)
    }

}