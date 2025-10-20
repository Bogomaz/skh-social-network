package service.commentservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.exception.CommentNotFoundException
import ru.netology.exception.NoteNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Note
import ru.netology.model.ParentType
import ru.netology.model.Post
import ru.netology.service.CommentService
import ru.netology.service.NoteService
import ru.netology.service.WallService

class EditCommmentTest {
    val noteService = NoteService<Note>()
    private lateinit var service: CommentService

    @BeforeEach
    fun setUp() {
        service = CommentService(WallService, noteService) // новый сервис для каждого теста
    }

    //Редактирование комментария к посту
    @Test
    fun editExistingPostCommentById() {
        val newCommentText = "This is a new comment text"
        WallService.add(Post(text = "Test post"))
        val comment = Comment(text = "Test comment", isDeleted = false)
        service.addComment(
            1,
            ParentType.POST,
            comment
        )

        val result = service.editComment(1, newCommentText)
        Assertions.assertEquals(newCommentText, result.text)
    }

    //Редактирование комментария к заметке
    @Test
    fun editExistingNoteCommentById() {
        val newCommentText = "This is a new comment text"
        noteService.add("Test note", "Note Title")
        val comment = Comment(text = "Test comment", isDeleted = false)
        service.addComment(
            1,
            ParentType.NOTE,
            comment
        )
        val result = service.editComment(1, newCommentText)
        Assertions.assertEquals(newCommentText, result.text)
    }

    //Попытка отредактировать несуществующий комментарий
    @Test
    fun editNonExistingComment() {
        val newCommentText = "This is a new comment text"
        Assertions.assertThrows(CommentNotFoundException::class.java) {
            service.editComment(999, newCommentText)
        }
    }

    //Попытка отредактировать удалённый комментарий
    @Test
    fun editDeletedComment() {
        val newCommentText = "This is a new comment text"
        WallService.add(Post(text = "Test post"))
        val comment = Comment(text = "Test comment", isDeleted = true)
        service.addComment(
            1,
            ParentType.POST,
            comment
        )
        Assertions.assertThrows(CommentNotFoundException::class.java) {
            service.editComment(1, newCommentText)
        }
    }
}