package service.noteservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.exception.NoteNotFoundException
import ru.netology.model.Note
import ru.netology.service.NoteService

class GetByIdNoteServiceTest {
    //Получение существующей заметки по id и ownerId
    @Test
    fun getByIdExistingNoteReturnsNote() {
        val noteService = NoteService<Note>()
        val notes = TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }
        val note = noteService.getById(3, ownerId = 0)
        Assertions.assertEquals("Как приручить дракона", note.title)
    }

    //Попытка получить несуществующую заметку
    @Test
    fun getByIdNonExistingNoteThrows() {
        val noteService = NoteService<Note>()
        val notes = TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }

        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.getById(100, ownerId = 0)
        }
    }

    //Попытка получить заметку несуществующего владельца
    @Test
    fun getByIdNonExistingOwnerThrows() {
        val noteService = NoteService<Note>()
        val notes = TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }

        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.getById(3, ownerId = 1)
        }
    }
}