package service.commentservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.exception.CommentNotFoundException
import ru.netology.exception.ReasonNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Note
import ru.netology.model.ParentType
import ru.netology.model.Post
import ru.netology.model.Report
import ru.netology.service.CommentService
import ru.netology.service.NoteService
import ru.netology.service.WallService

class ReportCommentTest {
    val noteService = NoteService<Note>()
    val comments = CommentService(WallService, noteService)

    @Test
    fun reportCommentAndReasonExists() {
        val post = WallService.add(Post(text = "Test post"))
        val comment = Comment(text = "Test comment")
        val report = Report(id = 1, commentId = 1,3)
        comments.addComment(post.id, ParentType.POST, comment)
        val result = comments.reportComment(report)
        Assertions.assertEquals(1, result)
    }

    @Test
    fun reportCommentNotFound() {
        WallService.clear()
        val report = Report(id = 1, commentId = 10, 3)
        Assertions.assertThrows(CommentNotFoundException::class.java){
            comments.reportComment(report)
        }
    }

    @Test
    fun reportCommentReasonNotFounded() {
        WallService.clear()
        val post = WallService.add(Post(text = "Test post"))
        val comment = Comment(text = "Test comment")
        comments.addComment(post.id, ParentType.POST, comment)

        val report = Report(id = 1, commentId = 1, 23)
        Assertions.assertThrows(ReasonNotFoundException::class.java) {
            comments.reportComment(report)
        }
    }

}