package patterns.comportementaux.observer

interface Subscriber {
    fun receiveEvent(event: String)
}

interface Observable {
    fun publishEvent(event: String)

    fun subscription(subscriber: Subscriber)
}

class Journal : Observable {
    private val subscribers: MutableSet<Subscriber> = mutableSetOf()

    override fun publishEvent(event: String) {
        subscribers.forEach { it.receiveEvent(event) }
    }

    override fun subscription(subscriber: Subscriber) {
        subscribers.add(subscriber)
    }
}

class Lecteur(private val name: String) : Subscriber {
    override fun receiveEvent(event: String) {
        println("$name a reçu le journal : $event")
    }
}

fun main() {
    val nicolas = Lecteur("Nicolas")
    val aurelien = Lecteur("Aurélien")

    val leMonde = Journal()

    leMonde.subscription(nicolas)
    leMonde.subscription(aurelien)

    leMonde.publishEvent("Augmentation en cours !")
}