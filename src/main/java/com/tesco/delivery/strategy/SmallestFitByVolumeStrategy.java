package com.tesco.delivery.strategy;

import com.tesco.delivery.model.Container;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Picks the smallest container whose volume is at least the required volume.
 * Smallest = least wasted space, so bigger containers stay free for bigger orders.
 */
public final class SmallestFitByVolumeStrategy implements ContainerSelectionStrategy {

    @Override
    public Optional<Container> select(List<Container> containers, long requiredVolume) {
        return containers.stream()
                .filter(container -> container.getVolume() >= requiredVolume)
                .min(Comparator.comparingLong(Container::getVolume));
    }
}