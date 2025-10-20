package service.noteservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.exception.RecordNotFoundException
import ru.netology.model.Note
import ru.netology.model.Privacy
import ru.netology.service.NoteService
import service.TestNotesToAdd

class EditNoteTest {
    //Редактирование  заметки по id
    @Test
    fun editExistingNoteById(){
        val noteService = NoteService<Note>()
        val notes = TestNotesToAdd.notes.map{ params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }
        val result = noteService.edit(3, "Hello", "I'm an editor", Privacy.FRIENDS_ONLY, Privacy.FRIENDS_ONLY)
        Assertions.assertEquals("Hello", result.title)
        Assertions.assertEquals("I'm an editor", result.text)

    }

    //Попытка отредактировать заметку, которой нет
    @Test
    fun editNonExistingNote(){
        val noteService = NoteService<Note>()
        val notes = TestNotesToAdd.notes.map{ params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }
        Assertions.assertThrows(RecordNotFoundException::class.java) {
            noteService.edit(999, "Hello", "I'm an editor", Privacy.FRIENDS_ONLY, Privacy.FRIENDS_ONLY)
        }
    }
}