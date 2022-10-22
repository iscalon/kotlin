package patterns.creation.fabrique

interface Vehicule {
    val marque: String
}

data class Voiture(override val marque: String) : Vehicule

data class Moto(override val marque: String) : Vehicule

/**
 * Fabrique est un patron de conception de création qui définit une interface pour créer des objets dans une classe mère, mais délègue le choix des types d’objets à créer aux sous-classes.
 */
abstract class Fabrique(val type: String) {
    abstract fun creerVehicule(marque: String): Vehicule

    fun mettreEnMarche(marque: String) {
        println("On va envoyer un véhicule : ${creerVehicule(marque)}")
    }
}

class FabriqueVoiture(type: String) : Fabrique(type) {
    override fun creerVehicule(marque: String): Vehicule {
        return Voiture(marque)
    }
}

class FabriqueVehicule(type: String) : Fabrique(type) {
    override fun creerVehicule(marque: String): Vehicule {
        if (type == "moto") {
            return Moto(marque)
        }
        return Voiture(marque)
    }
}

fun main() {
    val fabrique: Fabrique = FabriqueVehicule("moto")
    fabrique.mettreEnMarche("BMW")

    val fabrique2: Fabrique = FabriqueVoiture("voiture")
    fabrique2.mettreEnMarche("Volvo")
}