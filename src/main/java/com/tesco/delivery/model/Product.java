package com.tesco.delivery.model;

import java.util.Objects;

/**
 * A catalogue product and the space one unit of it needs.
 */
public final class Product {

    private final int productId;
    private final Dimensions dimensions;

    public Product(int productId, Dimensions dimensions) {
        this.productId = productId;
        this.dimensions = Objects.requireNonNull(dimensions, "dimensions");
    }

    public int getProductId() {
        return productId;
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
        if (!(o instanceof Product)) {
            return false;
        }
        return productId == ((Product) o).productId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(productId);
    }

    @Override
    public String toString() {
        return "Product{id=" + productId + ", " + dimensions + "}";
    }
}