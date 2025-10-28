package service.chatservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.model.Chat
import ru.netology.service.ChatService

class GetUnreadChatCountTest {
    lateinit var chatService: ChatService

    @BeforeEach
    fun setUp() {
        chatService = ChatService()
    }

    @Test
    fun getUnreadChatCountTest_01() {
        chatService.addMessageToChat(1, 2, "Привет!")
        chatService.addMessageToChat(1, 3, "Привет. Скучаешь?")
        chatService.addMessageToChat(1, 4, "Здравствуйте. Направляю Вам резюме.")
        chatService.addMessageToChat(1, 5, "Привет. Хочешь познакомиться?")

        val result = chatService.getUnreadChatCount(1)
        Assertions.assertEquals(4, result)
    }

    @Test
    fun getUnreadChatCountTest_02() {
        chatService.addMessageToChat(1, 2, "Привет!")
        chatService.addMessageToChat(2, 1, "Привет. Скучаешь?")
        chatService.addMessageToChat(1, 2, "Хочешь познакомиться?")

        chatService.getAllMessages(1, 2)
        val result = chatService.getUnreadChatCount(1)
        Assertions.assertEquals(0, result)
    }
}