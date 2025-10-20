package ru.netology.service

import ru.netology.exception.CommentNotFoundException
import ru.netology.exception.OwnerNotFoundException
import ru.netology.exception.ReasonNotFoundException
import ru.netology.exception.RecordNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Note
import ru.netology.model.ParentType
import ru.netology.model.Reason
import ru.netology.model.Record
import ru.netology.model.Report
import kotlin.collections.mutableListOf

class CommentService(
    private val wallService: WallService,
    private val noteService: NoteService<Note>
) {
    private val comments = mutableListOf<Comment>()
    private var currentId = 1

    //Проверяет, есть ли пост / заметка с указанным Id. Если есть - добавляет комментарий в массив комментариев
    // И возвращает добавленный комментарий.
    fun addComment(
        parentId: Int,
        parentType: ParentType,
        comment: Comment
    ): Comment {
        // Попытаться найти нужную запись по id и добавить коммент.
        when (parentType) {
            ParentType.POST -> wallService.getById(parentId)
            ParentType.NOTE -> noteService.getById(parentId)
        }
        val newComment = comment.copy(id = currentId++, parentId = parentId, parentType = parentType)
        comments += newComment
        return comments.last()
    }

    //Редактирует заметку текущего пользователя.
    fun editComment(
        commentId: Int,
        text: String,
    ): Comment {
        val index = comments.indexOfFirst { it.id == commentId && !it.isDeleted }
        if (index == -1) {
            throw CommentNotFoundException("This comment doesn't exist")
        } else {
            val oldComment = comments[index]
            val updatedComment = oldComment.copy(
                text = text,
            )
            comments[index] = updatedComment
        }
        return comments[index];
    }

    //Получить все комментарии
    fun getComments(
        parentId: Int,
        parentType: ParentType,
    ): List<Comment> {
        when (parentType) {
            // Попытаться найти нужную запись по id и добавить коммент.
            ParentType.POST -> wallService.getById(parentId)
            ParentType.NOTE -> noteService.getById(parentId)
        }
        return comments.filter { comment ->
            comment.parentId == parentId &&
                    comment.parentType == parentType &&
                    !comment.isDeleted
        }
    }

    // Присваивает комментарию флаг "Удалён"
    fun deleteComment(commentId: Int): Boolean {
        val index = comments.indexOfFirst { it.id == commentId && !it.isDeleted }
        if (index == -1) {
            throw CommentNotFoundException("The comment $commentId does not exist")
        } else {
            val comment = comments[index]
            comments[index] = comment.copy(isDeleted = true)
            return true
        }
    }

    // Восстанавливает комментарий
    // Присваивает комментарию флаг "Удалён"
    fun restoreComment(commentId: Int): Boolean {
        val index = comments.indexOfFirst { it.id == commentId && it.isDeleted }
        if (index == -1) {
            throw CommentNotFoundException("The comment $commentId does not exist")
        } else {
            val comment = comments[index]
            comments[index] = comment.copy(isDeleted = false)
            return true
        }
    }

    //Принимает жалобу на комментарий. Если неугодный комментарий существует и причина жалобы обоснована - возвращает 1
    // Если нет - генерит исключение.
    fun reportComment(report: Report): Int {
        val commentExists = comments.any { it.id == report.commentId && !it.isDeleted }
        val reasonExists = Reason.entries.any { it.code == report.reason }
        if (!commentExists) {
            throw CommentNotFoundException("This comment does not exist")
        } else if (!reasonExists) {
            throw ReasonNotFoundException(message = "You can't report a comment for this reason.")
        }
        return 1
    }


}


