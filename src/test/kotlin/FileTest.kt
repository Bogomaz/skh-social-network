import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.netology.File

class FileTest {
    val fileFullFilled = File(
        id = 1,
        owner_id = 1, // идентификатор владельца видео
        title = "Abronia graminea", // название
        size = 98_304, //Размер файла в байтах.
        ext = "webp", // Расширение файла.
        url = "https://terrarium-online.com/wp-content/uploads/2023/02/abronia-graminea.webp", // Адрес файла, по которому его можно загрузить.
        date = 1759661150, // Дата добавления в формате Unixtime
    )

    val fileNullTitle = File(
        id = 1,
        owner_id = 1, // идентификатор владельца видео
        title = null, // название
        size = 98_304, //Размер файла в байтах.
        ext = "webp", // Расширение файла.
        url = "https://terrarium-online.com/wp-content/uploads/2023/02/abronia-graminea.webp", // Адрес файла, по которому его можно загрузить.
        date = 1759661150, // Дата добавления в формате Unixtime
    )

    @Test
    fun getId() {
        val result = fileFullFilled.id;
        assertEquals(1, result)
    }

    @Test
    fun getOwner_id() {
        val result = fileFullFilled.owner_id;
        assertEquals(1, result)
    }

    @Test
    fun getTitleFilled() {
        val result = fileFullFilled.title;
        assertEquals("Abronia graminea", result)
    }

    @Test
    fun getTitleNull() {
        val result = fileNullTitle.title;
        assertEquals(null, result)
    }

    @Test
    fun getSize() {
        val result = fileFullFilled.size;
        assertEquals(98_304, result)
    }

    @Test
    fun getExt() {
        val result = fileFullFilled.ext;
        assertEquals("webp", result)
    }

    @Test
    fun getUrl() {
        val result = fileFullFilled.url;
        assertEquals("https://terrarium-online.com/wp-content/uploads/2023/02/abronia-graminea.webp", result)
    }

    @Test
    fun getDate() {
        val result = fileFullFilled.date;
        assertEquals(1759661150, result)
    }

}