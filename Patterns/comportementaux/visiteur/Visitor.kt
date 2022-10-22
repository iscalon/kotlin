package patterns.comportementaux.visiteur

interface Element {
    fun accept(visitor: Visitor)
}

interface ElementA : Element

interface ElementB : Element

interface Visitor {
    fun visit(element: ElementA)
    fun visit(element: ElementB)
}

class ConcreteElementA : ElementA {
    override fun accept(visitor: Visitor) {
        println("Salut visiteur, tu es en train de me visiter, je suis A")
    }
}

class ConcreteElementB : ElementB {
    override fun accept(visitor: Visitor) {
        println("Salut visiteur, tu es en train de me visiter, je suis B")
    }
}

class ConcreteVisitor : Visitor {
    override fun visit(element: ElementA) {
        element.accept(this)
    }

    override fun visit(element: ElementB) {
        element.accept(this)
    }
}

fun main() {
    val element = ConcreteElementA()
    val visitor = ConcreteVisitor()
    visitor.visit(element)
    visitor.visit(ConcreteElementB())
}