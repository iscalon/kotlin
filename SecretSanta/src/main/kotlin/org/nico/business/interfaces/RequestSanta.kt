package org.nico.business.interfaces

/**
 * Représente l'interface qui sera interrogée pour faire le tirage des pères noëls dans une famille
 */
interface RequestSanta {

    /**
     * Crée l'ensemble des pères-noëls associés à la personne qui va recevoir leur cadeau
     *
     * @return une map dont la clé est la personne offrant le cadeau (père-noël) et la valeur est la personne recevant le cadeau
     */
    fun createSantas(): Map<Person, Person>
}

/**
 * Permet d'avoir la personne qui est santa
 */
fun Map.Entry<Person, Person>.getSanta() = this.key

/**
 * Permet d'avoir la personne qui reçoit le cadeau
 */
fun Map.Entry<Person, Person>.getReceiver() = this.value