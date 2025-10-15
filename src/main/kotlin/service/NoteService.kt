package ru.netology.service

import ru.netology.exception.NoteNotFoundException
import ru.netology.model.Attachment
import ru.netology.model.Comments
import ru.netology.model.Likes
import ru.netology.model.Note
import ru.netology.model.Privacy
import ru.netology.model.Record
import ru.netology.model.Reposts
import ru.netology.model.Views

@Suppress("UNCHECKED_CAST")
class NoteService<T : Note>() {
    private val notes = mutableListOf<T>()
    private var nextId = 1

    //Добавляет заметку и возвращает её id
    fun add(note: T): Int {
        val newNoteWithId = note.copy(id = nextId++) as T
        notes.add(newNoteWithId)
        return newNoteWithId.id
    }

    //Возвращает список заметок.
    fun get(
        noteIds: List<Int>? = null,
        userId: Int? = null,
        offset: Int = 0,
        count: Int = Int.MAX_VALUE,
        sort: Int = 0
    ): List<T> {
        var result = notes.asSequence()

        // Фильтрация по userId, если задан
        if (userId != null) {
            result = result.filter { it.ownerId == userId }
        }

        // Фильтрация по noteIds, если заданы
        if (noteIds != null && noteIds.isNotEmpty()) {
            result = result.filter { it.id in noteIds }
        }

        // Сортировка
        result = when (sort) {
            1 -> result.sortedBy { it.id }      // по возрастанию id (или по дате, если есть поле date)
            else -> result.sortedByDescending { it.id } // по убыванию id
        }

        // Смещение и ограничение по количеству
        return result.drop(offset).take(count).toList()
    }

    //Возвращает заметку по её id.
    fun getById(noteId: Int): Note {
        val note = notes.firstOrNull() { it.id == noteId }
        if (note == null) {
            throw NoteNotFoundException("This note doesn't exist")
        }
        return note;
    }

    //Удаляет заметку текущего пользователя.
    fun delete(noteId: Int): Int {
        val removed = notes.removeIf { it.id == noteId }
        if (!removed) {
            throw NoteNotFoundException("This note doesn't exist")
        }
        return 1
    }


    //Редактирует заметку текущего пользователя.
    fun edit() {

    }

    //Добавляет новый комментарий к заметке.
    fun createComment() {

    }

    //Редактирует указанный комментарий у заметки.
    fun editComment() {

    }

    //Возвращает список комментариев к заметке.
    fun getComments() {

    }

    // Восстанавливает удалённый комментарий.
    fun restoreComment() {

    }

    //Удаляет комментарий к заметке.
    fun deleteComment() {

    }
}