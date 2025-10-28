package ru.netology.model

data class Message (
    val id : Int = 0,
    val chatId : Int, // Идентификатор чата, к которому принадлежат сообщения
    val senderId : Int, //Идентификатор отправителя
    val addresseeId: Int = 0, //Идентификатор получателя .
    var isRead : Boolean, //Прочитано сообщение адресатом, или нет.
    val sendDate: Int, //Дата отправки
    val receiveDate: Int? = null, //Дата получения
    var readingDate: Int? = null, //Дата прочтения
    var text : String, //Текст сообщения
)

data class Chat(
    val id: Int = 0,
    val usersId: List<Int>,
    var messagesCount: Int = 0, //Количество сообщений.
    var unreadMessagesCount: Int = 0, //Количество непрочитанных сообщений.
    var isDeleted: Boolean = false, //Удалено или нет
)