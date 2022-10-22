package patterns.comportementaux.commande

interface Commande {
    fun execute()
}

interface Executor {
    fun action1()
    fun action2()
}

object DefaultExecutor : Executor {
    override fun action1() {
        println("Action1 du defaultExecutor")
    }

    override fun action2() {
        println("Action2 du defaultExecutor")
    }

}

abstract class AbstractCommand(protected open val executor: Executor) : Commande

class CommandeSave(override val executor: Executor) : AbstractCommand(executor) {
    override fun execute() {
        println("On va sauvegarder...")
        executor.action1();
        executor.action2();
    }
}

class CommandeUpdate(override val executor: Executor) : AbstractCommand(executor) {
    override fun execute() {
        println("On va updater...")
        executor.action2();
    }
}

class Invoker(private val executors: MutableMap<String, Executor> = mutableMapOf()) {

    fun addExecutor(name: String, executor: Executor) {
        executors[name] = executor
    }

    private fun executeCommand(commande: Commande) {
        commande.execute()
    }

    private fun createCommandSave(): Commande {
        return CommandeSave(executors["default"] ?: throw Exception("Pas de handler pour le save"))
    }

    private fun createCommandUpdate(): Commande {
        return CommandeUpdate(executors["default"] ?: throw Exception("Pas de handler pour le update"))
    }

    fun actionSave() {
        executeCommand(createCommandSave())
    }

    fun actionUpdate() {
        executeCommand(createCommandUpdate())
    }
}

fun main() {
    val app = Invoker()
    app.addExecutor("default", DefaultExecutor)

    app.actionSave()
    app.actionUpdate()
}