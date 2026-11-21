package com.tesco.delivery.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DimensionsTest {

    @Test
    void volumeIsLengthTimesBreadthTimesHeight() {
        assertEquals(6000L, new Dimensions(10, 20, 30).volume());
    }

    @Test
    void largeVolumeDoesNotOverflow() {
        // 2,000 x 2,000 x 2,000 = 8 billion, which is bigger than the int limit (~2.1 billion)
        assertEquals(8_000_000_000L, new Dimensions(2000, 2000, 2000).volume());
    }

    @Test
    void rejectsZeroOrNegativeSides() {
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(0, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(1, -1, 1));
    }
}