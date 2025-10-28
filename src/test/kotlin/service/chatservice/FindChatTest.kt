package service.chatservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.model.Chat
import ru.netology.service.ChatService

class FindChatTest {
    lateinit var chatService : ChatService

    @BeforeEach
    fun setUp() {
        chatService = ChatService()
    }

    @Test
    fun findNonExistingChat(){
        val result = chatService.findChat(2, 1)
        Assertions.assertEquals(null, result)
    }

    @Test
    fun findDeletedChat(){
        val chat = Chat(
            id = 1,
            usersId = listOf(1, 2),
            messagesCount = 0, //Количество сообщений.
            unreadMessagesCount = 0, //Количество непрочитанных сообщений.
            isDeleted = true, //Удалено или нет
        )
        chatService.createChat(1, 2)
        chatService.deleteChat(1)
        val result = chatService.findChat(1, 2)

        Assertions.assertEquals(chat, result)
    }
}