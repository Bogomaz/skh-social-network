package ru.netology

interface Attachment {
    val type: String
}

data class PhotoAttachment(val photo: Photo): Attachment{
    override val type: String = "photo"
}

data class VideoAttachment(val video: Video): Attachment{
    override val type: String = "video"
}

data class FileAttachment(val file: File): Attachment{
    override val type: String = "file"
}

data class StickerAttachment(val sticker: Sticker): Attachment{
    override val type: String = "sticker"
}

data class GeotagAttachment(val geotag: Geotag): Attachment{
    override val type: String = "geotag"
}

data class Photo(
    val id: Int, // идентификатор
    val ownerId: Int, // идентификатор владельца фото
    val photo130: String, // фото для предпросмотра
    val photo604: String // полноразмерное фото
)

data class Video(
    val id: Int,
    val ownerId: Int, // идентификатор владельца видео
    val title: String, // название
    val duration: Int, // длительность ролика в секундах
    val views: Int, // количество просмотров
)
data class File(
    val id: Int,
    val owner_id: Int, // идентификатор владельца видео
    val title: String, // название
    val size: Int, //Размер файла в байтах.
    val ext: String, // Расширение файла.
    val url: String, // Адрес файла, по которому его можно загрузить.
    val date: Int, // Дата добавления в формате Unixtime
)
data class Sticker(
    val product_id: Int, //Идентификатор набора
    val sticker_id: Int, // Идентификатор стикера
    val animation_url: String, // URL анимации стикера
    val is_allowed: Boolean // Информация о том, доступен ли стикер.
)

data class Geotag(
    val type: PlaceType = PlaceType.HOME, //Тип места.
    val coordinates: Coordinates, //Координаты места. Объект, который содержит поля:
    val place: Place? = null // Информация о месте, если она добавлена.
)

data class Coordinates(
    val latitude: Double, // географическая широта
    val longitude: Double, //географическая долгота.
)

data class Place(
    val type: Int, //тип чекина
    val group_id: Int, //идентификатор сообщества
    val group_photo: String, //URL миниатюры главной фотографии сообщества
    val checkins: Int, //количество чекинов
    val updated: Int, // время последнего чекина в Unixtime;
    val address: Int // адрес.
)

// Опциональный enum для post_type
enum class PlaceType {
    HOME,
    JOB,
    RESTOURANT,
    SIGHT,
    SCHOOL,
    HOSPITAL
}