package org.nico.business.strategies

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.nico.business.interfaces.Person
import org.nico.business.interfaces.SelectionStrategy
import org.nico.server.interfaces.FamilyMember

internal class PartOneSelectionStrategyTest {

    /**
     * Vérifie que s'il n'y a personne dans la famille alors on récupère un tirage 'vide'
     */
    @Test
    fun makePairsNobody() {
        val strategy: SelectionStrategy = PartOneSelectionStrategy()
        val pairs = strategy.makePairs(emptySet())
        kotlin.test.assertTrue(pairs.isEmpty(), "On ne doit pas avoir de tirage")
    }

    /**
     * Vérifie que s'il n'y a qu'une seule personne alors le tirage ne peut avoir lieu
     */
    @Test
    fun makePairsHomeAlone() {
        val strategy: SelectionStrategy = PartOneSelectionStrategy()
        assertThrows(IllegalArgumentException::class.java) { -> strategy.makePairs(setOf(FamilyMember("Kevin"))) }
    }

    /**
     * Vérifie que les associations sont OK
     */
    @Test
    fun makePairsOK() {
        val strategy: SelectionStrategy = PartOneSelectionStrategy()
        val romeo: Person = FamilyMember("Romeo")
        val juliette: Person = FamilyMember("Juliette")
        val pairs = strategy.makePairs(setOf(romeo, juliette))
        kotlin.test.assertEquals(juliette, pairs[romeo], "Le santa de Juliette doit être Roméo")
        kotlin.test.assertEquals(romeo, pairs[juliette], "Le santa de Roméo doit être Juliette")
    }
}