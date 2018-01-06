package com.edu.recipies.controller;

import com.edu.recipies.commands.RecipeCommand;
import com.edu.recipies.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping(value = "recipe/{id}/show", method = RequestMethod.GET)
    public String showRecipe(@PathVariable long id, Model model) {
        recipeService.findById(id).ifPresent(r -> model.addAttribute("recipe", r));
        return "recipe/show";
    }

    @RequestMapping(value = "recipe/{id}/update", method = RequestMethod.GET)
    public String updateRecipe(@PathVariable Long id, Model model) {
        recipeService.findCommandById(id).ifPresent(r -> model.addAttribute("recipe", r));
        return "recipe/recipeForm";
    }


    @RequestMapping(value = "recipe/new", method = RequestMethod.GET)
    public String newRecipeForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }


    @RequestMapping(value = "recipe", method = RequestMethod.POST)
    public String handleRecipeForm(@ModelAttribute RecipeCommand recipeCommand) {
        Optional<RecipeCommand> recipeCommandSaved = recipeService.saveRecipeCommand(recipeCommand);
        return recipeCommandSaved
                .map(command -> "redirect:/recipe/" + command.getId() + "/show")
                .orElse("recipe/recipeForm");
    }


    @RequestMapping(value = "recipe/delete", method = RequestMethod.GET)
    public String deleteRecipe(@RequestParam Long id) {
        recipeService.deleteById(id);
        return "redirect:/all";
    }
}
