package service.wallservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.exception.PostNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Post
import ru.netology.service.WallService

class WallServiceTestCreateComment {
    @Test
    fun сreateCommentToExistingPost() {
        WallService.clear()
        val post = WallService.add(Post(text = "Test post"))
        val comment = Comment(postId = post.id, text = "Test comment")
        val result = WallService.createComment(post.id, comment)
        Assertions.assertEquals("Test comment", result.text)
        Assertions.assertTrue(result.id > 0)
    }

    @Test
    fun сreateCommentToNotFoundedPost() {
        WallService.clear()
        val comment = Comment(postId = 999, text = "Should fail")
        Assertions.assertThrows(PostNotFoundException::class.java) {
            WallService.createComment(999, comment)
        }
    }

}