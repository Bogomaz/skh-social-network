package service.noteservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.exception.RecordNotFoundException
import ru.netology.model.Note
import ru.netology.service.NoteService
import service.TestNotesToAdd

class DeleteNoteTest {
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
        Assertions.assertThrows(RecordNotFoundException::class.java) {
            noteService.delete(999)
        }
    }
}