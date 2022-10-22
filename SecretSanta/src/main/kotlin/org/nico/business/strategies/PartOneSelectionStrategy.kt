package org.nico.business.strategies

import org.nico.business.interfaces.Person
import org.nico.business.interfaces.SelectionStrategy
import org.nico.business.interfaces.findRandomPerson

/**
 * Choose a Secret Santa for everyone given a list of all the members of your extended family. Obviously, a person cannot be their own Secret Santa
 */
class PartOneSelectionStrategy : SelectionStrategy {
    override fun makePairs(persons: Collection<Person>): Map<Person, Person> {
        if (persons.isEmpty()) {
            return emptyMap()
        }

        val result = mutableMapOf<Person, Person>()
        persons.forEach { santa -> // Chacune des personnes de la liste va devenir santa de quelqu'un
            val receiver =
                persons.filter { it != santa && !result.values.contains(it) } // Dans la liste des personnes on va retirer la personne actuellement santa et aussi celles à qui un santa a déjà été attribué
                    .findRandomPerson()
                    ?: throw IllegalArgumentException("Il faut au moins 2 personnes pour pouvoir tirer les pères-noëls") // On tire une personne au hasard dans les personnes restantes
            result[santa] = receiver // On fait l'association 'santa -> receiver'
        }
        return result
    }
}