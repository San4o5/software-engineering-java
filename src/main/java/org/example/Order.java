package org.example;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Order {
    private List<Product> products; // Список товарів у замовленні
    private double totalPrice; // Загальна вартість замовлення

    @Setter
    private String status; // Статус замовлення

    public Order(Cart cart) {
        this.products = new ArrayList<>(cart.getProducts()); // Копіювання товарів з кошика
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове"; // Стандартний статус при створенні замовлення
    }
}


