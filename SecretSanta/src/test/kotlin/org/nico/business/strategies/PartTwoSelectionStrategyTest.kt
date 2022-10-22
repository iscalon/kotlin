package org.nico.business.strategies

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.nico.business.interfaces.Person
import org.nico.business.interfaces.SelectionStrategy
import org.nico.server.interfaces.FamilyMember

internal class PartTwoSelectionStrategyTest {

    /**
     * Vérifie que s'il n'y a personne dans la famille alors on récupère un tirage 'vide'
     */
    @Test
    fun makePairsNobody2() {
        val strategy: SelectionStrategy = PartTwoSelectionStrategy()
        val pairs = strategy.makePairs(emptySet())
        kotlin.test.assertTrue(pairs.isEmpty(), "On ne doit pas avoir de tirage")
    }

    /**
     * Vérifie que s'il n'y a qu'une seule personne alors le tirage ne peut avoir lieu
     */
    @Test
    fun makePairsHomeAlone2() {
        val strategy: SelectionStrategy = PartTwoSelectionStrategy()
        assertThrows(IllegalArgumentException::class.java) { -> strategy.makePairs(setOf(FamilyMember("Kevin"))) }
    }

    /**
     * Vérifie que les associations sont OK
     */
    @Test
    fun makePairsOK2() {
        val strategy: SelectionStrategy = PartTwoSelectionStrategy()
        val romeo: Person = FamilyMember("Romeo")
        val juliette: Person = FamilyMember("Juliette")
        val pairs = strategy.makePairs(setOf(romeo, juliette))
        kotlin.test.assertEquals(juliette, pairs[romeo], "Le santa de Juliette doit être Roméo")
        kotlin.test.assertEquals(romeo, pairs[juliette], "Le santa de Roméo doit être Juliette")
    }

    /**
     * Vérifie que les associations sont NOK car un santa a déjà été associé à une personne dans les 3 dernières années
     * et il n'y a personne d'autre d'éligible
     */
    @Test
    fun makePairsNOK2NoElegible() {
        val strategy: SelectionStrategy = PartTwoSelectionStrategy()
        val romeo: Person = FamilyMember("Romeo")
        val juliette: Person = FamilyMember("Juliette")
        val tristan: Person = FamilyMember("Tristan", listOf(romeo, juliette, romeo))
        assertThrows(IllegalArgumentException::class.java) { -> strategy.makePairs(setOf(romeo, juliette, tristan)) }
    }

    /**
     * Vérifie que les associations sont NOK car un santa a déjà été associé à une personne dans les 3 dernières années
     * et il y a une seule personne éligible
     */
    @Test
    fun makePairsNOK2Elegible() {
        val strategy: SelectionStrategy = PartTwoSelectionStrategy()
        val romeo: Person = FamilyMember("Romeo")
        val juliette: Person = FamilyMember("Juliette")
        val tristan: Person = FamilyMember("Tristan", listOf(romeo, juliette, romeo))
        val iseult: Person = FamilyMember("Iseult")
        val pairs = strategy.makePairs(setOf(romeo, juliette, tristan, iseult))
        kotlin.test.assertEquals(tristan, pairs[iseult], "Le santa de Tristan doit être Iseult")
    }
}