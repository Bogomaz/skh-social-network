package service.noteservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.exception.RecordNotFoundException
import ru.netology.model.Note
import ru.netology.service.NoteService
import service.TestNotesToAdd

class GetByIdAndOwnerIdTest {
    val noteService = NoteService<Note>()

    //Получение существующей заметки по id и ownerId
    @Test
    fun getByIdExistingNoteReturnsNote() {
        val notes = TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }
        val note = noteService.getByIdAndOwner(3, ownerId = 0)
        Assertions.assertEquals("Как приручить дракона", note.title)
    }

    //Попытка получить несуществующую заметку
    @Test
    fun getByIdNonExistingNoteThrows() {
        val notes = TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }

        Assertions.assertThrows(RecordNotFoundException::class.java) {
            noteService.getByIdAndOwner(100, ownerId = 0)
        }
    }

    //Попытка получить заметку несуществующего владельца
    @Test
    fun getByIdNonExistingOwnerThrows() {
        val notes = TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }

        Assertions.assertThrows(RecordNotFoundException::class.java) {
            noteService.getByIdAndOwner(3, ownerId = 1)
        }
    }
}