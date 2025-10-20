package service.commentservice

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertThrows
import ru.netology.exception.RecordNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Note
import ru.netology.model.ParentType
import ru.netology.model.Post
import ru.netology.service.CommentService
import ru.netology.service.NoteService
import ru.netology.service.WallService

class GetCommentsTest {
    val noteService = NoteService<Note>()
    private lateinit var service: CommentService

    @BeforeEach
    fun setUp() {
        service = CommentService(WallService, noteService) // новый сервис для каждого теста
    }

    @Test // Комментарии к посту
    fun getCommentsForPost() {
        val postId = 1
        WallService.add(Post(text = "Test post"))
        val expectedComments = listOf(
            Comment(id = 1, parentId = postId, parentType = ParentType.POST, text = "New Post Comment 1"),
            Comment(id = 2, parentId = postId, parentType = ParentType.POST, text = "New Post Comment 2")
        )
        // Добавляем комментарии к посту
        expectedComments.forEach { comment ->
            service.addComment(parentId = postId, parentType = ParentType.POST, comment = comment)
        }

        val result = service.getComments(parentId = postId, parentType = ParentType.POST)

        assertEquals(expectedComments.size, result.size)
        assertEquals(expectedComments, result)
    }

    @Test // Комментаарии к заметки
    fun getCommentsForNote() {
        val noteId = 1
        noteService.add("Test note", "Note Title")
        val expectedComments = listOf(
            Comment(id = 1, parentId = noteId, parentType = ParentType.NOTE, text = "New Note Comment 1"),
            Comment(id = 2, parentId = noteId, parentType = ParentType.NOTE, text = "New Note Comment 2")
        )
        // Добавляем комментарии к заметки
        expectedComments.forEach { comment ->
            service.addComment(parentId = noteId, parentType = ParentType.NOTE, comment = comment)
        }

        val result = service.getComments(parentId = noteId, parentType = ParentType.NOTE)

        assertEquals(expectedComments.size, result.size)
        assertEquals(expectedComments, result)
    }

    @Test
    fun getCommentsThrowsExceptionWhenParentNotFound() {
        // Arrange
        val nonExistentId = 999

        assertThrows<RecordNotFoundException> {
            service.getComments(parentId = nonExistentId, parentType = ParentType.POST)
        }
    }

    @Test
    fun getDeletedComments() {
        // Arrange
        val postId = 1
        val activeComment = Comment(id = 1, parentId = postId, parentType = ParentType.POST, text = "Active")
        val deletedComment = Comment(id = 2, parentId = postId, parentType = ParentType.POST, text = "Deleted", isDeleted = true)

        // Добавляем оба комментария
        service.addComment(parentId = postId, parentType = ParentType.POST, comment = activeComment)
        service.addComment(parentId = postId, parentType = ParentType.POST, comment = deletedComment)

        val result = service.getComments(parentId = postId, parentType = ParentType.POST)

        assertEquals(1, result.size)
        assertEquals(activeComment, result.first())
        assertFalse(result.any { it.isDeleted })
    }

    @Test
    fun getEmptyCommentsList() {
        noteService.add("Test note", "Note Title")
        val postId = 1
        val result = service.getComments(parentId = postId, parentType = ParentType.POST)
        assertTrue(result.isEmpty())
    }

}