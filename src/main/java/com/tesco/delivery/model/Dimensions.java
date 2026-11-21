package com.tesco.delivery.model;

import java.util.Objects;

/**
 * Immutable length x breadth x height. Shared by containers and products.
 */
public final class Dimensions {

    private final int length;
    private final int breadth;
    private final int height;

    public Dimensions(int length, int breadth, int height) {
        if (length <= 0 || breadth <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "All dimensions must be positive, was " + length + "x" + breadth + "x" + height);
        }
        this.length = length;
        this.breadth = breadth;
        this.height = height;
    }

    /** Volume as long: 100 x 200 x 300 already needs 6 million, so int could overflow on bigger sizes. */
    public long volume() {
        return Math.multiplyExact(Math.multiplyExact((long) length, breadth), height);
    }

    public int getLength() {
        return length;
    }

    public int getBreadth() {
        return breadth;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dimensions)) {
            return false;
        }
        Dimensions other = (Dimensions) o;
        return length == other.length && breadth == other.breadth && height == other.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, breadth, height);
    }

    @Override
    public String toString() {
        return length + "x" + breadth + "x" + height;
    }
}