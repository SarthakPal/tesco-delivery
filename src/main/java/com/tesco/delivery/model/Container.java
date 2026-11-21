package com.tesco.delivery.model;

import java.util.Objects;

/**
 * A container type a vehicle can carry, e.g. SMALL, MEDIUM, LARGE.
 */
public final class Container {

    private final int id;
    private final String name;
    private final Dimensions dimensions;

    public Container(int id, String name, Dimensions dimensions) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Container name must not be blank");
        }
        this.id = id;
        this.name = name.trim();
        this.dimensions = Objects.requireNonNull(dimensions, "dimensions");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public long getVolume() {
        return dimensions.volume();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Container)) {
            return false;
        }
        return id == ((Container) o).id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return name + "{id=" + id + ", " + dimensions + ", volume=" + getVolume() + "}";
    }
}