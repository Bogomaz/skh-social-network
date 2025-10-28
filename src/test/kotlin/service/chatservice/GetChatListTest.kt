package service.chatservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.netology.service.ChatService
import ru.netology.model.Chat

class GetChatListTest {
    lateinit var chatService: ChatService

    @BeforeEach
    fun setUp() {
        chatService = ChatService()
    }
    @Test
    fun getChatListTest() {
        val testChats = listOf(
            Chat(
                id = 1,
                usersId = listOf(1, 2),
                messagesCount = 1,
                unreadMessagesCount = 1,
                isDeleted = false
            ),
            Chat(
                id = 2,
                usersId = listOf(1, 3),
                messagesCount = 1,
                unreadMessagesCount = 1,
                isDeleted = false
            ),
            Chat(
                id = 3,
                usersId = listOf(1, 4),
                messagesCount = 1,
                unreadMessagesCount = 1,
                isDeleted = false
            ),
            Chat(
                id = 4,
                usersId = listOf(1, 5),
                messagesCount = 1,
                unreadMessagesCount = 1,
                isDeleted = false
            ),
        )

        chatService.addMessageToChat(1, 2, "Привет!")
        chatService.addMessageToChat(1, 3, "Привет. Скучаешь?")
        chatService.addMessageToChat(1, 4, "Здравствуйте. Направляю Вам резюме.")
        chatService.addMessageToChat(1, 5, "Привет. Хочешь познакомиться?")

        val result = chatService.getChatList(1)
        Assertions.assertEquals(testChats, result)
    }

    @Test
    fun getChatListTestWithDeletedChat() {
        val testChats = listOf(
            Chat(
                id = 1,
                usersId = listOf(1, 2),
                messagesCount = 1,
                unreadMessagesCount = 1,
                isDeleted = false
            ),
            Chat(
                id = 2,
                usersId = listOf(1, 3),
                messagesCount = 1,
                unreadMessagesCount = 1,
                isDeleted = false
            ),
            Chat(
                id = 3,
                usersId = listOf(1, 4),
                messagesCount = 1,
                unreadMessagesCount = 1,
                isDeleted = false
            )
        )

        chatService.addMessageToChat(1, 2, "Привет!")
        chatService.addMessageToChat(1, 3, "Привет. Скучаешь?")
        chatService.addMessageToChat(1, 4, "Здравствуйте. Направляю Вам резюме.")
        chatService.addMessageToChat(1, 5, "Привет. Хочешь познакомиться?")
        chatService.deleteMessage(4)

        val result = chatService.getChatList(1)
        Assertions.assertEquals(testChats, result)
    }

    @Test
    fun getEmptyChatListTest() {
        chatService.addMessageToChat(1, 2, "Привет!")

        val result = chatService.getChatList(3)
        Assertions.assertEquals(emptyList<Chat>(), result)
    }
}