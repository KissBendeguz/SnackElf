package hu.elte.snackelf.controllers;

import hu.elte.snackelf.entities.Food;
import hu.elte.snackelf.entities.FoodType;
import hu.elte.snackelf.repositories.FoodRepository;
import hu.elte.snackelf.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;

    @PostMapping
    public ResponseEntity<?> createTempFoods() {
        List<Food> foods = new ArrayList<>();

        foods.add(new Food("Saláta", FoodType.HAPPY));
        foods.add(new Food("Friss gyümölcs", FoodType.HAPPY));
        foods.add(new Food("Grillezett csirkemell", FoodType.HAPPY));
        foods.add(new Food("Zöldségleves", FoodType.HAPPY));

        foods.add(new Food("Hamburger", FoodType.SAD));
        foods.add(new Food("Sült krumpli", FoodType.SAD));
        foods.add(new Food("Cukros üdítő", FoodType.SAD));
        foods.add(new Food("Csokoládé", FoodType.SAD));

        foods.add(new Food("Tészta", FoodType.NEUTRAL));
        foods.add(new Food("Rántotta", FoodType.NEUTRAL));
        foods.add(new Food("Szendvics", FoodType.NEUTRAL));
        foods.add(new Food("Pirítós", FoodType.NEUTRAL));

        foodRepository.saveAll(foods);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Set<Food>> getFoods(){
        Set<Food> foods = foodService.getFoods();

        if (foods.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(foods);
    }
}
