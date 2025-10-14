package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.model.Comment
import ru.netology.model.Photo
import ru.netology.model.PhotoAttachment

class CommentTest {
    val commentFullFilled = Comment(
        id = 1,
        postId = 2, // Идентификатор поста
        fromId = 1, //Идентификатор автора комментария
        date = 1759661150, //Дата создания комментария в формате Unixtime
        text = "It's cool!", //Текст комментария
        replyToUser = 12, //Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо).
        replyToComment = 1, // Идентификатор комментария, в ответ на который оставлен текущий (если применимо).
        attachments = listOf(
            PhotoAttachment(
                photo = Photo(
                    id = 1,
                    ownerId = 1,
                    photo130 = "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-465x260.jpg",
                    photo604 = "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-1.jpg"
                )
            )
        )
    )

    @Test
    fun getId() {
        val result = commentFullFilled.id;
        Assertions.assertEquals(1, result)
    }

    @Test
    fun getPostId() {
        val result = commentFullFilled.postId;
        Assertions.assertEquals(2, result)
    }

    @Test
    fun getFromId() {
        val result = commentFullFilled.fromId;
        Assertions.assertEquals(1, result)
    }

    @Test
    fun getDate() {
        val result = commentFullFilled.date;
        Assertions.assertEquals(1759661150, result)
    }

    @Test
    fun getText() {
        val result = commentFullFilled.text;
        Assertions.assertEquals("It's cool!", result)
    }

    @Test
    fun getReplyToUser() {
        val result = commentFullFilled.replyToUser;
        Assertions.assertEquals(12, result)
    }

    @Test
    fun getReplyToComment() {
        val result = commentFullFilled.replyToComment;
        Assertions.assertEquals(1, result)
    }

}