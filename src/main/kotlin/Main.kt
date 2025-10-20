package ru.netology

import ru.netology.exception.CommentNotFoundException
import ru.netology.exception.RecordNotFoundException
import ru.netology.exception.ReasonNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Comments
import ru.netology.model.Coordinates
import ru.netology.model.File
import ru.netology.model.FileAttachment
import ru.netology.model.Geotag
import ru.netology.model.GeotagAttachment
import ru.netology.model.Note
import ru.netology.model.ParentType
import ru.netology.model.Photo
import ru.netology.model.PhotoAttachment
import ru.netology.model.Place
import ru.netology.model.PlaceType
import ru.netology.model.Post
import ru.netology.model.Privacy
import ru.netology.model.Report
import ru.netology.model.Sticker
import ru.netology.model.StickerAttachment
import ru.netology.model.Video
import ru.netology.model.VideoAttachment
import ru.netology.service.CommentService
import ru.netology.service.NoteService
import ru.netology.service.WallService


fun main() {
    val noteService = NoteService<Note>()
    val commentService = CommentService(WallService, noteService)
    val post = Post(
        date = 1759092958,
        text = "Post 1 content.",
        attachments = listOf(
            PhotoAttachment(
                photo = Photo(
                    id = 1,
                    ownerId = 1,
                    photo130 = "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-465x260.jpg",
                    photo604 = "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-1.jpg"
                )
            ),
            VideoAttachment(
                video = Video(
                    id = 1,
                    ownerId = 1,
                    title = "Cats",
                    description = "Funny cats",
                    duration = 30,
                    views = 2_900_000,
                    player = "https://vkvideo.ru/video-158579430_456262028"
                )
            ),
            FileAttachment(
                file = File(
                    id = 1,
                    ownerId = 1, // идентификатор владельца видео
                    title = "Abronia graminea", // название
                    size = 98_304, //Размер файла в байтах.
                    ext = "webp", // Расширение файла.
                    url = "https://terrarium-online.com/wp-content/uploads/2023/02/abronia-graminea.webp", // Адрес файла, по которому его можно загрузить.
                    date = 1759661150 // Дата добавления в формате Unixtime
                )
            ),
            StickerAttachment(
                sticker = Sticker(
                    productId = 1610,
                    stickerId = 76072,
                    animationUrl = "https://vk.com/sticker/3-76072.json",
                    isAllowed = true
                )
            ),
            GeotagAttachment(
                geotag = Geotag(
                    type = PlaceType.SIGHT,
                    coordinates = Coordinates(
                        latitude = 47.416481,
                        longitude = 40.086535
                    ),
                    place = Place(
                        type = 1,
                        groupId = 996167,
                        groupPhoto = "https://sun9-25.userapi.com/s/v1/ig2/x9jACJlLoXrVuUOehcbNBhHZVBKAm2pq9CzJoDsSkJMO1vAk6qQ8uU4mda_oIc7dUW7N6yxlJZiGQINeTNL8SArB.jpg?quality=95&as=32x32,48x48,72x72,108x108,160x160,240x240,360x360,480x480,540x540,640x640,720x720,1080x1080,1280x1280,1300x1300&from=bu&cs=1280x0",
                        checkins = 10,
                        updated = 2,
                        address = 423
                    )
                )
            ),
        ),
    )
    val note = Note(
        text = "I learn Kotlin",
        title = "Hello, Kotlin!",
        viewPrivacy = Privacy.FRIENDS_ONLY,
        comments = Comments(
            commentPrivacy = Privacy.FRIENDS_ONLY
        )
    )
    val comment1 = Comment(text = "Hello! I read your blog.")
    val comment2 = Comment(
        id = 0,
        fromId = 1, //Идентификатор автора комментария
        date = 1759661150, //Дата создания комментария в формате Unixtime
        text = "It's cool!.", //Текст комментария
        replyToUser = 12, //Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо).
        replyToComment = 0, // Идентификатор комментария, в ответ на который оставлен текущий (если применимо).
        attachments = listOf(
            PhotoAttachment(
                photo = Photo(
                    id = 1,
                    ownerId = 1,
                    photo130 = "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-465x260.jpg",
                    photo604 = "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-1.jpg"
                )
            )
        )
    )
    //Добавим тестовый пост
    val addedPost = WallService.add(post)

    //Добавим тестовую заметку
    val addedNote = noteService.add(note.text, note.title, Privacy.FRIENDS_ONLY)

    //Добавим комментарий к посту, и к заметке
    val postComment1 = commentService.addComment(1, ParentType.POST, comment1)
    val postComment2 = commentService.addComment(1, ParentType.POST, comment2)

    val noteComment1 = commentService.addComment(1, ParentType.NOTE, comment1)
    val noteComment2 = commentService.addComment(1, ParentType.NOTE, comment2)

    println(addedPost)
    println(addedNote)
    println("Post comments: \n $postComment1 \n $postComment2")
    println("Note comments: \n $noteComment1 \n $noteComment2")

    //Пожалуемся на комментарий
    val report = Report(
        id = 1,
        commentId = 1,
        reason = 8
    )

    println(commentService.reportComment(report))

//    val notes = listOf(
//        // text, title, viewPrivacy, commentPrivacy
//        arrayOf("Hello, Kotlin!", "I learn Kotlin", Privacy.FRIENDS_ONLY, Privacy.FRIENDS_ONLY),
//        arrayOf(
//            "Это моя заметка. Комментируйте и читайте",
//            "Привет, Интернет!",
//            Privacy.FRIENDS_ONLY,
//            Privacy.FRIENDS_ONLY
//        ),
//        arrayOf(
//            "Драконы любят рыбу, мясо, тепло и свежую траву.",
//            "Как приручить дракона",
//            Privacy.HUMANS_ONLY,
//            Privacy.FRIENDS_ONLY
//        ),
//        arrayOf(
//            "Это моя заметка. Комментируйте и читайте",
//            "Привет, Интернет!",
//            Privacy.HUMANS_ONLY,
//            Privacy.HUMANS_ONLY
//        ),
//        arrayOf(
//            "Побывал в тех местах, куда не светит солнце.",
//            "Как я провёл лето",
//            Privacy.USER_ONLY,
//            Privacy.USER_ONLY
//        ),
//        arrayOf(
//            "Коты и собаки иногда хорошо уживаются вместе",
//            "Прикладное котоводство",
//            Privacy.FRIENDS_OF_FRIENDS,
//            Privacy.FRIENDS_OF_FRIENDS
//        ),
//        arrayOf("Я не знаю, что написать, но начну.", "Боязнь белого листа.", Privacy.HUMANS_ONLY, Privacy.HUMANS_ONLY)
//    )
//
//    val results = notes.map { (text, title, viewPrivacy, commentPrivacy) ->
//        noteService.add(
//            newText = text as String,
//            newTitle = title as String,
//            newViewPrivacy = viewPrivacy as Privacy,
//            newCommentPrivacy = commentPrivacy as Privacy
//        )
//    }
//    val ids = listOf(1, 3, 5)

//    println(noteService.get(ids, 0))
}
