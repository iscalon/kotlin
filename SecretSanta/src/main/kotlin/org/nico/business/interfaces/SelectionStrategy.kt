package org.nico.business.interfaces

/**
 * Méthode utilitaire pour tirer une personne au hasard dans une liste de personnes.
 *
 * @return <code>null</code> si la collection est vide, une personne de la liste tirée au hasard sinon.
 */
fun Collection<Person>.findRandomPerson(): Person? {
    if (this.isEmpty()) return null
    return this.random()
}

/**
 * Interface définissant une stratégie de tirage des pères noëls
 */
interface SelectionStrategy {

    /**
     * Crée les associations "santa -> personne recevant un cadeau" à partir d'une collection de personnes.
     *
     * @param persons la collection contenant les personnes dont on souhaite faire les tirages
     *
     * @return une map contenant les associations "santa -> personne recevant un cadeau"
     */
    fun makePairs(persons: Collection<Person>): Map<Person, Person>
}