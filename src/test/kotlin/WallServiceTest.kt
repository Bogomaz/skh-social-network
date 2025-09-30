import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.netology.Comments
import ru.netology.Likes
import ru.netology.Post
import ru.netology.PostType
import ru.netology.Reposts
import ru.netology.Views
import ru.netology.WallService
import java.util.stream.Stream

class WallServiceTest {
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
                    postType = PostType.COMMENT,
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
        assertNotEquals(unxpected, result.id)
    }


    //изменяем пост с существующим id, возвращается true;
    @Test
    fun updateExist() {
        val post = Post(
            id = 1,
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
        );
        WallService.add(post);
        val result = WallService.update(post);
        assertEquals(true, result)
    }

    //изменяем пост с несуществующим id, возвращается false.
    @Test
    fun updateNonExist() {
        val post = Post(
            id = 1,
            ownerId = 10,
            fromId = 2,
            date = 1759092958,
            text = "Post 1 content.",
            replyOwnerId = 2,
            replyPostId = 5,
            friendsOnly = true,
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
        val result = WallService.update(post);
        assertEquals(false, result)
    }
}