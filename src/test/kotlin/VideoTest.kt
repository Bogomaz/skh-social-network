import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.netology.Video

class VideoTest {
    val videoFullFilled = Video(
        id = 1,
        ownerId = 1,
        title = "Cats",
        description = "Funny cats",
        duration = 30,
        views = 2_900_000,
        player = "https://vkvideo.ru/video-158579430_456262028"
    )
    val videoNullFilled = Video(
        id = 1,
        ownerId = 1,
        title = null,
        description = null,
        duration = 30,
        views = 2_900_000,
        player = "https://vkvideo.ru/video-158579430_456262028"
    )

    @Test
    fun getId() {
        val result = videoFullFilled.id;
        assertEquals(1, result)
    }

    @Test
    fun getOwnerId() {
        val result = videoFullFilled.ownerId;
        assertEquals(1, result)
    }

    @Test
    fun getTitleFilled() {
        val result = videoFullFilled.title;
        assertEquals("Cats", result)
    }
    @Test
    fun getTitleNull() {
        val result = videoNullFilled.title;
        assertEquals(null, result)
    }

    @Test
    fun getDescriptionFilled() {
        val result = videoFullFilled.description;
        assertEquals("Funny cats", result)
    }

    @Test
    fun getDescriptionNull() {
        val result = videoNullFilled.description;
        assertEquals(null, result)
    }

    @Test
    fun getDuration() {
        val result = videoFullFilled.duration;
        assertEquals(30, result)
    }

    @Test
    fun getViews() {
        val result = videoFullFilled.views;
        assertEquals(2_900_000, result)
    }

    @Test
    fun getPlayer() {
        val result = videoFullFilled.player;
        assertEquals("https://vkvideo.ru/video-158579430_456262028", result)
    }

}