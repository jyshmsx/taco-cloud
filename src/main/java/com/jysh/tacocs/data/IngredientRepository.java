package com.jysh.tacocs.data;

import com.jysh.tacocs.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save (Ingredient ingredient);
}
