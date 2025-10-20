package service.commentservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.exception.CommentNotFoundException
import ru.netology.exception.RecordNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Note
import ru.netology.model.ParentType
import ru.netology.model.Post
import ru.netology.service.CommentService
import ru.netology.service.NoteService
import ru.netology.service.WallService

class DeleteCommentTest {
    val noteService = NoteService<Note>()
    private lateinit var service: CommentService

    @BeforeEach
    fun setUp() {
        service = CommentService(WallService, noteService) // новый сервис для каждого теста
    }

    @Test // Создать один комментарий к существующему посту
    fun deleteCommentToExistingPost() {
        WallService.add(Post(text = "Test post"))
        val comment = Comment(text = "Test comment")
        service.addComment(
            1,
            ParentType.POST,
            comment
        )
        val result = service.deleteComment(1)
        Assertions.assertEquals(true, result)
    }

    @Test // Создать один комментарий к существующей заметке
    fun deleteCommentToExistingNote() {
        noteService.add("Test note", "Title")
        val comment = Comment(text = "Test comment")
        service.addComment(1, ParentType.NOTE, comment)
        val result = service.deleteComment(1)
        Assertions.assertEquals(true, result)
    }

    @Test // Попытаться удалить несуществующий комментарий
    fun deleteNotFoundedPostComment() {
        Assertions.assertThrows(CommentNotFoundException::class.java) {
            service.deleteComment(999)
        }
    }
}