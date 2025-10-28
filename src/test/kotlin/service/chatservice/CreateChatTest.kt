package service.chatservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.exception.ChatAlreadyExists
import ru.netology.model.Chat
import ru.netology.service.ChatService

class CreateChatTest {
    lateinit var chatService : ChatService

    @BeforeEach
    fun setUp() {
        chatService = ChatService()
    }

    @Test
    fun createChatAndFindDirect(){
        val chat = Chat(
            id = 1,
            usersId = listOf(1, 2),
            messagesCount = 0, //Количество сообщений.
            unreadMessagesCount = 0, //Количество непрочитанных сообщений.
            isDeleted = false, //Удалено или нет
        )
        chatService.createChat(1, 2)
        val result = chatService.findChat(1, 2)
        Assertions.assertEquals(chat, result)
    }

    @Test
    fun createChatAndFindReverse(){
        val chat = Chat(
            id = 1,
            usersId = listOf(1, 2),
            messagesCount = 0, //Количество сообщений.
            unreadMessagesCount = 0, //Количество непрочитанных сообщений.
            isDeleted = false, //Удалено или нет
        )
        chatService.createChat(1, 2)
        val result = chatService.findChat(2,1)
        Assertions.assertEquals(chat, result)
    }

    @Test
    fun createDublicateChat(){
        chatService.createChat(1, 2)
        Assertions.assertThrows(ChatAlreadyExists::class.java){
            chatService.createChat(2, 1)
        }
    }

}