package org.nico.business.strategies

import org.nico.business.interfaces.Person
import org.nico.business.interfaces.PreviousYearsSanta
import org.nico.business.interfaces.SelectionStrategy
import org.nico.business.interfaces.findRandomPerson
import kotlin.math.max
import kotlin.math.min

/**
 * Choose a Secret Santa for everyone given a list of all the members of your extended family. Obviously, a person cannot be their own Secret Santa.
 * Family member can only have the same Secret Santa once every 3 years
 */
class PartTwoSelectionStrategy(private val nbYears: Int = 3) : SelectionStrategy {
    override fun makePairs(persons: Collection<Person>): Map<Person, Person> {
        if (persons.isEmpty()) {
            return emptyMap()
        }

        val result = mutableMapOf<Person, Person>()
        persons.forEach { santa -> // Chacune des personnes de la liste va devenir santa de quelqu'un
            val receiver =
                persons.filter { it != santa && !result.values.contains(it) } // Dans la liste des personnes on va retirer la personne actuellement santa et aussi celles à qui un santa a déjà été attribué
                    .filter { canHaveSantaAgain(it, santa) } // Pas 'nbYears' ans d'affilée le même santa
                    .findRandomPerson()
                    ?: throw IllegalArgumentException("Il faut au moins 2 personnes éligibles pour pouvoir tirer les pères-noëls") // On tire une personne au hasard dans les personnes restantes
            result[santa] = receiver // On fait l'association 'santa -> receiver'
        }
        return result
    }

    private fun canHaveSantaAgain(person: Person, santa: Person): Boolean {
        val previousSantas = (person as? PreviousYearsSanta
            ?: throw IllegalStateException("Impossible de déterminer les pères noëls précédents")).getPreviousSantas()

        val size = previousSantas.size
        val lastsYears = max(0, min(size, nbYears - 1))
        val lastSantas = previousSantas.takeLast(lastsYears)

        return !lastSantas.contains(santa)
    }
}

