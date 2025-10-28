package service.chatservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.exception.MessageNotFoundException
import ru.netology.service.ChatService

class DeleteMessageTest {
    lateinit var chatService : ChatService

    @BeforeEach
    fun setUp() {
        chatService = ChatService()
    }

    @Test
    fun deleteExistingMessage(){
        val textMsg1: String = "Hello! I'm here."
        val textMsg2: String = "Hello! And me too."
        chatService.addMessageToChat(1, 2, textMsg1)
        chatService.addMessageToChat(2, 1, textMsg2)
        val result = chatService.deleteMessage(1)
        Assertions.assertEquals(true, result)
    }

    @Test
    fun deleteLastExistingMessage(){
        val textMsg1: String = "Hello! I'm here."
        chatService.addMessageToChat(1, 2, textMsg1)
        chatService.deleteMessage(1)
        val result =  chatService.findChat(1,2)?.isDeleted
        Assertions.assertEquals(true, result)
    }

    @Test
    fun deleteNonExistingMessage(){
        Assertions.assertThrows(MessageNotFoundException::class.java){
            chatService.deleteMessage(999)
        }
    }
}