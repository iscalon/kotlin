package org.nico.business.strategies

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.nico.business.interfaces.Person
import org.nico.business.interfaces.SelectionStrategy
import org.nico.server.interfaces.FamilyMember

internal class PartThreeStrategyTest {

    /**
     * Vérifie que s'il n'y a personne dans la famille alors on récupère un tirage 'vide'
     */
    @Test
    fun makePairsNobody3() {
        val strategy: SelectionStrategy = PartThreeStrategy()
        val pairs = strategy.makePairs(emptySet())
        kotlin.test.assertTrue(pairs.isEmpty(), "On ne doit pas avoir de tirage")
    }

    /**
     * Vérifie que s'il n'y a qu'une seule personne alors le tirage ne peut avoir lieu
     */
    @Test
    fun makePairsHomeAlone3() {
        val strategy: SelectionStrategy = PartThreeStrategy()
        assertThrows(IllegalArgumentException::class.java) { -> strategy.makePairs(setOf(FamilyMember("Kevin"))) }
    }

    /**
     * Vérifie que les associations sont OK
     */
    @Test
    fun makePairsOK3() {
        val strategy: SelectionStrategy = PartThreeStrategy()
        val romeo: Person = FamilyMember("Romeo")
        val juliette: Person = FamilyMember("Juliette")
        val pairs = strategy.makePairs(setOf(romeo, juliette))
        kotlin.test.assertEquals(juliette, pairs[romeo], "Le santa de Juliette doit être Roméo")
        kotlin.test.assertEquals(romeo, pairs[juliette], "Le santa de Roméo doit être Juliette")
    }

    /**
     * Vérifie que les associations sont NOK car un santa est membre direct de la famille
     * et il n'y a personne d'autre d'éligible
     */
    @Test
    fun makePairsNOK3NoElegible() {
        val strategy: SelectionStrategy = PartThreeStrategy()
        val romeo: Person = FamilyMember("Romeo")
        val juliette: Person = FamilyMember("Juliette")
        val tristan: Person = FamilyMember("Tristan", directFamilyMembers = setOf(romeo, juliette))
        assertThrows(IllegalArgumentException::class.java) { -> strategy.makePairs(setOf(romeo, juliette, tristan)) }
    }

    /**
     * Vérifie que les associations sont NOK car un santa est un membre direct de la famille
     * et il y a une seule personne éligible
     */
    @Test
    fun makePairsNOK3Elegible() {
        val strategy: SelectionStrategy = PartThreeStrategy()
        val romeo: Person = FamilyMember("Romeo")
        val juliette: Person = FamilyMember("Juliette")
        val tristan: Person = FamilyMember("Tristan", directFamilyMembers = setOf(romeo, juliette))
        val iseult: Person = FamilyMember("Iseult")
        val pairs = strategy.makePairs(setOf(romeo, juliette, tristan, iseult))
        kotlin.test.assertEquals(tristan, pairs[iseult], "Le santa de Tristan doit être Iseult")
    }
}