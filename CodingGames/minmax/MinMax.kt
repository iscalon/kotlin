package org.nico.minmax

class Node(
    private val label: String,
    private var isMax: Boolean = true,
    var value: Double = if (isMax) Double.NEGATIVE_INFINITY else Double.POSITIVE_INFINITY,
    private val children_: MutableSet<Node> = HashSet()
) {
    val children: Set<Node>
        get() = children_

    override fun toString(): String {
        return "$label(value = $value, isMax = $isMax)"
    }

    fun addChild(vararg children: Node) {
        children.forEach { child ->
            child.isMax = !isMax
            if (child.isMax && child.value == Double.POSITIVE_INFINITY) {
                child.value = Double.NEGATIVE_INFINITY
            }
            if (!child.isMax && child.value == Double.NEGATIVE_INFINITY) {
                child.value = Double.POSITIVE_INFINITY
            }
            this.children_.add(child)
        }
    }

    fun isMax(): Boolean {
        return isMax
    }

    fun isLeaf(): Boolean {
        return children.isEmpty()
    }

    fun findNodeWithValue(value: Double): Node? {
        return children.find { it.value == value }
    }
}

class MinMax {
    companion object {
        fun minmax(
            node: Node,
            alpha: Double = Double.NEGATIVE_INFINITY,
            beta: Double = Double.POSITIVE_INFINITY
        ): Double {
            if (node.isLeaf()) return node.value
            var a = alpha
            var b = beta
            return if (node.isMax()) {
                var v = Double.NEGATIVE_INFINITY
                node.children.forEach {
                    v = maxOf(v, minmax(it, a, b))
                    if (v >= b) {
                        return v
                    }
                    a = maxOf(a, v)
                }
                node.value = v
                v
            } else {
                var v = Double.POSITIVE_INFINITY
                node.children.forEach {
                    v = minOf(v, minmax(it, a, b))
                    if (a >= v) {
                        return v
                    }
                    b = minOf(b, v)
                }
                node.value = v
                v
            }
        }
    }
}

fun main() {
    val a = Node("A")
    val b1 = Node("B1")
    val b2 = Node("B2")
    val b3 = Node("B3")
    a.addChild(b1, b2, b3)
    val c1 = Node("C1", value = 12.0)
    val c2 = Node("C2", value = 10.0)
    val c3 = Node("C3", value = 3.0)
    b1.addChild(c1, c2, c3)
    val c4 = Node("C4", value = 5.0)
    val c5 = Node("C5", value = 8.0)
    val c6 = Node("C6", value = 10.0)
    b2.addChild(c4, c5, c6)
    val c7 = Node("C7", value = 11.0)
    val c8 = Node("C8", value = 2.0)
    val c9 = Node("C9", value = 12.0)
    b3.addChild(c7, c8, c9)

    val minmax = MinMax.minmax(a)
    println("MinMax = $minmax")
    val bestChoice = a.findNodeWithValue(minmax)
    println("Meilleur noeud à choisir : $bestChoice")
}