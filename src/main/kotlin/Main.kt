package ru.netology

import ru.netology.model.User
import ru.netology.service.ChatService
import ru.netology.service.UserService
import kotlin.Int


fun main() {
    val chatService = ChatService()
    chatService.addMessageToChat(1, 2, "Привет!")
    chatService.addMessageToChat(2, 1, "Привет! Как дела?")
    chatService.addMessageToChat(1, 2, "Нормально.")
    chatService.addMessageToChat(2, 1, "Может, поговорим?")

    chatService.addMessageToChat(1, 3, "Привет. Скучаешь?")

    chatService.addMessageToChat(1,4, "Здравствуйте. Направляю Вам резюме.")
    chatService.addMessageToChat(4,1, "Здравствуйте. Спасибо. Мы рассмотрим его и вернёмся с ответом в ближайшее время.")

    chatService.addMessageToChat(1,5, "Привет. Хочешь познакомиться?")

    chatService.addMessageToChat(2,4, "Добрый день. Соискатель прислал резюме?")
    chatService.addMessageToChat(4, 2, "Добрый день. Да, только что.")

    println(chatService.getChatList(1))

    println(chatService.getUnreadMessageCount(1, 4))
    println(chatService.getUnreadChatCount(1))
    println(chatService.getAllMessages(1, 4))
    println(chatService.getAllMessages(1, 2))
    println(chatService.getUnreadChatCount(1))

}
