package com.tesco.delivery;

import com.tesco.delivery.catalog.ProductCatalog;
import com.tesco.delivery.model.Container;
import com.tesco.delivery.model.Dimensions;
import com.tesco.delivery.model.Order;
import com.tesco.delivery.model.OrderLine;
import com.tesco.delivery.model.Product;
import com.tesco.delivery.service.ContainerAllocationService;
import com.tesco.delivery.strategy.SmallestFitByVolumeStrategy;

import java.util.List;

/**
 * Demo entry point using the example from the problem statement.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        List<Container> containers = List.of(
                new Container(1, "SMALL", new Dimensions(10, 20, 30)),
                new Container(2, "MEDIUM", new Dimensions(50, 60, 70)),
                new Container(3, "LARGE", new Dimensions(100, 200, 300))
        );

        ProductCatalog catalog = new ProductCatalog(List.of(
                new Product(1, new Dimensions(2, 4, 10)),
                new Product(2, new Dimensions(10, 30, 4)),
                new Product(3, new Dimensions(5, 6, 7))
        ));

        Order order = new Order(List.of(
                new OrderLine(1, 3),
                new OrderLine(3, 7)
        ));

        ContainerAllocationService service =
                new ContainerAllocationService(catalog, containers, new SmallestFitByVolumeStrategy());

        System.out.println("Required volume: " + service.calculateRequiredVolume(order));
        System.out.println(service.findContainer(order)
                .map(c -> "Selected container: " + c)
                .orElse("No container can fit this order"));
    }
}