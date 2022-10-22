package org.nico.business.strategies

import org.nico.business.interfaces.DirectFamilyMembers
import org.nico.business.interfaces.Person
import org.nico.business.interfaces.SelectionStrategy
import org.nico.business.interfaces.findRandomPerson

/**
 * Choose a Secret Santa for everyone given a list of all the members of your extended family. Obviously, a person cannot be their own Secret Santa.
 * Families usually get gifts for members of their immediate family, so it doesn’t make a lot of sense for anyone to be a Secret Santa for a member of their immediate family (spouse, parents, or children).
 */
class PartThreeStrategy : SelectionStrategy {
    override fun makePairs(persons: Collection<Person>): Map<Person, Person> {
        if (persons.isEmpty()) {
            return emptyMap()
        }

        val result = mutableMapOf<Person, Person>()
        persons.forEach { santa -> // Chacune des personnes de la liste va devenir santa de quelqu'un
            val receiver =
                persons.filter { it != santa && !result.values.contains(it) } // Dans la liste des personnes on va retirer la personne actuellement santa et aussi celles à qui un santa a déjà été attribué
                    .filter { canHaveSanta(it, santa) } // Pas de santa dans la famille directe
                    .findRandomPerson()
                    ?: throw IllegalArgumentException("Il faut au moins 2 personnes éligibles pour pouvoir tirer les pères-noëls") // On tire une personne au hasard dans les personnes restantes
            result[santa] = receiver // On fait l'association 'santa -> receiver'
        }
        return result
    }

    private fun canHaveSanta(person: Person, santa: Person): Boolean {
        val directFamilyMembers = (person as? DirectFamilyMembers
            ?: throw IllegalStateException("Impossible de déterminer les membres de la famille directe")).getDirectFamilyMembers()

        return !directFamilyMembers.contains(santa)
    }
}