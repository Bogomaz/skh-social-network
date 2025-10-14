package service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.netology.model.Comments
import ru.netology.model.Likes
import ru.netology.model.Post
import ru.netology.model.PostType
import ru.netology.model.Reposts
import ru.netology.model.Views
import ru.netology.service.WallService

class WallServiceTestUpdate {
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
        Assertions.assertEquals(true, result)
    }

    //изменяем пост с несуществующим id, возвращается false.
    @Test
    fun updateNonExist() {
        val post = Post(
            id = 4,
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
        Assertions.assertEquals(false, result)
    }


}