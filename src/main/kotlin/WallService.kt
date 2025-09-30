package ru.netology

object WallService {
    private var posts = emptyArray<Post>()
    private var current_id = 1

    // Добавляет пост в массив постов и возвращает только что добавленный пост
    fun add(post: Post): Post {
        val newPost = post.copy(id = current_id++)
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
        current_id = 1
    }
}