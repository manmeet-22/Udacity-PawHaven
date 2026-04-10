package com.udacity.pawhaven.data

internal object Repository {

    val validVolunteers = setOf(
        newVolunteer("Grace", "Johnson", 28),
        newVolunteer("Samuel", "Okoye", 35),
        newVolunteer("Amina", "Diallo", 30),
        newVolunteer("Marie", "Dupont", 26)
    )

    val pets = mutableListOf<Animal>(
        Dog("Bella", 2, "A friendly Golden Retriever who loves to play fetch and is great with kids."),
        Cat("Max", 3, "A calm and affectionate Tabby cat who enjoys sunbathing and soft purring."),
        Elephant("Charlie", 5, "A gentle young elephant rescued from a sanctuary, now enjoying his wide-open space."),
        Bird("Cindy", 4, "A colorful canary with a beautiful singing voice that brightens everyone's day."),
        Lion("Darrel", 4, "A majestic young lion who was found abandoned and is now the king of our heart."),
        Parrot("Parry", 1, "A talkative parrot who can mimic almost any sound and loves to say 'Hello!'.")
    )

    var user : Person? = null

    fun getPetById(id: String): Animal? {
        return pets.find { it.id == id }
    }
}