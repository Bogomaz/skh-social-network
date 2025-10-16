package service.noteservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.exception.NoteNotFoundException
import ru.netology.model.Note
import ru.netology.service.NoteService

class DeleteNoteServiceTest {
    //Удаление заметки по Id
    @Test
    fun deleteNotesId() {
        val noteService = NoteService<Note>()
        TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }
        val result = noteService.delete(2)
        Assertions.assertEquals(1, result)
    }

    //Удаление несуществующей заметки по Id
    @Test
    fun deleteNonFoundedNotesId() {
        val noteService = NoteService<Note>()
       TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }
        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.delete(999)
        }
    }
}