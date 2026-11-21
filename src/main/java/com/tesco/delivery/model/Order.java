package com.tesco.delivery.model;

import java.util.List;

/**
 * A customer order: an immutable list of order lines.
 */
public final class Order {

    private final List<OrderLine> lines;

    public Order(List<OrderLine> lines) {
        if (lines == null || lines.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one line");
        }
        for (OrderLine line : lines) {
            if (line == null) {
                throw new IllegalArgumentException("Order must not contain null lines");
            }
        }
        this.lines = List.copyOf(lines);
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "Order{lines=" + lines + "}";
    }
}