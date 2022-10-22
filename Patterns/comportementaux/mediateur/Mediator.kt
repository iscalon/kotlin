package patterns.comportementaux.mediateur

interface Component

interface Mediator {
    fun notify(component: Component, event: String)
}

object ConcreteMediator : Mediator {

    override fun notify(component: Component, event: String) {
        if (component is Button) {
            reactOnButtonClicked(event)
        }
        if (component is Window && event == "windowClosing") {
            reactOnWindowClose()
        }
    }

    private fun reactOnButtonClicked(event: String) {
        // Some logic
        println("Un bouton vient d'être cliqué, il envoit la valeur $event")
    }

    private fun reactOnWindowClose() {
        // Some logic
        println("Une fenêtre se ferme")
    }
}

open class DefaultComponent(protected val mediator: Mediator) : Component

class Button(mediator: Mediator) : DefaultComponent(mediator) {
    fun click() {
        mediator.notify(this, "save")
    }
}

class Window(mediator: Mediator) : DefaultComponent(mediator) {
    fun close() {
        mediator.notify(this, "windowClosing")
    }
}

fun main() {
    val button = Button(ConcreteMediator)
    val window = Window(ConcreteMediator)

    button.click()
    window.close()
}