package service.noteservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.model.Note
import ru.netology.service.NoteService

class AddNoteServiceTest {
    //Добавление нескольких заметок и проверка идентификторов
    @Test
    fun addMultipleNotesIdIncrement() {
        val noteService = NoteService<Note>()
        val results = TestNotesToAdd.notes.map { params ->
            noteService.add(
                newText = params.text,
                newTitle = params.title,
                newViewPrivacy = params.viewPrivacy,
                newCommentPrivacy = params.commentPrivacy
            )
        }
        results.forEachIndexed { index, id ->
            Assertions.assertEquals(index + 1, id)
        }
    }

    fun addOneNote() {
        val noteService = NoteService<Note>()
        val result = noteService.add(
            newText = "Hello, World",
            newTitle = "Start",
        )
        Assertions.assertEquals(1, result)
    }
}
