package org.nico.business.interfaces


/**
 * Représente une personne physique
 */
interface Person {
    fun getName(): String
}

/**
 * Interface permettant d'obtenir les santas des autres années
 */
interface PreviousYearsSanta : Person {
    /**
     * Retourne la liste des pères noël des années précédentes, les plus récents sont en fin de liste.
     */
    fun getPreviousSantas(): List<Person>
}

/**
 * Interface ajoutant la fonctionnalité d'obtention des membres directs de la famille
 */
interface DirectFamilyMembers : Person {
    /**
     * Retourne l'ensemble des membres directs de la famille
     */
    fun getDirectFamilyMembers(): Set<Person>
}

/**
 * Le service permettant d'obtenir les membres d'une famille
 */
interface ObtainPerson {

    /**
     * Retourne la collection de tous les membres de la famille étendue
     */
    fun getExtendedFamily(): Collection<Person>
}