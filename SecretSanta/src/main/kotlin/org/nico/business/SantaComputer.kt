package org.nico.business

import org.nico.business.interfaces.ObtainPerson
import org.nico.business.interfaces.Person
import org.nico.business.interfaces.RequestSanta
import org.nico.business.interfaces.SelectionStrategy
import org.nico.business.strategies.PartOneSelectionStrategy

class SantaComputer(
    private val personObtainer: ObtainPerson,
    private val selectionStrategy: SelectionStrategy = PartOneSelectionStrategy()
) : RequestSanta {

    override fun createSantas(): Map<Person, Person> {
        val wholeFamily = personObtainer.getExtendedFamily()
        return selectionStrategy.makePairs(wholeFamily)
    }
}