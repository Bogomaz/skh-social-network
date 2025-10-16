package service.wallservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.exception.CommentNotFoundException
import ru.netology.exception.ReasonNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Post
import ru.netology.model.Report
import ru.netology.service.WallService

class WallServiceTestReportComment {
    @Test
    fun reportCommentAndReasonExists() {
        WallService.clear()
        val post = WallService.add(Post(text = "Test post"))
        val comment = Comment(postId = post.id, text = "Test comment")
        val report = Report(id = 1, commentId = 1, ownerId = 1, 3)
        WallService.createComment(post.id, comment)
        val result = WallService.reportComment(report)
        Assertions.assertEquals(1, result)
    }

    @Test
    fun reportCommentNotFound() {
        WallService.clear()
        val report = Report(id = 1, commentId = 10, ownerId = 1, 3)
        Assertions.assertThrows(CommentNotFoundException::class.java){
            WallService.reportComment(report)
        }
    }

    @Test
    fun reportCommentReasonNotFounded() {
        WallService.clear()
        val post = WallService.add(Post(text = "Test post"))
        val comment = Comment(postId = post.id, text = "Test comment")
        WallService.createComment(post.id, comment)

        val report = Report(id = 1, commentId = 1, ownerId = 1, 23)
        Assertions.assertThrows(ReasonNotFoundException::class.java) {
            WallService.reportComment(report)
        }
    }

}