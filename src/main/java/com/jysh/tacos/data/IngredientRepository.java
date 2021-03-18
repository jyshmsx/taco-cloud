package com.jysh.tacos.data;

import com.jysh.tacos.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save (Ingredient ingredient);
}
