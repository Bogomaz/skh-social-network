package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.model.Coordinates
import ru.netology.model.Geotag
import ru.netology.model.Place
import ru.netology.model.PlaceType

class GeotagTest {
    val geotag = Geotag(
        type = PlaceType.SIGHT,
        coordinates = Coordinates(
            latitude = 47.416481,
            longitude = 40.086535
        ),
        place = Place(
            type = 1,
            groupId = 996167,
            groupPhoto = "https://sun9-25.userapi.com/s/v1/ig2/x9jACJlLoXrVuUOehcbNBhHZVBKAm2pq9CzJoDsSkJMO1vAk6qQ8uU4mda_oIc7dUW7N6yxlJZiGQINeTNL8SArB.jpg?quality=95&as=32x32,48x48,72x72,108x108,160x160,240x240,360x360,480x480,540x540,640x640,720x720,1080x1080,1280x1280,1300x1300&from=bu&cs=1280x0",
            checkins = 10,
            updated = 2,
            address = 423
        )
    )

    @Test
    fun getPlaceType() {
        val result = geotag.type;
        Assertions.assertEquals(PlaceType.SIGHT, result)
    }

    @Test
    fun getCoordinates() {
        val result = geotag.coordinates;
        Assertions.assertEquals(
            Coordinates(latitude = 47.416481, longitude = 40.086535),
            result
        );
    }

    @Test
    fun getLongitude() {
        val result = geotag.coordinates.longitude;
        Assertions.assertEquals(40.086535, result);
    }

    @Test
    fun getLatitude() {
        val result = geotag.coordinates.latitude;
        Assertions.assertEquals(47.416481, result);
    }

    @Test
    fun getPlace() {
        val result = geotag.place;
        Assertions.assertEquals(
            Place(
                type = 1,
                groupId = 996167,
                groupPhoto = "https://sun9-25.userapi.com/s/v1/ig2/x9jACJlLoXrVuUOehcbNBhHZVBKAm2pq9CzJoDsSkJMO1vAk6qQ8uU4mda_oIc7dUW7N6yxlJZiGQINeTNL8SArB.jpg?quality=95&as=32x32,48x48,72x72,108x108,160x160,240x240,360x360,480x480,540x540,640x640,720x720,1080x1080,1280x1280,1300x1300&from=bu&cs=1280x0",
                checkins = 10,
                updated = 2,
                address = 423
            ), result
        )
    }

    @Test
    fun getCheckinType() {
        val result = geotag.place?.type;
        Assertions.assertEquals(1, result);
    }

    @Test
    fun getGroupId() {
        val result = geotag.place?.groupId;
        Assertions.assertEquals(996167, result);
    }

    @Test
    fun getGroupPhoto() {
        val result = geotag.place?.groupPhoto;
        Assertions.assertEquals(
            "https://sun9-25.userapi.com/s/v1/ig2/x9jACJlLoXrVuUOehcbNBhHZVBKAm2pq9CzJoDsSkJMO1vAk6qQ8uU4mda_oIc7dUW7N6yxlJZiGQINeTNL8SArB.jpg?quality=95&as=32x32,48x48,72x72,108x108,160x160,240x240,360x360,480x480,540x540,640x640,720x720,1080x1080,1280x1280,1300x1300&from=bu&cs=1280x0",
            result
        );
    }
    @Test
    fun getGroupCheckins() {
        val result = geotag.place?.checkins;
        Assertions.assertEquals(10, result);
    }
    @Test
    fun getUpdate() {
        val result = geotag.place?.updated;
        Assertions.assertEquals(2, result);
    }
    @Test
    fun getAddress() {
        val result = geotag.place?.address;
        Assertions.assertEquals(423, result);
    }
}