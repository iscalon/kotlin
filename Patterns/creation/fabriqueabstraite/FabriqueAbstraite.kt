package patterns.creation.fabriqueabstraite

interface Chaise

interface Lit

interface Armoire

class ChaiseModerne : Chaise

class ChaiseRococo : Chaise

class ChaiseAncienne : Chaise

class LitModerne : Lit

class LitRococo : Lit

class LitAncien : Lit

class ArmoireModerne : Armoire

class ArmoireRococo : Armoire

class ArmoireAncienne : Armoire

/**
 * Fabrique abstraite est un patron de conception qui permet de créer des familles d’objets apparentés sans préciser leur classe concrète.
 */
interface AbstractFactory {
    fun createChaise(): Chaise
    fun createLit(): Lit
    fun createArmoire(): Armoire
}

class FabriqueModerne : AbstractFactory {
    override fun createChaise(): Chaise {
        return ChaiseModerne()
    }

    override fun createLit(): Lit {
        return LitModerne()
    }

    override fun createArmoire(): Armoire {
        return ArmoireModerne()
    }
}

class FabriqueRococo : AbstractFactory {
    override fun createChaise(): Chaise {
        return ChaiseRococo()
    }

    override fun createLit(): Lit {
        return LitRococo()
    }

    override fun createArmoire(): Armoire {
        return ArmoireRococo()
    }
}

class FabriqueAncienne : AbstractFactory {
    override fun createChaise(): Chaise {
        return ChaiseAncienne()
    }

    override fun createLit(): Lit {
        return LitAncien()
    }

    override fun createArmoire(): Armoire {
        return ArmoireAncienne()
    }
}

class Client(private val fabrique: AbstractFactory) {
    fun commanderMobilier() {
        println(fabrique.createLit())
        println(fabrique.createChaise())
        println(fabrique.createArmoire())
    }
}

fun main() {
    val clientRococo = Client(FabriqueRococo())
    clientRococo.commanderMobilier()
}