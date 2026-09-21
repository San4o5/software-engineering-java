package org.example;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Shop {
    private List<Product> catalog = new ArrayList<>();

    @Getter
    private List<Order> orderHistory = new ArrayList<>();

    // Додавання товару до каталогу магазину
    public void addProductToCatalog(Product product) {
        catalog.add(product);
    }

    // Збереження замовлення в історію
    public void saveOrder(Order order) {
        orderHistory.add(order);
    }

    // Пошук товару за ID (для додавання в кошик)
    public Product getProductById(int id) {
        for (Product p : catalog) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // Завдання 3: Пошук за назвою або категорією
    public List<Product> searchProducts(String query) {
        List<Product> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (Product p : catalog) {
            if (p.getName().toLowerCase().contains(lowerQuery) ||
                    (p.getCategory() != null && p.getCategory().getName().toLowerCase().contains(lowerQuery))) {
                results.add(p);
            }
        }
        return results;
    }

    public void printCatalog() {
        for (Product p : catalog) {
            System.out.println(p);
        }
    }
}