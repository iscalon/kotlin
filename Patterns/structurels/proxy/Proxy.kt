package patterns.structurels.proxy

interface Abstraction {
    fun executer()
}

class Proxy(private val abstraction: Abstraction) : Abstraction {
    override fun executer() {
        println("Je vérifie qu'on puisse appeler la méthode sur l'objet réel...")
        println("C'est bon, on fait l'appel sur l'objet réel")
        abstraction.executer()
    }
}

class Consultation(private val facture: String) : Abstraction {
    override fun executer() {
        println("Je lis la facture $facture")
    }
}

fun main() {
    val facture: Abstraction = Proxy(Consultation("FBL65300"))
    facture.executer()
}