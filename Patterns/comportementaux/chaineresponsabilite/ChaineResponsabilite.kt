package patterns.comportementaux.chaineresponsabilite

interface Maillon {
    fun setNext(maillon: Maillon)

    fun operation(data: Any): Boolean
}

abstract class AbstractMaillon : Maillon {

    private var prochain: Maillon? = null

    abstract fun operationSpecifique(data: Any): Boolean

    override fun setNext(maillon: Maillon) {
        this.prochain = maillon
    }

    override fun operation(data: Any): Boolean {
        if (operationSpecifique(data)) {
            return true;
        }

        prochain?.let {
            return@operation it.operation(data);
        }
        println("On ne sait pas traiter $data")
        return false;
    }
}

class TraitementString : AbstractMaillon() {
    override fun operationSpecifique(data: Any): Boolean {
        if (data is String) {
            println("Je traite la chaine $data")
            return true
        }
        return false
    }
}

class TraitementInt : AbstractMaillon() {
    override fun operationSpecifique(data: Any): Boolean {
        if (data is Int) {
            println("Je traite l'entier $data")
            return true
        }
        return false
    }
}

class TraitementBoolean : AbstractMaillon() {
    override fun operationSpecifique(data: Any): Boolean {
        if (data is Boolean) {
            println("Je traite le booléen $data")
            return true
        }
        return false
    }
}

fun main() {
    val traitementInt = TraitementInt()
    traitementInt.setNext(TraitementString())
    val traitementBoolean = TraitementBoolean()
    traitementBoolean.setNext(traitementInt)

    val chaine: Maillon = traitementBoolean
    chaine.operation("Je suis une chaine")
    chaine.operation(42)
    chaine.operation(777.7)
}