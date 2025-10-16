package service.noteservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.exception.NoteNotFoundException
import ru.netology.model.Note
import ru.netology.model.Privacy
import ru.netology.service.NoteService

class EditNoteServiceTest {
    //Удаление заметки по id
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
        Assertions.assertEquals(1, result)
    }

    //Попытка удалить заметку, которой нет
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
        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.edit(999, "Hello", "I'm an editor", Privacy.FRIENDS_ONLY, Privacy.FRIENDS_ONLY)
        }
    }
}