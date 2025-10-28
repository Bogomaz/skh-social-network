package service.chatservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.exception.ChatNotFoundException
import ru.netology.service.ChatService

class DeleteChatTest {
    lateinit var chatService : ChatService

    @BeforeEach
    fun setUp() {
        chatService = ChatService()
    }

    @Test
    fun deleteExistingChat(){
        chatService.createChat(1, 2)
        val result = chatService.deleteChat(1)

        Assertions.assertEquals(true, result)
    }

    @Test
    fun deleteNonExistingChat(){
        Assertions.assertThrows(ChatNotFoundException::class.java){
            chatService.deleteChat(100)
        }
    }
}