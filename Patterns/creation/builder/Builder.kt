package patterns.creation.builder

interface Builder<T> {
    fun withSwimpool(size: Int): Builder<T>
    fun withGarage(): Builder<T>
    fun withGarden(): Builder<T>
    fun build(): T
}

class Maison {
    var nbPortes: Int = -1
        private set
    var nbFenetres: Int = -1
        private set

    var sizeSwimmingPool = -1
        private set
    var hasGarage = false
        private set
    var hasGarden = false
        private set

    private constructor(nbPortes: Int, nbFenetres: Int) {
        this.nbPortes = nbPortes
        this.nbFenetres = nbFenetres
    }

    class MaisonBuilder : Builder<Maison> {

        private val maison: Maison

        constructor(nbPortes: Int, nbFenetres: Int) {
            maison = Maison(nbPortes, nbFenetres)
        }

        override fun withSwimpool(size: Int): Builder<Maison> {
            maison.sizeSwimmingPool = size
            return this
        }

        override fun withGarage(): Builder<Maison> {
            maison.hasGarage = true
            return this
        }

        override fun withGarden(): Builder<Maison> {
            maison.hasGarden = true
            return this
        }

        override fun build(): Maison {
            return maison
        }
    }

    override fun toString(): String {
        return with(StringBuilder()) {
            append("nbPortes : $nbPortes, ")
            append("nbFenetres : $nbFenetres, ")
            append("piscine de : $sizeSwimmingPool mètres, ")
            append("garage : $hasGarage, ")
            append("garden : $hasGarden")
        }.toString()
    }

    companion object {
        fun builder(nbPortes: Int, nbFenetres: Int): MaisonBuilder = MaisonBuilder(nbPortes, nbFenetres)
    }
}

fun main() {
    val maisonAvecJardin = Maison.builder(2, 5).withGarden().build()
    println(maisonAvecJardin)
    val maisonAvecPiscineEtGarage = Maison.builder(3, 8).withSwimpool(77).withGarage().build()
    println(maisonAvecPiscineEtGarage)
}