package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString

public class Category {
    private int id; // Унікальний ідентифікатор категорії
    private String name; // Назва категорії
}

