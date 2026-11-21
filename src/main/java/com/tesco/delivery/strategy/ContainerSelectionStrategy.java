package com.tesco.delivery.strategy;

import com.tesco.delivery.model.Container;

import java.util.List;
import java.util.Optional;

/**
 * How to pick a container for an order (Strategy pattern).
 * Different strategies can be plugged in without changing the service.
 */
public interface ContainerSelectionStrategy {

    /**
     * @param containers     available containers, never null
     * @param requiredVolume total volume the order needs
     * @return the chosen container, or empty if none can hold the order
     */
    Optional<Container> select(List<Container> containers, long requiredVolume);
}