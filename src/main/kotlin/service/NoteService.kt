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

    //Принимает содержимое заметки и права доступа
    //Добавляет заметку
    //Возвращает только что добавленную заметку
    fun add(
        newText: String, // Текст заметки.
        newTitle: String, // Заголовок заметки.
        newViewPrivacy: Privacy = Privacy.EVERYONE, //Уровень доступа к заметке.
        newCommentPrivacy: Privacy = Privacy.EVERYONE // Уровень доступа к комментированию
    ): Note {
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
        return notes.last()
    }

    //Принимает id заметки и id пользователя-владельца
    //Возвращает найденную заметку, или генерит исключение
    @Throws(RecordNotFoundException::class)
    fun getByIdAndOwner(noteId: Int, ownerId: Int): Note {
        val note = notes.firstOrNull() { it.id == noteId && it.ownerId == ownerId }
        if (note == null) {
            throw RecordNotFoundException("The note $noteId non exists")
        }
        return note;
    }

    //Принимает набор идентификаторов заметок и id пользователя-владельца,
    //Количество заметок, которое нужно вернуть
    //Способ сортировки (1 - по возрастанию id, 0 - по убыванию id)
    //Возвращает найденную заметку, или генерит исключение
    fun get(
        noteIds: List<Int>,
        userId: Int? = 0,
        count: Int = Int.MAX_VALUE,
        sort: Int = 1
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
        return result.take(count).toList()
    }

    //Принимает id заметки
    //Возвращает запрошенную заметку, или генерит исключение
    fun getById(noteId: Int): Note {
        val note = notes.firstOrNull() { it.id == noteId }
        if (note == null) {
            throw RecordNotFoundException("The note  $noteId doesn't exist")
        }
        return note;
    }

    //Принимает id заметки и содержимое, которое надо изменить,
    //Возвращает отредактированную заметку или генерит исключение
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

    //Принимает id заметки
    //Возвращает 1, если заметка успешно удалена
    fun delete(noteId: Int): Int {
        val removed = notes.removeIf { it.id == noteId }
        if (!removed) {
            throw RecordNotFoundException("This note doesn't exist")
        }
        return 1
    }

}