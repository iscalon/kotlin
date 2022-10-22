package patterns.comportementaux.etat


interface Etat {
    fun travailler(employe: ClasseAvecEtat)
    fun manger(employe: ClasseAvecEtat)
    fun reposer(employe: ClasseAvecEtat)
}

object EmployeMange : Etat {
    override fun travailler(employe: ClasseAvecEtat) {
        println("Je ne peux pas travailler, je mange")
    }

    override fun manger(employe: ClasseAvecEtat) {
        println("Je suis déjà en train de manger")
    }

    override fun reposer(employe: ClasseAvecEtat) {
        println("OK, je me repose")
        employe.etat = EmployeEnPause
    }
}

object EmployeEnPause : Etat {
    override fun travailler(employe: ClasseAvecEtat) {
        println("Je me mets au travail de suite")
        employe.etat = Employe
    }

    override fun manger(employe: ClasseAvecEtat) {
        println("Je n'ai pas envie de manger")
    }

    override fun reposer(employe: ClasseAvecEtat) {
        println("Je suis déjà en pause")
    }
}

object Employe : Etat {
    override fun travailler(employe: ClasseAvecEtat) {
        println("Je suis déjà en train de travailler")
    }

    override fun manger(employe: ClasseAvecEtat) {
        println("OK je vais manger")
        employe.etat = EmployeMange
    }

    override fun reposer(employe: ClasseAvecEtat) {
        println("OK je vais partir en pause")
        employe.etat = EmployeEnPause
    }
}

class ClasseAvecEtat(var etat: Etat = Employe) {
    fun operationManger() {
        etat.manger(this)
    }

    fun operationTravail() {
        etat.travailler(this)
    }

    fun operationPause() {
        etat.reposer(this)
    }
}

fun main() {
    val employe = ClasseAvecEtat()
    employe.operationTravail()
    employe.operationPause()
    employe.operationManger()
    employe.operationTravail()
    employe.operationManger()
    employe.operationTravail()
    employe.operationPause()
    employe.operationTravail()
}