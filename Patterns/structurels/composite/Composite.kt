package patterns.structurels.composite

interface Component {
    val name: String
    fun execute()
}

class Leaf(override val name: String) : Component {

    override fun execute() {
        println("$name : Je suis une feuille, je peux exécuter la commande")
    }
}

class Composite(override val name: String, private val children: MutableList<Component> = mutableListOf()) : Component {

    override fun execute() {
        println("$name : Je ne suis pas une feuille, je transmets à mes enfants")
        children.forEach(Component::execute)
    }

    fun addChild(child: Component) {
        children.add(child)
    }
}

fun main() {
    val root = Composite("root")
    val subRoot1 = Composite("sub1")
    val leaf1 = Leaf("feuille1")
    root.addChild(subRoot1)
    root.addChild(leaf1)
    subRoot1.addChild(Leaf("subLeaf1"))
    subRoot1.addChild(Leaf("subLeaf2"))

    root.execute()
}