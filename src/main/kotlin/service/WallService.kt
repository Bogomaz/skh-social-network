package ru.netology.service

import ru.netology.exception.CommentNotFoundException
import ru.netology.exception.PostNotFoundException
import ru.netology.exception.ReasonNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Post
import ru.netology.model.Reason
import ru.netology.model.Report

object WallService {
    private var posts = emptyArray<Post>()
    private var currentPostId = 1

    private var comments = emptyArray<Comment>()
    private var currentCommentId = 1

    // Добавляет пост в массив постов и возвращает только что добавленный пост
    fun add(post: Post): Post {
        val newPost = post.copy(id = currentPostId++)
        posts += newPost
        return posts.last();
    }

    // Находит в массиве запись с тем же id, что и у post и обновлять все свойства;
    // если пост с таким id не найден, то ничего не происходит и возвращается false, в противном случае – возвращается true.
    fun update(post: Post): Boolean {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == post.id) {
                posts[index] = post.copy(id = currentPost.id)
                println(posts[index])
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        currentPostId = 1
    }

    //Проверяет, есть ли пост с указанным Id. Если есть - добавляет комментарий в массив комментариев
    // И возвращает добавленный комментарий.
    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (post.id == postId) {
                val newComment = comment.copy(id = currentCommentId++)
                comments += newComment
                return newComment
            }
        }
        throw PostNotFoundException("This post does not exist")
    }

    //Принимает жалобу на комментарий. Если неугодный комментарий есть и причина жалобы обоснована - возвращает 1
    // Если нет - генерит исключение.
    fun reportComment(report: Report): Int {
        val commentExists = comments.any { it.id == report.commentId }
        val reasonExists = Reason.entries.any { it.code == report.reason }
        if(!commentExists){
            throw CommentNotFoundException("This comment does not exist")
        } else if(!reasonExists){
            throw ReasonNotFoundException(message = "You can't report a comment for this reason.")
        }
        return 1
    }
}