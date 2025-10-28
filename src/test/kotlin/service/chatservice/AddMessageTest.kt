package service.chatservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.service.ChatService

class AddMessageTest {
    lateinit var chatService : ChatService

    @BeforeEach
    fun setUp() {
        chatService = ChatService()
    }

    @Test
    fun addMessageToExistingChat(){
       val text: String = "Hello! I'm here."
       chatService.createChat(1, 2)
       chatService.addMessageToChat(1, 2, text)
       val result = chatService.getAllMessages(1, 2).last().text
       Assertions.assertEquals(text, result)
    }

    @Test
    fun addMessageToNonExistingChat(){
        val text: String = "Hello! I'm here."
        chatService.addMessageToChat(2, 1, text)
        val result = chatService.getAllMessages(1, 2).last().text
        Assertions.assertEquals(text, result)
    }

    @Test
    fun addMessageToDeletedChat(){
        val text: String = "Hello! I'm here."
        chatService.createChat(1, 2)
        chatService.deleteChat(1)
        chatService.addMessageToChat(2, 1, text)
        val result = chatService.getAllMessages(1, 2).last().text
        Assertions.assertEquals(text, result)
    }
}