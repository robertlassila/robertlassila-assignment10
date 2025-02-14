package com.coderscampus.assignment10.web;


import com.coderscampus.assignment10.dto.DayResponse;
import com.coderscampus.assignment10.dto.WeekResponse;
import com.coderscampus.assignment10.service.SpoonacularService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealPlanController {

    private final SpoonacularService spoonacularService;

    public MealPlanController(SpoonacularService spoonacularService) {
        this.spoonacularService = spoonacularService;
    }

    @GetMapping("mealplanner/day")
    public ResponseEntity<DayResponse> getDayMeals(
        @RequestParam String numCalories,
        @RequestParam(required = false) String diet,
        @RequestParam(required= false) String exclusions) {
        return ResponseEntity.ok(spoonacularService.getDayMeals(numCalories, diet, exclusions));
    }

    @GetMapping("mealplanner/week")
    public ResponseEntity<WeekResponse> getWeekMeals(
            @RequestParam String numCalories,
            @RequestParam(required = false) String diet,
            @RequestParam(required= false) String exclusions) {
        return ResponseEntity.ok(spoonacularService.getWeekMeals(numCalories, diet, exclusions));
    }



}
