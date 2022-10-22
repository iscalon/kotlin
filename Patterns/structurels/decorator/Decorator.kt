package patterns.structurels.decorator

interface Component {
    fun execute()
}

class DefaultComponent : Component {
    override fun execute() {
        println("Comportement par défaut")
    }
}

abstract class Decorator(protected val wrappee: Component) : Component

class DefaultImpl1(wrappee: Component) : Decorator(wrappee) {
    override fun execute() {
        println("J'ajoute le comportement de type n°1")
        wrappee.execute()
    }
}

class DefaultImpl2(wrappee: Component) : Decorator(wrappee) {
    override fun execute() {
        println("J'ajoute le comportement de type n°2")
        wrappee.execute()
    }
}

fun main() {
    val default = DefaultComponent()
    val decorations = DefaultImpl1(DefaultImpl2(default))
    decorations.execute()
}