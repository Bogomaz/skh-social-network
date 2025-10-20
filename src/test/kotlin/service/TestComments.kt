package service

import ru.netology.model.Attachment
import ru.netology.model.Comment
import ru.netology.model.Coordinates
import ru.netology.model.File
import ru.netology.model.FileAttachment
import ru.netology.model.Geotag
import ru.netology.model.GeotagAttachment
import ru.netology.model.ParentType
import ru.netology.model.Photo
import ru.netology.model.PhotoAttachment
import ru.netology.model.Place
import ru.netology.model.PlaceType
import ru.netology.model.Privacy
import ru.netology.model.Sticker
import ru.netology.model.StickerAttachment
import ru.netology.model.Video
import ru.netology.model.VideoAttachment

class TestComments {
    data class recordCommentToAdd(
        val parentId: Int,
        val parentType: ParentType,
        val comment: Comment
    )

    // Список комментариев к одному посту
    object PostCommentsToAdd{
        val postComments = listOf(
            recordCommentToAdd(
                1,
                ParentType.POST,
                Comment(
                    fromId = 1, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Хочешь провести незабываемый вечер? Пиши +7-989-999-66-66! ", //Текст комментария
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
                    )
                )
            ), recordCommentToAdd(
                1,
                ParentType.POST,
                Comment(
                    fromId = 2, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Хорошая статья.", //Текст комментария
                )
            ),
            recordCommentToAdd(
                1,
                ParentType.POST,
                Comment(
                    fromId = 3, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Узнала в сегодня лет.", //Текст комментария
                )
            ),
            recordCommentToAdd(
                1,
                ParentType.POST,
                Comment(
                    fromId = 4, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Автор, лучше иди работать на завод.", //Текст комментария
                )
            ),
            recordCommentToAdd(
                1,
                ParentType.POST,
                Comment(
                    fromId = 8, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Свой совет себе посоветуй.", //Текст комментария
                    replyToComment = 4,
                    replyToUser = 4,
                )
            )
        )
    }


    object NoteCommentsToAdd{
        val noteComments = listOf(
            recordCommentToAdd(
                1,
                ParentType.NOTE,
                Comment(
                    fromId = 1, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Хочешь провести незабываемый вечер? Пиши +7-989-999-66-66! ", //Текст комментария
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
                    )
                )
            ), recordCommentToAdd(
                1,
                ParentType.NOTE,
                Comment(
                    fromId = 2, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Хорошая статья.", //Текст комментария
                )
            ),
            recordCommentToAdd(
                1,
                ParentType.NOTE,
                Comment(
                    fromId = 3, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Узнала в сегодня лет.", //Текст комментария
                )
            ),
            recordCommentToAdd(
                1,
                ParentType.NOTE,
                Comment(
                    fromId = 4, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Автор, лучше иди работать на завод.", //Текст комментария
                )
            ),
            recordCommentToAdd(
                1,
                ParentType.NOTE,
                Comment(
                    fromId = 8, //Идентификатор автора комментария
                    date = (System.currentTimeMillis() / 1000).toInt(), //Дата создания комментария в формате Unixtime
                    text = "Свой совет себе посоветуй.", //Текст комментария
                    replyToComment = 4,
                    replyToUser = 4,
                )
            )
        )
    }
}

