package service.chatservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.exception.MessageNotFoundException
import ru.netology.service.ChatService

class EditMessageTest {
    lateinit var chatService : ChatService

    @BeforeEach
    fun setUp() {
        chatService = ChatService()
    }

    @Test
    fun editExistingMessage(){
        val oldText: String = "Hello! I'm here."
        val newText: String = "This message was editing."
        chatService.createChat(1, 2)
        chatService.addMessageToChat(1, 2, oldText)
        val result = chatService.editMessage(1, newText)
        Assertions.assertEquals(newText, result.text)
    }

    @Test
    fun editNonExistingMessage(){
        Assertions.assertThrows(MessageNotFoundException::class.java){
            chatService.editMessage(999, "New Message Text")
        }
    }
}