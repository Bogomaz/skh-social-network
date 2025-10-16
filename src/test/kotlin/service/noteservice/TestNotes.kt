package service.noteservice

import ru.netology.model.Privacy

data class NoteParamsToAdd(
    val text: String,
    val title: String,
    val viewPrivacy: Privacy = Privacy.EVERYONE,
    val commentPrivacy: Privacy = Privacy.EVERYONE
)

object TestNotesToAdd{
    val notes = listOf(
        NoteParamsToAdd("Hello, Kotlin!", "I learn Kotlin", Privacy.FRIENDS_ONLY, Privacy.FRIENDS_ONLY),
        NoteParamsToAdd("Это моя заметка. Комментируйте и читайте", "Привет, Интернет!", Privacy.FRIENDS_ONLY, Privacy.FRIENDS_ONLY),
        NoteParamsToAdd("Драконы любят рыбу, мясо, тепло и свежую траву.", "Как приручить дракона", Privacy.HUMANS_ONLY),
        NoteParamsToAdd("Это рассказ о том, как сделать удобную таблицу.", "UX-дизайн больших таблиц"),
        NoteParamsToAdd("Побывал в тех местах, куда не светит солнце.", "Как я провёл лето"),
        NoteParamsToAdd("Коты и собаки часто хорошо уживаются вместе", "Прикладное котоводство", Privacy.FRIENDS_OF_FRIENDS, Privacy.FRIENDS_OF_FRIENDS),
        NoteParamsToAdd("Я не знаю, что написать, но начну.", "Боязнь белого листа.", Privacy.HUMANS_ONLY, Privacy.HUMANS_ONLY)
    )
}


