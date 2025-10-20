package service.commentservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.exception.RecordNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Note
import ru.netology.model.ParentType
import ru.netology.model.Post
import ru.netology.service.CommentService
import ru.netology.service.NoteService
import ru.netology.service.WallService
import service.TestComments

class AddCommentTest {
    val noteService = NoteService<Note>()
    private lateinit var service: CommentService

    @BeforeEach
    fun setUp() {
        service = CommentService(WallService, noteService) // новый сервис для каждого теста
    }

    @Test // Добавить несколько комментариев к заметке
    fun addSomePostCommentsIdIncrement() {
        WallService.add(Post(text = "Test post"))
        val testData = TestComments.PostCommentsToAdd.postComments
        val comments = mutableListOf<Comment>()

        comments += testData.map { params ->
            service.addComment(
                parentId = params.parentId,
                parentType = params.parentType,
                comment = params.comment
            )
        }
        comments.forEachIndexed { index, comment ->
            //наращивается ли id коммента
            assertEquals(index + 1, comment.id)
            // id поста / заметки
            assertEquals(testData[index].parentId, comment.parentId)
            // тип записи, к которой относится комментарий
            assertEquals(testData[index].parentType, comment.parentType)
        }
    }

    @Test // Добавить несколько комментариев к заметке
    fun addSomeNoteCommentsIdIncrement(){
        noteService.add("Text","Title")
        val testData = TestComments.NoteCommentsToAdd.noteComments
        val comments = mutableListOf<Comment>()

        comments += testData.map { params ->
            service.addComment(
                parentId = params.parentId,
                parentType = params.parentType,
                comment = params.comment
            )
        }

        comments.forEachIndexed { index, comment ->
            //наращивается ли id коммента
            assertEquals(index + 1, comment.id)
            // id поста / заметки
            assertEquals(testData[index].parentId, comment.parentId)
            // тип записи, к которой относится комментарий
            assertEquals(testData[index].parentType, comment.parentType)
        }
    }

    @Test // Создать один комментарий к существующему посту
    fun сreateCommentToExistingPost() {
        WallService.add(Post(text = "Test post"))
        val comment = Comment(text = "Test comment")
        val result = service.addComment(1, ParentType.POST, comment)
        Assertions.assertEquals("Test comment", result.text)
        Assertions.assertTrue(result.id > 0)
    }

    @Test // Создать один комментарий к существующей заметке
    fun сreateCommentToExistingNote() {
        noteService.add("Test note", "Title")
        val comment = Comment(text = "Test comment")
        val result = service.addComment(1, ParentType.NOTE, comment)
        Assertions.assertEquals("Test comment", result.text)
        Assertions.assertTrue(result.id > 0)
    }

    @Test // Попытаться создать комментарий к несуществующему посту
    fun сreateCommentToNotFoundedPost() {
        val comment = Comment(text = "Should fail")
        Assertions.assertThrows(RecordNotFoundException::class.java) {
            service.addComment(999, ParentType.POST, comment)
        }
    }
    @Test // Попытаться создать комментарий к несуществующей заметке
    fun сreateCommentToNotFoundedNote() {
        val comment = Comment(text = "Should fail")
        Assertions.assertThrows(RecordNotFoundException::class.java) {
            service.addComment(999, ParentType.NOTE, comment)
        }
    }

}