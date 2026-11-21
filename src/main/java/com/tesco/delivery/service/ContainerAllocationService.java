package com.tesco.delivery.service;

import com.tesco.delivery.catalog.ProductCatalog;
import com.tesco.delivery.model.Container;
import com.tesco.delivery.model.Order;
import com.tesco.delivery.model.OrderLine;
import com.tesco.delivery.model.Product;
import com.tesco.delivery.strategy.ContainerSelectionStrategy;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Works out how much space an order needs and asks the strategy for a container.
 * Immutable and thread-safe: create once, use for every order.
 */
public final class ContainerAllocationService {

    private final ProductCatalog catalog;
    private final List<Container> containers;
    private final ContainerSelectionStrategy strategy;

    public ContainerAllocationService(ProductCatalog catalog,
                                      List<Container> containers,
                                      ContainerSelectionStrategy strategy) {
        this.catalog = Objects.requireNonNull(catalog, "catalog");
        this.strategy = Objects.requireNonNull(strategy, "strategy");
        if (containers == null || containers.isEmpty()) {
            throw new IllegalArgumentException("At least one container is required");
        }
        for (Container container : containers) {
            if (container == null) {
                throw new IllegalArgumentException("containers must not contain null");
            }
        }
        this.containers = List.copyOf(containers);
    }

    /** Total volume of the order = sum of (product volume x quantity) for every line. */
    public long calculateRequiredVolume(Order order) {
        Objects.requireNonNull(order, "order");
        long total = 0;
        for (OrderLine line : order.getLines()) {
            Product product = catalog.getProduct(line.getProductId());
            long lineVolume = Math.multiplyExact(product.getVolume(), line.getQuantity());
            total = Math.addExact(total, lineVolume);
        }
        return total;
    }

    /** The container chosen by the strategy, or empty if the order is too big for every container. */
    public Optional<Container> findContainer(Order order) {
        return strategy.select(containers, calculateRequiredVolume(order));
    }
}