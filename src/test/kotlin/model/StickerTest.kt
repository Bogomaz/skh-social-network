package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.netology.model.Sticker

class StickerTest {
    val stickerFullFilled = Sticker(
        productId = 1610,
        stickerId = 76072,
        animationUrl = "https://vk.com/sticker/3-76072.json",
        isAllowed = true
    )
    val stickerNullAnimation = Sticker(
        productId = 1042,
        stickerId = 66,
        animationUrl = null,
        isAllowed = true
    )
    @Test
    fun getProduct_id() {
        val result = stickerFullFilled.productId;
        Assertions.assertEquals(1610, result)
    }

    @Test
    fun getSticker_id() {
        val result = stickerFullFilled.stickerId;
        Assertions.assertEquals(76072, result)
    }

    @Test
    fun getAnimation_urlFiiled() {
        val result = stickerFullFilled.animationUrl;
        Assertions.assertEquals("https://vk.com/sticker/3-76072.json", result)
    }
    @Test
    fun getAnimation_urlNull() {
        val result = stickerNullAnimation.animationUrl;
        Assertions.assertEquals(null, result)
    }

    @Test
    fun is_allowed() {
        val result = stickerFullFilled.isAllowed;
        Assertions.assertEquals(true, result)
    }

}