import org.nico.business.SantaComputer
import org.nico.business.interfaces.Person
import org.nico.business.interfaces.RequestSanta
import org.nico.business.strategies.PartThreeStrategy
import org.nico.business.strategies.PartTwoSelectionStrategy
import org.nico.server.interfaces.PersonObtainerAdapter

fun printSantas(santas: Map<Person, Person>) {
    for ((santa, receiver) in santas) {
        println("Santa for ${receiver.getName()} is ${santa.getName()}")
    }
}

fun main() {
    // Part One
    println("Part one")
    val appPartOne: RequestSanta = SantaComputer(PersonObtainerAdapter())
    var santas = appPartOne.createSantas()
    printSantas(santas)

    // Part Two
    println("Part two")
    val appPartTwo: RequestSanta =
        SantaComputer(PersonObtainerAdapter(), selectionStrategy = PartTwoSelectionStrategy())
    santas = appPartTwo.createSantas()
    printSantas(santas)

    // Part Three
    println("Part three")
    val appPartThree: RequestSanta = SantaComputer(PersonObtainerAdapter(), selectionStrategy = PartThreeStrategy())
    santas = appPartThree.createSantas()
    printSantas(santas)
}