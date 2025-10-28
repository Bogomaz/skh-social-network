package service.chatservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.exception.ChatNotFoundException
import ru.netology.exception.MessageNotFoundException
import ru.netology.service.ChatService

class getUnreadMessageCountTest {
    lateinit var chatService: ChatService

    @BeforeEach
    fun setUp() {
        chatService = ChatService()
    }

    @Test
    fun getNotNullUnreadMessagesTest() {
        chatService.addMessageToChat(1, 2, "Привет!")
        chatService.addMessageToChat(2, 1, "Привет. Скучаешь?")
        chatService.addMessageToChat(1, 2, "Здравствуйте. Направляю Вам резюме.")
        chatService.addMessageToChat(2, 1, "Привет. Хочешь познакомиться?")

        val result = chatService.getUnreadMessageCount(2, 1)
        Assertions.assertEquals(4, result)
    }

    @Test
    fun getNullUnreadMessagesTest_01() {
        chatService.addMessageToChat(1, 2, "Привет!")
        chatService.addMessageToChat(2, 1, "Привет. Скучаешь?")
        chatService.addMessageToChat(1, 2, "Здравствуйте. Направляю Вам резюме.")
        chatService.addMessageToChat(2, 1, "Привет. Хочешь познакомиться?")

        chatService.getAllMessages(1, 2)

        val result = chatService.getUnreadMessageCount(2, 1)
        Assertions.assertEquals(0, result)
    }

    @Test
    fun getNullUnreadMessagesTest_02() {
        Assertions.assertThrows(ChatNotFoundException::class.java){
            chatService.getUnreadMessageCount(2, 1)
        }
    }
}