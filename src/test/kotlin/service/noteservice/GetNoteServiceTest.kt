package service.noteservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.netology.exception.NoteNotFoundException
import ru.netology.exception.OwnerNotFoundException
import ru.netology.model.Note
import ru.netology.model.Privacy
import ru.netology.service.NoteService

class GetNoteServiceTest {
    val noteService = NoteService<Note>()

    // Проверяем, возвращается ли запрошенный список записей
    @Test
    fun testGetNotesReturnsCorrectList() {
        val notes = TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }
        val ids = listOf(1, 3, 4, 5)
        val result = noteService.get(ids, 0, 4, sort = 1)

        // Размер списка совпадает с ожидаемым
        assertEquals(ids.size, result.size)

        // Записи с запрошенными id присутствуют
        val resultIds = result.map { it.id }
        assertTrue(resultIds.containsAll(ids))

        // Порядок возвращённых записей совпадает с указанным
        assertEquals(ids, resultIds)
    }

    //Попытка получить несуществующую заметку
    @Test
    fun testThrowsNoteNotFoundedException() {
        val notes = TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }
        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.get(listOf(1, 2, 999))
        }
    }

    //Попытка получить заметки несуществующего пользователя
    @Test
    fun testThrowsOwnerNotFoundedException() {
        noteService.add("text", "title")
        Assertions.assertThrows(OwnerNotFoundException::class.java) {
            noteService.get(emptyList(), userId = 999)
        }
    }

}