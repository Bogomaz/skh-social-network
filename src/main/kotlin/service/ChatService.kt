package ru.netology.service

import ru.netology.exception.ChatAlreadyExists
import ru.netology.exception.ChatNotFoundException
import ru.netology.exception.MessageNotFoundException
import ru.netology.model.Chat
import ru.netology.model.Message
import kotlin.Int

class ChatService() {
    private val chats = mutableListOf<Chat>()
    private val messages = mutableListOf<Message>()
    private var currentChatId = 1
    private var currentMessageId = 1

    //Принимает идентификатор отправителя и идентификатор адресата,
    //Создаёт новый чат между этими пользователями.
    //Возвращает созданный чат
    fun createChat(senderId: Int, addresseeId: Int): Chat {
        return chats.find { chat ->
            chat.usersId.containsAll(listOf(senderId, addresseeId))
        }?.let {
            throw ChatAlreadyExists("User's $senderId and $addresseeId chat already exists")
        } ?: run {
            val newChat = Chat(
                id = currentChatId++,
                usersId = listOf(senderId, addresseeId),
                messagesCount = 0,
                unreadMessagesCount = 0
            )
            chats.add(newChat)
            newChat
        }
    }

    //Принимает идентификатор отправителя и идентификатор адресата,
    //Ищет, есть ли уже чат с такой парой пользователей.
    //Если есть - возвращает идентификатор чата
    fun findChat(senderId: Int, addresseeId: Int): Chat? {
        return chats.find { chat ->
            chat.usersId.containsAll(listOf(senderId, addresseeId))
        }
    }

    //Принимает идентификатор пользователя,
    //Ищет все чаты, в которых есть такой пользователь.
    //Если есть - возвращает список, в котором есть идентификатор и участники
    fun getChatList(userId: Int): List<Chat> {
        return chats.filter { chat ->
            chat.usersId.contains(userId) && !chat.isDeleted
        }
    }

    // Принимает id пользователя, находит все его чаты, в которых есть непрочитанные сообщения
    fun getUnreadChatCount(userId: Int): Int {
        val userChats = getChatList(userId)
        return userChats.count { chat ->
            messages.any { msg ->
                msg.chatId == chat.id && !msg.isRead
            }
        }
    }

    //Принимает идентификаторы отправителя и адресата и сообщения.
    //Ищет, есть ли уже чат с такой парой пользователей.
    //Если есть добавляет сообщение в чат. Если нет - сначала создаёт чат, потом добавляет туда сообщение
    fun addMessageToChat(senderId: Int, addresseeId: Int, text: String): Message {
        //Ищем чат между этими пользователями. Если чат не найден, то создаём его
        var chat = findChat(senderId, addresseeId) ?: createChat(senderId, addresseeId)
        val index = chats.indexOfFirst { it.id == chat.id }
        chats[index] = chats[index].apply {
            isDeleted = false
            messagesCount++
            unreadMessagesCount++
        }

        val message = createMessage(chat.id, senderId, addresseeId, text)
        messages.add(message)
        return message
    }

    //Принимает id чата, в который надо добавить сообщение, id отправителя/получателя, текст сообщения
    //создаёт объект Сообщение и возвращает его.
    fun createMessage(chatId: Int, senderId: Int, addresseeId: Int, text: String): Message {
        return Message(
            id = currentMessageId++,
            chatId = chatId,
            senderId = senderId,
            addresseeId = addresseeId,
            isRead = false,
            sendDate = (System.currentTimeMillis() / 1000).toInt(),
            receiveDate = (System.currentTimeMillis() / 1000).toInt(),
            readingDate = null,
            text = text,
        )
    }

    //Принимает id сообщения и удаляет его, если оно ещё не удалено
    //Проверяет, остались ли в этом переписке ещё сообщения.
    //Если сообщений не осталось, то чат помечается как удалённый
    fun deleteMessage(messageId: Int): Boolean {
        val message = messages.firstOrNull { it.id == messageId }
            ?: throw MessageNotFoundException("Message $messageId non exists")

        val result = messages.remove(message)

        val chatMessages = getAllMessages(message.senderId, message.addresseeId)
        if (chatMessages.isEmpty()) {
            deleteChat(message.chatId)
        }
        return result
    }

    //Принимает id сообщения и новый текст
    //Изменяет сообщение и возвращает финальную версию.
    fun editMessage(
        id: Int,
        text: String,
    ): Message {
        val index = messages.indexOfFirst { it.id == id}
        if (index == -1) throw MessageNotFoundException("Message $id non exists")
        messages[index].text = text
        return messages[index]
    }

    //Принимает id собеседников,
    //Выбирает все сообщения, у которых нет isDeleted = false
    //Возвращает список сообщений, упорядоченных по дате.
    fun getAllMessages(collocutor1: Int, collocutor2: Int, count: Int = Int.MAX_VALUE): List<Message> {
        val chat = findChat(collocutor1, collocutor2) ?: throw ChatNotFoundException("Chat non exists")
        return messages
            .filter { it.chatId == chat.id }
            .sortedBy { it.sendDate }
            .take(count)
            .onEach { it.isRead = true }
    }


    //Принимает идентификатор чата,
    //Выбирает все сообщения с меткой unread = true
    //Возвращает количество сообщений.
    fun getUnreadMessageCount(senderId: Int, addresseeId: Int): Int {
        val chat = findChat(senderId, addresseeId) ?: throw ChatNotFoundException("Chat non exists")
        return messages.filter {
            it.chatId == chat.id &&
                    !it.isRead
        }.size
    }

    //Принимает идентификатор чата,
    //Присваивает чату и всем его сообщениям метку is_deleted
    //Возвращает true, если чат удалён успешно.
    fun deleteChat(chatId: Int): Boolean {
        val chat = chats.firstOrNull() { it.id == chatId }
            ?: throw ChatNotFoundException("Chat $chatId non exists")
        chat.isDeleted = true
        return chat.isDeleted
    }
}
