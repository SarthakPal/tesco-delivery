package com.tesco.delivery.strategy;

import com.tesco.delivery.model.Container;
import com.tesco.delivery.model.Dimensions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmallestFitByVolumeStrategyTest {

    private final SmallestFitByVolumeStrategy strategy = new SmallestFitByVolumeStrategy();

    // Deliberately not sorted, to prove the strategy does not depend on input order
    private final List<Container> containers = List.of(
            new Container(3, "LARGE", new Dimensions(100, 200, 300)),   // 6,000,000
            new Container(1, "SMALL", new Dimensions(10, 20, 30)),      // 6,000
            new Container(2, "MEDIUM", new Dimensions(50, 60, 70))      // 210,000
    );

    @Test
    void picksSmallestThatFits() {
        assertEquals(1, strategy.select(containers, 1710).get().getId());
    }

    @Test
    void exactFitIsAllowed() {
        assertEquals(1, strategy.select(containers, 6000).get().getId());
    }

    @Test
    void movesUpWhenSmallIsTooSmall() {
        assertEquals(2, strategy.select(containers, 6001).get().getId());
    }

    @Test
    void emptyWhenNothingFits() {
        assertEquals(Optional.empty(), strategy.select(containers, 6_000_001));
    }
}