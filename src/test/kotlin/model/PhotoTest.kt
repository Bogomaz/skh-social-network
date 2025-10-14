package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.model.Photo

class PhotoTest {

    val photoFullFilled = Photo(
        id = 1,
        ownerId = 1,
        photo130 = "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-465x260.jpg",
        photo604 = "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-1.jpg"
    )
    val photoNullPreview = Photo(
        id = 2,
        ownerId = 1,
        photo130 = null,
        photo604 = "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-1.jpg"
    )

    @Test
    fun getId() {
        val result = photoFullFilled.id;
        Assertions.assertEquals(1, result);
    }

    @Test
    fun getOwner_id() {
        val result = photoFullFilled.ownerId;
        Assertions.assertEquals(1, result);
    }

    @Test
    fun photo130Filled() {
        val result = photoFullFilled.photo130;
        Assertions.assertEquals(
            "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-465x260.jpg",
            result
        );
    }
    @Test
    fun photo130Null() {
        val result = photoNullPreview.photo130;
        Assertions.assertEquals(null, result);
    }

    @Test
    fun photo604() {
        val result = photoFullFilled.photo604;
        Assertions.assertEquals(
            "https://terrarium-online.com/wp-content/uploads/2023/10/ushastyj-bananoed-1.jpg",
            result
        );
    }
}