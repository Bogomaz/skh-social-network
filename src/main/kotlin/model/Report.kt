package ru.netology.model

data class Report(
    val id: Int,
    val commentId: Int,
    val ownerId: Int,
    val reason: Int
)

enum class Reason(val code: Int){
    SPAM(0),
    CHILD_PORNOGRAPHY(1),
    EXTREMISM(2),
    VIOLENCE(3),
    DRUG_PROPAGANDA(4),
    ADULT_MATERIAL(5),
    INSULT(7),
    SUICIDE_CALLS(8)
}