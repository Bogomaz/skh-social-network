package service

import ru.netology.model.Comments
import ru.netology.model.Note
import ru.netology.model.Privacy

object TestNotes {
    val notes = listOf(
        Note(
            text = "Hello, Kotlin!",
            title = "I learn Kotlin",
            viewPrivacy = Privacy.FRIENDS_ONLY,
            comments = Comments(
                count = 10,
                commentPrivacy = Privacy.FRIENDS_ONLY
            )
        ),

        Note(
            text = "Это моя заметка. Комментируйте и читайте",
            title = "Привет, Интернет!",
            viewPrivacy = Privacy.FRIENDS_ONLY,
            comments = Comments(
                commentPrivacy = Privacy.FRIENDS_ONLY
            )
        ),

        Note(
            text = "Драконы любят рыбу, мясо, тепло и свежую траву.",
            title = "Как приручить дракона",
            viewPrivacy = Privacy.HUMANS_ONLY,
            comments = Comments(
                commentPrivacy = Privacy.FRIENDS_ONLY
            )
        ),

        Note(
            id = 0,
            ownerId = 10,
            fromId = 10,
            date = 1759092958,
            text = "Это моя заметка. Комментируйте и читайте",
            viewPrivacy = Privacy.HUMANS_ONLY,
            comments = Comments(
                count = 0, //Количество комментариев к записи
                readCommentsCount = 0, //Количество прочитанных комментариев.
                commentPrivacy = Privacy.HUMANS_ONLY, //Уровень доступа к комментированию заметки.
                canClose = true, // может ли текущий пользователь закрыть комментарии к записи;
                canOpen = false // может ли текущий пользователь открыть комментарии к записи.
            ),
            title = "Привет, Интернет!"
        )
    )

}