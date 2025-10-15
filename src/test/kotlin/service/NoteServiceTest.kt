package service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.netology.model.Comments
import ru.netology.model.Note
import ru.netology.model.Privacy
import ru.netology.service.NoteService
import java.util.stream.Stream

class NoteServiceTest {
    @Test
        fun addMultipleNotes_IdIncrement() {
        val noteService = NoteService<Note>()
        val results = TestNotes.notes.map { noteService.add(it) }
        results.forEachIndexed { index, id ->
            Assertions.assertEquals(index + 1, id)
        }
    }



    @Test
    fun get() {
    }

    @Test
    fun getById() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun edit() {
    }

}