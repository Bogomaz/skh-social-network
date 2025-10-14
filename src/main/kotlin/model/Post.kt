package ru.netology.model

data class Post(
    val id: Int = 0, // id записи
    val ownerId: Int = 0, // id владельца стены, на которой размещена запись
    val fromId: Int = 0, // id автора записи, от чьего имени опубликована запись
    val date: Int = 0, //время публикации записи в формате unixtime.
    val text: String = "", //текст записи.
    val replyOwnerId: Int = 0, //id владельца записи, в ответ на которую была оставлена текущая.
    val replyPostId: Int = 0, //id записи, в ответ на которую была оставлена текущая.
    val friendsOnly: Boolean = true, // 1, если запись видна только друзьям.
    val attachments: List<Attachment>? = null, // медаивложения
    val comments: Comments? = null, // комментарии
    val likes: Likes? = null, // лайки
    val reposts: Reposts? = null, // репосты
    val views: Views? = null, // просмотры
    val postType: PostType = PostType.POST, //Тип записи, может принимать следующие значения: post, repost, comment, reply, postpone, suggest.
    val isFavorite: Boolean = false // true, если объект добавлен в закладки у текущего пользователя
)

data class Comments(
    val count: Int = 0, // количество комментариев к записи
    val canPost: Boolean = true, ///информация о том, может ли текущий пользователь комментировать запись (1 — может, 0 — не может);
    val groupsCanPost: Boolean = true, // информация о том, могут ли сообщества комментировать запись;
    val canClose: Boolean = false, // может ли текущий пользователь закрыть комментарии к записи;
    val canOpen: Boolean = false // может ли текущий пользователь открыть комментарии к записи.
)

data class Likes(
    val count: Int = 0, // число пользователей, которым понравилась запись;
    val userLikes: Boolean = true, // наличие отметки «Мне нравится» от текущего пользователя (1 — есть, 0 — нет);
    val canLike: Boolean = true, // информация о том, может ли текущий пользователь поставить отметку «Мне нравится» (1 — может, 0 — не может);
    val canPublish: Boolean = true // информация о том, может ли текущий пользователь сделать репост записи (1 — может, 0 — не может).
)

data class Reposts(
    val count: Int = 0, // число пользователей, скопировавших запись;
    val userReposted: Boolean = false // наличие репоста от текущего пользователя (1 — есть, 0 — нет).
)

data class Views(
    val count: Int // число просмотров записи.
)

// Опциональный enum для post_type
enum class PostType {
    POST,
    REPOST,
    REPLY,
    POSTPONE,
    SUGGEST
}
