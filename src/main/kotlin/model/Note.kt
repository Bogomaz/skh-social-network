package ru.netology.model

//Класс, описывающий Заметку. Унаследован от базового класса Запись.
data class Note (
    override val id: Int = 0,
    override val ownerId: Int = 0,
    override val fromId: Int = 0,
    override val date: Int = 0,
    override val text: String = "",
    override val viewPrivacy: Privacy = Privacy.EVERYONE,
    override val comments: Comments? = null,
    override val likes: Likes? = null, // лайки
    override val views: Views? = null, // просмотры
    override val reposts: Reposts? = null, // просмотры
    override val attachments: List<Attachment>? = null, // медаивложения
    val title: String, //Заголовок заметки.

    val viewUrl: String = "", //string URL страницы для отображения заметки.
    val textWiki: String = "" //Тэги ссылок на wiki
): Record(id, fromId, ownerId, date, text, viewPrivacy, comments, likes, views, reposts, attachments)