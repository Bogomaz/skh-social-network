package ru.netology

import ru.netology.Coordinates
import ru.netology.Place
import kotlin.Boolean
import kotlin.Int
import kotlin.String

fun main() {
    val post = Post(
        id = 0,
        ownerId = 10,
        fromId = 2,
        date = 1759092958,
        text = "Post 1 content.",
        replyOwnerId = 2,
        replyPostId = 5,
        friendsOnly = true,
        attachments = listOf(
            PhotoAttachment(
                photo = Photo(
                    id = 1,
                    ownerId = 1,
                    photo130 = "https://vk.com/some_photo_link",
                    photo604 = "https://vk.com/another_photo_link"
                )
            ),
            VideoAttachment(
                video = Video(
                    id = 1,
                    ownerId = 1,
                    title = "Cats",
                    description = "Funny cats",
                    duration =30,
                    views = 2_900_000,
                    player = "https://vkvideo.ru/video-158579430_456262028"
                )
            ),
            StickerAttachment(
                sticker = Sticker(
                    productId = 1, //Идентификатор набора
                    stickerId = 1, // Идентификатор стикера
                    animationUrl = "https://vk.com/some_sticker_link", // URL анимации стикера
                    isAllowed = true // Информация о том, доступен ли стикер.
                )
            ),
            FileAttachment(
                file = File(
                    id = 1,
                    owner_id = 1, // идентификатор владельца видео
                    title = "My cat", // название
                    size = 2300, //Размер файла в байтах.
                    ext = "jpeg", // Расширение файла.
                    url = "https://vk.com/some_file_address", // Адрес файла, по которому его можно загрузить.
                    date = 1759092958, // Дата добавления в формате Unixtime
                )
            ),
            GeotagAttachment(
                geotag = Geotag(
                    type = PlaceType.JOB, //Тип места.
                    coordinates = Coordinates(
                        latitude = 47.456293,
                        longitude = 40.059049
                    ),
                    place = Place(
                        type = 1, //тип чекина
                        groupId = 1, //идентификатор сообщества
                        groupPhoto = "https://vk.com/mini-img", //URL миниатюры главной фотографии сообщества
                        checkins = 19, //количество чекинов
                        updated = 1759092958, // время последнего чекина в Unixtime;
                        address = 334 // адрес.
                    )
                )
            ),
        ),
        comments = Comments(
            count = 3,
            canPost = true,
            groupsCanPost = false
        ),
        likes = Likes(
            count = 2,
            userLikes = true,
            canLike = true,
            canPublish = true
        ),
        reposts = Reposts(
            count = 4,
            userReposted = true
        ),
        views = Views(
            count = 123
        ),
        postType = PostType.POST,
        isFavorite = true
    )

//    val repost = Post (
//            id = 0,
//    ownerId = 20,
//    fromId = 5,
//    date = 1759093999,
//    text = "Post 2 content.",
//    replyOwnerId = 3,
//    replyPostId = 7,
//    friendsOnly = false,
//    comments = Comments(
//        count = 0,
//        canPost = false,
//        groupsCanPost = true
//    ),
//    likes = Likes(
//        count = 4,
//        userLikes = false,
//        canLike = false,
//        canPublish = false
//    ),
//    reposts = Reposts(
//        count = 0,
//        userReposted = false
//    ),
//    views = Views(
//        count = 10
//    ),
//    postType = PostType.COMMENT,
//    isFavorite = false
//    )
//
//    val testPost3 = Post(
//        id = 0,
//        ownerId = 30,
//        fromId = 8,
//        date = 1759094999,
//        text = "Post 3 content.",
//        replyOwnerId = 4,
//        replyPostId = 9,
//        friendsOnly = true,
//        comments = Comments(
//            count = 1,
//            canPost = true,
//            groupsCanPost = false
//        ),
//        likes = Likes(
//            count = 5,
//            userLikes = true,
//            canLike = true,
//            canPublish = false
//        ),
//        reposts = Reposts(
//            count = 2,
//            userReposted = false
//        ),
//        views = Views(
//            count = 50
//        ),
//        postType = PostType.REPLY,
//        isFavorite = true
//    )
//    val testPost4 = Post(
//        id = 0,
//        ownerId = 40,
//        fromId = 12,
//        date = 1759095999,
//        text = "Post 4 content.",
//        replyOwnerId = 5,
//        replyPostId = 11,
//        friendsOnly = false,
//        comments = Comments(
//            count = 7,
//            canPost = false,
//            groupsCanPost = true
//        ),
//        likes = Likes(
//            count = 8,
//            userLikes = false,
//            canLike = true,
//            canPublish = true
//        ),
//        reposts = Reposts(
//            count = 3,
//            userReposted = true
//        ),
//        views = Views(
//            count = 200
//        ),
//        postType = PostType.POSTPONE,
//        isFavorite = false
//    )
//
//    val testPost5 = Post(
//        id = 0,
//        ownerId = 50,
//        fromId = 15,
//        date = 1759096999,
//        text = "Post 5 content.",
//        replyOwnerId = 6,
//        replyPostId = 13,
//        friendsOnly = true,
//        comments = Comments(
//            count = 2,
//            canPost = true,
//            groupsCanPost = false
//        ),
//        likes = Likes(
//            count = 12,
//            userLikes = true,
//            canLike = true,
//            canPublish = false
//        ),
//        reposts = Reposts(
//            count = 1,
//            userReposted = false
//        ),
//        views = Views(
//            count = 75
//        ),
//        postType = PostType.SUGGEST,
//        isFavorite = true
//    )
//
//    val testPost6 = Post(
//        id = 0,
//        ownerId = 30,
//        fromId = 8,
//        date = 1759095011,
//        text = "Post 3 content changed.",
//        replyOwnerId = 12,
//        replyPostId = 41,
//        friendsOnly = false,
//        comments = Comments(
//            count = 1,
//            canPost = true,
//            groupsCanPost = false
//        ),
//        likes = Likes(
//            count = 5,
//            userLikes = true,
//            canLike = true,
//            canPublish = false
//        ),
//        reposts = Reposts(
//            count = 2,
//            userReposted = false
//        ),
//        views = Views(
//            count = 50
//        ),
//        postType = PostType.REPLY,
//        isFavorite = true
//    )

    println(WallService.add(post))
//    println(WallService.add(repost))
//    println(WallService.add(testPost3))
//    println(WallService.add(testPost4))
//    println(WallService.add(testPost5))

//    WallService.update(testPost6);


}