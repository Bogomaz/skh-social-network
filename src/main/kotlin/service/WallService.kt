package ru.netology.service

import ru.netology.exception.NoteNotFoundException
import ru.netology.exception.RecordNotFoundException
import ru.netology.model.Comment
import ru.netology.model.Note
import ru.netology.model.Post

object WallService {
    var posts = emptyArray<Post>()
    private var currentPostId = 1

    private var comments = emptyArray<Comment>()
    private var currentCommentId = 1

    //Принимает объект Post
    //Добавляет пост
    //Возвращает только что добавленный пост
    fun add(post: Post): Post {
        val newPost = post.copy(id = currentPostId++)
        posts += newPost
        return posts.last();
    }

    //Принимает id поста
    //Возвращает запрошенный пост, или генерит исключение
    fun getById(postId: Int): Post {
        val post = posts.firstOrNull() { it.id == postId}
        if (post == null) {
            throw RecordNotFoundException("The post $postId doesn't exist")
        }
        return post;
    }

    //Принимает объект Post
    //Находит в массиве запись с тем же id, что и у post и обновляет все свойства;
    //Если пост с таким id не найден, то ничего не происходит и возвращается false, в противном случае – возвращается true.
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
}