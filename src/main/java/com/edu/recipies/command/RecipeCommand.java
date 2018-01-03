package com.edu.recipies.command;

import com.edu.recipies.model.Category;
import com.edu.recipies.model.Difficulty;
import com.edu.recipies.model.Ingredient;
import com.edu.recipies.model.Notes;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RecipeCommand {

    private Long id;
    private String  url;
    private String  source;
    private Integer servings;
    private Integer cookTime;
    private Integer prepTime;
    private String  description;
    private String directions;
    private Difficulty difficulty;
    private Byte[] image;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();
    private NotesCommand notes;

}
