package org.nico.dontpanic

import java.util.*

const val LEFT = "LEFT"
const val RIGHT = "RIGHT"
const val BLOCK = "BLOCK"
const val WAIT = "WAIT"

data class Clone(var cloneFloor: Etage, var clonePos: Int = -1, var direction: String = RIGHT, var blocked: Boolean = false)

data class Etage(val floor: Int, val width: Int, var elevatorPos: Int = -1, val exitPos: Int = -1, private val clones: MutableList<Clone> = mutableListOf()) {
    fun addClone(clone: Clone) = run {
        clones.add(clone)
        clone.cloneFloor = this
    }
}

class Immeuble(private val nbFloors: Int, private val width: Int, private val exitFloor: Int, private val exitPos: Int) {
    private val etages: Array<Etage>

    init {
        etages = Array(nbFloors) { floor ->
            if (floor == exitFloor) Etage(floor, width, exitPos = exitPos)
            else Etage(floor, width)
        }
    }

    operator fun get(floor: Int): Etage = etages[floor]
}

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val nbFloors = input.nextInt() // number of floors
    val width = input.nextInt() // width of the area
    val nbRounds = input.nextInt() // maximum number of rounds
    val exitFloor = input.nextInt() // floor on which the exit is found
    val exitPos = input.nextInt() // position of the exit on its floor
    val nbTotalClones = input.nextInt() // number of generated clones
    val nbAdditionalElevators = input.nextInt() // ignore (always zero)

    val immeuble = Immeuble(nbFloors, width, exitFloor, exitPos)

    val nbElevators = input.nextInt() // number of elevators
    for (i in 0 until nbElevators) {
        val elevatorFloor = input.nextInt() // floor on which this elevator is found
        val elevatorPos = input.nextInt() // position of the elevator on its floor
        immeuble[elevatorFloor].elevatorPos = elevatorPos
    }

    // game loop
    while (true) {
        val cloneFloor = input.nextInt() // floor of the leading clone
        val clonePos = input.nextInt() // position of the leading clone on its floor
        val direction = input.next() // direction of the leading clone: LEFT or RIGHT

        if (cloneFloor >= 0) {
            val etage = immeuble[cloneFloor]
            val clone = Clone(etage, clonePos, direction)
            etage.addClone(clone)
            if (etage.exitPos != -1) {
                // On va vers la sortie
                if ((clone.clonePos > etage.exitPos && clone.direction == RIGHT) || (clone.clonePos < etage.exitPos && clone.direction == LEFT)) {
                    println(BLOCK)
                    continue
                }
            } else {
                // On va vers l'ascenceur
                if ((clone.clonePos > etage.elevatorPos && clone.direction == RIGHT) || (clone.clonePos < etage.elevatorPos && clone.direction == LEFT)) {
                    println(BLOCK)
                    continue
                }
            }
        }
        // Write an action using println()
        // To debug: System.err.println("Debug messages...");

        println(WAIT) // action: WAIT or BLOCK
    }
}