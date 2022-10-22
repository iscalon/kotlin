package org.nico.botnet

import java.util.*
import kotlin.collections.ArrayDeque

interface NodeInterface {
    val id: Int
    val neighbors: MutableSet<Node>
    var predecessor: Node?
    var value: Double
}

data class Node(
    override val id: Int,
    override val neighbors: MutableSet<Node> = HashSet(),
    override var value: Double = Double.POSITIVE_INFINITY,
    override var predecessor: Node? = null
) : NodeInterface {
    override fun equals(other: Any?): Boolean {
        if (other == null || other::class != Node::class)
            return false
        return id == (other as Node).id
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "($id, $value)"
    }
}

data class Link(val node1: Node, val node2: Node, val pairSet: Set<Node> = setOf(node1, node2)) {
    companion object {
        fun newLink(text: String /* forme 'n1 - n2' */): Link {
            val nodePair = text.split(" - ")
            return Link(Node(nodePair[0].toInt()), Node(nodePair[1].toInt()))
        }

        fun createFrom(text: Collection<String>, cache: MutableMap<Int, Node> = HashMap()): Set<Link> {

            fun createFrom(text: String /* forme 'n1 - n2' */): Link {
                val nodePair = text.split(" - ")
                val id1 = nodePair[0].toInt()
                val id2 = nodePair[1].toInt()
                val node1 = cache.computeIfAbsent(id1) { Node(it) }
                val node2 = cache.computeIfAbsent(id2) { Node(it) }
                node1.neighbors.add(node2)
                node2.neighbors.add(node1)
                return Link(node1, node2)
            }

            return text.map { createFrom(it) }.toSet()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other::class != Link::class)
            return false
        return pairSet == (other as Link).pairSet
    }

    override fun hashCode(): Int {
        return node1.id * node2.id
    }

    override fun toString(): String {
        return "${node1.id} - ${node2.id}"
    }
}

fun Collection<String>.removeLink(link: String): Collection<String> {
    return this.filter { Link.newLink(it.trim()) != Link.newLink(link.trim()) }
}

fun Collection<String>.removeLink(link: Link): Collection<String> {
    return removeLink(link.toString())
}

fun Collection<Link>.findStartNode(virusNode: Int): Node? {
    val foundNode = this.asSequence()
        .filter { link -> link.node1.id == virusNode || link.node2.id == virusNode }
        .flatMap { it.pairSet }
        .find { it.id == virusNode } ?: return null

    foundNode.value = 0.0

    return foundNode
}

fun Collection<Node>.findMinValueNode(): Node? = minByOrNull { it.value }

fun plusCourtChemin(EI: Node, SI: Int): List<Node> {
    val nodesToSee: ArrayDeque<Node> = ArrayDeque()
    nodesToSee.add(EI)
    val seen: MutableSet<Node> = HashSet()
    var siNode: Node? = null
    while (nodesToSee.isNotEmpty() && siNode == null) {
        val currentNode = nodesToSee.findMinValueNode() ?: break
        seen.add(currentNode)
        nodesToSee.remove(currentNode)

        val neighbors = currentNode.neighbors.asSequence()
            .filter { neighbor -> neighbor !in seen }
            .filter { neighbor -> neighbor.value > currentNode.value + 1 }
            .toSet()

        neighbors.forEach { neighbor ->
            neighbor.value = currentNode.value + 1
            neighbor.predecessor = currentNode
            if (neighbor.id == SI) {
                siNode = neighbor
            }
            nodesToSee.add(neighbor)
        }
    }

    if (siNode == null) {
        return Collections.emptyList()
    }
    return generateSequence(siNode) { node -> node.predecessor }.toList()
}

fun main() {
    var reseau = setOf("0 - 2", "0 - 1", "0 - 3", "2 - 4", "1 - 4", "1 - 5", "3 - 5", "4 - 5")
    println(reseau)
    val links = Link.createFrom(reseau)
    val startNode = links.findStartNode(2)
    startNode?.let {
        val plusCourtChemin = plusCourtChemin(it, 5)
        println(plusCourtChemin)
        if (plusCourtChemin.isNotEmpty()) {
            reseau = reseau.removeLink(Link(plusCourtChemin[0], plusCourtChemin[1])).toSet()
        }
    }


    println(reseau)
}