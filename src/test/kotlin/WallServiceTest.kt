import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.netology.Comments
import ru.netology.Likes
import ru.netology.Post
import ru.netology.PostType
import ru.netology.Reposts
import ru.netology.Views
import ru.netology.WallService

class WallServiceTest {
    @Test
    fun add() {
        val post = Post(
            id = 0,
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
        val result = WallService.add(post);
        assertNotEquals(0, result.id)
    }

    //изменяем пост с существующим id, возвращается true;
    @Test
    fun updateExist() {
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