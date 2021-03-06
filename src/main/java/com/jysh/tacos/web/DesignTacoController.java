package com.jysh.tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jysh.tacos.Order;
import com.jysh.tacos.data.IngredientRepository;
import com.jysh.tacos.data.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import lombok.extern.slf4j.Slf4j;

import com.jysh.tacos.Taco;
import com.jysh.tacos.Ingredient;
import com.jysh.tacos.Ingredient.Type;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private TacoRepository designRepo;

    @Autowired
    public  DesignTacoController(IngredientRepository ingredientRepo,
                                 TacoRepository designRepo){
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type [] types = Ingredient.Type.values();
        for (Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        return "design";
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }
        Taco saved = designRepo.save(design);
        order.addDesign(saved);

        log.info("processing design: " + design);
        return "redirect:/orders/current";
    }


    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type){
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
