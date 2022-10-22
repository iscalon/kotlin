package org.nico.server.interfaces

import org.nico.business.interfaces.DirectFamilyMembers
import org.nico.business.interfaces.ObtainPerson
import org.nico.business.interfaces.Person
import org.nico.business.interfaces.PreviousYearsSanta

/**
 * Les objets remontés par le service d'obtention des membres de la famille
 */
data class FamilyMember(
    private val name: String,
    private val previousSantas: List<Person> = listOf(),
    private val directFamilyMembers: Set<Person> = setOf()
) : Person, PreviousYearsSanta, DirectFamilyMembers {
    override fun getName() = name
    override fun getPreviousSantas() = previousSantas
    override fun getDirectFamilyMembers(): Set<Person> = directFamilyMembers
}

/**
 * La classe chargée d'aller remonter les informations nécessaires
 */
class PersonObtainerAdapter : ObtainPerson {

    override fun getExtendedFamily(): Collection<Person> {

        return listOf(
            FamilyMember("Leonardo"),
            FamilyMember("Michelangelo"),
            FamilyMember("Donatello"),
            FamilyMember("Raphaël"),
            FamilyMember("April")
        )
    }
}