package service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.netology.model.Comments
import ru.netology.model.Coordinates
import ru.netology.model.File
import ru.netology.model.FileAttachment
import ru.netology.model.Geotag
import ru.netology.model.GeotagAttachment
import ru.netology.model.Likes
import ru.netology.model.Photo
import ru.netology.model.PhotoAttachment
import ru.netology.model.Place
import ru.netology.model.PlaceType
import ru.netology.model.Post
import ru.netology.model.PostType
import ru.netology.model.Reposts
import ru.netology.model.Sticker
import ru.netology.model.StickerAttachment
import ru.netology.model.Video
import ru.netology.model.VideoAttachment
import ru.netology.model.Views
import ru.netology.service.WallService
import java.util.stream.Stream

class WallServiceTestAddPost {
    @BeforeEach
    fun clearBeforeTest() {
        WallService.clear()
    }

    companion object {
        @JvmStatic
        fun postsToAdding(): Stream<Arguments> = Stream.of(
            Arguments.of(
                Post(
                    id = 0,
                    ownerId = 1,
                    fromId = 1,
                    date = 1759092958,
                    text = "Post 1 content.",
                    replyOwnerId = 1,
                    replyPostId = 1,
                    friendsOnly = true,
                    comments = Comments(
                        count = 1,
                        canPost = true,
                        groupsCanPost = true
                    ),
                    likes = Likes(
                        count = 0,
                        userLikes = true,
                        canLike = true,
                        canPublish = true
                    ),
                    reposts = Reposts(
                        count = 4,
                        userReposted = true
                    ),
                    views = Views(
                        count = 10
                    ),
                    postType = PostType.POST,
                    isFavorite = true
                ), 0
            ),
            Arguments.of(
                Post(
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
                        StickerAttachment(
                            sticker = Sticker(
                                productId = 1610,
                                stickerId = 76072,
                                animationUrl = "https://vk.com/sticker/3-76072.json",
                                isAllowed = true
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
                                date = 1759661150, // Дата добавления в формате Unixtime
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
                ), 0
            ),
            Arguments.of(
                Post(), 0
            ),
            Arguments.of(
                Post(
                    id = 6545,
                    ownerId = 21,
                    fromId = 32,
                    date = 1759095462,
                    text = "Comment 1",
                    replyOwnerId = 1,
                    replyPostId = 1,
                    friendsOnly = true,
                    comments = Comments(),
                    likes = Likes(),
                    reposts = Reposts(),
                    views = Views(
                        count = 123
                    ),
                    postType = PostType.REPOST,
                    isFavorite = true
                ), 0
            ),
            Arguments.of(
                Post(
                    id = 4,
                    ownerId = 1,
                    fromId = 1,
                    date = 1759092958,
                    text = "Reply content.",
                    replyOwnerId = 1,
                    replyPostId = 1,
                    friendsOnly = true,
                    comments = Comments(
                        count = 1,
                        canPost = true,
                        groupsCanPost = true
                    ),
                    likes = Likes(
                        count = 10,
                        userLikes = true,
                        canLike = true,
                        canPublish = true
                    ),
                    reposts = Reposts(),
                    views = Views(
                        count = 5
                    ),
                    postType = PostType.REPLY,
                    isFavorite = false
                ), 0
            )
        )
    }

    @ParameterizedTest
    @MethodSource("postsToAdding")
    fun addPost(post: Post, unxpected: Int){
        val result = WallService.add(post)
        Assertions.assertNotEquals(unxpected, result.id)
    }
}