package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shop shop = new Shop();
        Cart cart = new Cart();

        // Ініціалізація
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        shop.addProductToCatalog(new Product(1, "Ноутбук", 19999.99, "Ноутбук", electronics));
        shop.addProductToCatalog(new Product(2, "Смартфон", 12999.50, "Смартфон", smartphones));
        shop.addProductToCatalog(new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories));

        while (true) {
            System.out.println("\n1 - Каталог | 2 - Додати в кошик | 3 - Видалити з кошика | 4 - Кошик | 5 - Замовити | 6 - Історія | 7 - Пошук | 0 - Вийти");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    shop.printCatalog();
                    break;
                case 2:
                    System.out.println("ID товару для додавання:");
                    Product p = shop.getProductById(scanner.nextInt());
                    if (p != null) { cart.addProduct(p); System.out.println("Додано!"); }
                    else { System.out.println("Не знайдено."); }
                    break;
                case 3:
                    System.out.println("ID товару для видалення:");
                    if (cart.removeProductById(scanner.nextInt())) System.out.println("Видалено!");
                    else System.out.println("Такого товару немає в кошику.");
                    break;
                case 4:
                    System.out.println(cart);
                    break;
                case 5:
                    if (!cart.getProducts().isEmpty()) {
                        Order order = new Order(cart);
                        shop.saveOrder(order); // Зберігаємо в магазин
                        System.out.println("Оформлено:\n" + order);
                        cart.clear();
                    } else System.out.println("Кошик порожній.");
                    break;
                case 6:
                    for (Order o : shop.getOrderHistory()) System.out.println(o + "\n---");
                    break;
                case 7:
                    System.out.println("Що шукаємо?");
                    List<Product> results = shop.searchProducts(scanner.nextLine());
                    if (results.isEmpty()) System.out.println("Нічого не знайдено.");
                    else for (Product res : results) System.out.println(res);
                    break;
                case 0:
                    return;
            }
        }
    }
}