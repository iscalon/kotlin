package org.nico.shadow

import java.util.*

enum class Direction {
    U, UL, UR, D, DL, DR, L, R;

    companion object {
        private val LABEL_TO_ENUM = values().associateBy { it.name }

        fun findDirection(label: String): Direction? = LABEL_TO_ENUM[label]
    }
}

data class Position(val x: Int, val y: Int)

data class Immeuble(private val xDeb: Int = 0, private val yDeb: Int = 0, private val xFin: Int, private val yFin: Int) {

    fun sousImmeuble(direction: Direction, position: Position? = null): Immeuble = when (direction) {
        Direction.U -> Immeuble(xDeb, yDeb, xFin, (position?.y ?: yFin) - 1)
        Direction.UL -> Immeuble(xDeb, yDeb, (position?.x ?: xFin) - 1, (position?.y ?: yFin) - 1)
        Direction.UR -> Immeuble((position?.x ?: xDeb) + 1, yDeb, xFin, (position?.y ?: yFin) - 1)
        Direction.D -> Immeuble(xDeb, (position?.y ?: yDeb) + 1, xFin, yFin)
        Direction.DL -> Immeuble(xDeb, (position?.y ?: yDeb) + 1, (position?.x ?: xFin) - 1, yFin)
        Direction.DR -> Immeuble((position?.x ?: xDeb) + 1, (position?.y ?: yDeb) + 1, xFin, yFin)
        Direction.L -> Immeuble(xDeb, yDeb, (position?.x ?: xFin) - 1, yFin)
        Direction.R -> Immeuble((position?.x ?: xDeb) + 1, yDeb, xFin, yFin)
    }

    fun getMiddle(): Position {
        val w = xFin - xDeb
        val h = yFin - yDeb
        return Position(xDeb + w / 2, yDeb + h / 2)
    }
}

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    val W = 25 //input.nextInt() // width of the building.
    val H = 33 //input.nextInt() // height of the building.
    val X0 = 2//input.nextInt()
    val Y0 = 29//input.nextInt()

    // game loop
    var immeuble = Immeuble(0, 0, W - 1, H - 1)
    var position: Position? = Position(X0, Y0)
    while (true) {
        val bombDir = input.next()!! // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

        immeuble = Direction.findDirection(bombDir)?.let { dir -> immeuble.sousImmeuble(dir, position) }
            ?: throw IllegalArgumentException("La direction $bombDir est inconnue")

        position = immeuble.getMiddle()

        // the location of the next window Batman should jump to.
        println("${position.x} ${position.y}")
    }
}