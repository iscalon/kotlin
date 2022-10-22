package patterns.structurels.bridge

/**
 * Le Pont est un patron de conception structurel qui permet de séparer une grosse classe ou un ensemble de classes connexes en deux hiérarchies
 * — abstraction et implémentation — qui peuvent évoluer indépendamment l’une de l’autre.
 */
class Bridge {
}

/**
 * Les différents types d'appareils pilotés (implémentations) par les télécommandes (interfaces, pas au sens langage informatique)
 */
interface Appareil {
    fun getVolume(): Int
    fun setVolume(volume: Int)
}

class Telecommande(private val appareil: Appareil) { // C'est l'interface (pas au sens langage de programmation) qui fera appel aux implémentations

    fun monterVolume() {
        appareil.setVolume((appareil.getVolume() + 1).coerceIn(0..100))
    }

    fun descendreVolume() {
        appareil.setVolume((appareil.getVolume() - 1).coerceIn(0..100))
    }

    fun mute() {
        appareil.setVolume(0)
    }
}

data class TVBraviaSony(private val name: String = "XT46K", private var volume: Int = 10) : Appareil {
    override fun getVolume(): Int {
        return volume
    }

    override fun setVolume(volume: Int) {
        println("Le volume actuel de $name est : $volume")
        this.volume = volume
    }
}

data class RadioRadiola(private val name: String = "RAD65300", private var volume: Int = 5) : Appareil {
    override fun getVolume(): Int {
        return volume
    }

    override fun setVolume(volume: Int) {
        println("Le volume actuel de $name est : $volume")
        this.volume = volume
    }
}

fun main() {
    val telecommandeSony = Telecommande(TVBraviaSony())
    val telecommandeRadiola = Telecommande(RadioRadiola())

    telecommandeSony.mute()
    telecommandeRadiola.monterVolume()
    telecommandeSony.descendreVolume()
    telecommandeRadiola.monterVolume()
}