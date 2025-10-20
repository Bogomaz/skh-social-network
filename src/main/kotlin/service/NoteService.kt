package ru.netology.service

import ru.netology.exception.OwnerNotFoundException
import ru.netology.exception.RecordNotFoundException
import ru.netology.model.Comments
import ru.netology.model.Note
import ru.netology.model.Privacy


@Suppress("UNCHECKED_CAST")
class NoteService<T : Note>() {
    private val notes = mutableListOf<T>()
    private var nextId = 1

    //Добавление заметки в коллекцию
    fun add(
        newText: String, // Текст заметки.
        newTitle: String, // Заголовок заметки.
        newViewPrivacy: Privacy = Privacy.EVERYONE, //Уровень доступа к заметке.
        newCommentPrivacy: Privacy = Privacy.EVERYONE // Уровень доступа к комментированию
    ): Int {
        val note = Note(
            id = nextId++,
            text = newText,
            title = newTitle,
            viewPrivacy = newViewPrivacy,
            comments = Comments(
                commentPrivacy = newCommentPrivacy
            )
        )
        notes.add(note as T)
        return note.id
    }

    //Возвращает заметку по её id.
    fun getById(noteId: Int): Note {
        val note = notes.firstOrNull() { it.id == noteId}
        if (note == null) {
            throw RecordNotFoundException("The note  $noteId doesn't exist")
        }
        return note;
    }

    //Возвращает заметку по её id.
    @Throws(RecordNotFoundException::class)
    fun getByIdAndOwner(noteId: Int, ownerId: Int): Note {
        val note = notes.firstOrNull() { it.id == noteId && it.ownerId == ownerId}
        if (note == null) {
            throw RecordNotFoundException("The note $noteId non exists")
        }
        return note;
    }

    //Возвращает список заметок.
    fun get(
        noteIds: List<Int>,
        userId: Int? = 0,
        count: Int = Int.MAX_VALUE,
        sort: Int = 0
    ): List<T> {
        var result = notes.asSequence()

        // Проверка наличия пользователя и фильтрация
        if (userId != null) {
            if (notes.none { it.ownerId == userId }) {
                throw OwnerNotFoundException("User $userId does not exist")
            }
            result = result.filter { it.ownerId == userId }
        }

        // Проверка наличия заметок и фильтрация по noteIds
        if (noteIds.isNotEmpty()) {
            val existingIds = notes.map { it.id }.toSet()
            val missingIds = noteIds.filter { it !in existingIds }
            if (missingIds.isNotEmpty()) {
                throw RecordNotFoundException("Notes with ids $missingIds not found")
            }
            result = result.filter { it.id in noteIds }
        }

        // Сортировка
        result = when (sort) {
            1 -> result.sortedBy { it.id }      // по возрастанию id (или по дате, если есть поле date)
            else -> result.sortedByDescending { it.id } // по убыванию id
        }

        // Смещение и ограничение по количеству
        return result.take(count).toList()
    }

    //Удаляет заметку текущего пользователя.
    fun delete(noteId: Int): Int {
        val removed = notes.removeIf { it.id == noteId }
        if (!removed) {
            throw RecordNotFoundException("This note doesn't exist")
        }
        return 1
    }

    //Редактирует заметку текущего пользователя.
    fun edit(
        noteId: Int,
        title: String,
        text: String,
        viewPrivacy: Privacy,
        commentPrivacy: Privacy,
    ): Note {
        val index = notes.indexOfFirst { it.id == noteId }
        return if (index == -1) {
            throw RecordNotFoundException("This note $noteId non exists")
        } else {
            val oldNote = notes[index]
            notes[index] = oldNote.copy(
                title = title,
                text = text,
                viewPrivacy = viewPrivacy,
                comments = Comments(commentPrivacy = commentPrivacy)
            ) as T
            notes[index]
        }
    }
}