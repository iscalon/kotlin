package patterns.comportementaux.iterateur

interface Iterator<T> {
    fun hasNext(): Boolean
    fun next(): T
}

interface IterableCollection<T> {
    fun createIterator(): Iterator<T>
}

class ConcreteIterator(private val collection: ConcreteCollection) : Iterator<Int> {
    private var currentIndex = 0

    override fun hasNext(): Boolean = currentIndex < collection.elements.size

    override fun next(): Int {
        if (hasNext()) {
            val element = collection.elements[currentIndex]
            currentIndex++
            return element
        }
        throw IndexOutOfBoundsException("Il n'y a plus d'élément à parcourir")
    }
}

class RandomIterator(private val collection: ConcreteCollection) : Iterator<Int> {
    private val availableValues: MutableList<Int> = collection.elements.toMutableList()
    override fun hasNext(): Boolean = availableValues.isNotEmpty()

    override fun next(): Int {
        if (hasNext()) {
            val element = availableValues.random()
            availableValues.remove(element)
            return element
        }
        throw IndexOutOfBoundsException("Il n'y a plus d'élément à parcourir")
    }
}

class ConcreteCollection(val elements: IntArray) : IterableCollection<Int> {
    override fun createIterator(): Iterator<Int> {
        return ConcreteIterator(this)
    }

    fun createRandomIterator(): Iterator<Int> {
        return RandomIterator(this)
    }
}

fun main() {
    val collection: IterableCollection<Int> = ConcreteCollection(intArrayOf(7, 10, 100, 75000, 4625))
    val iterator = collection.createIterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }

    println("Random iterator :")
    val randomIterator = (collection as ConcreteCollection).createRandomIterator()
    while (randomIterator.hasNext()) {
        println(randomIterator.next())
    }
}