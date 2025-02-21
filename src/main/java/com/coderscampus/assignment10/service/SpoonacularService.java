package com.coderscampus.assignment10.service;

import com.coderscampus.assignment10.dto.DayResponse;
import com.coderscampus.assignment10.dto.WeekResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class SpoonacularService {



    @Value("${spoonacular.api.key}")
    private String API_KEY;
    @Value("${spoonacular.urls.base}")
    private String BASE_URL;
    @Value("${spoonacular.urls.mealplan}")
    private String MEAL_PLAN_URL;

    private final RestTemplate restTemplate = new RestTemplate();

    public DayResponse getDayMeals(String numCalories, String diet, String exclusions) {

        String url = buildUrl("day", numCalories, diet, exclusions);

        ResponseEntity<DayResponse> response = restTemplate.getForEntity(url, DayResponse.class);
        return response.getBody();
    }

    public WeekResponse getWeekMeals(String numCalories, String diet, String exclusions) {

        String url = buildUrl("week", numCalories, diet, exclusions);

        ResponseEntity<WeekResponse> response = restTemplate.getForEntity(url, WeekResponse.class);
        return response.getBody();
    }

    private String buildUrl(String timeFrame, String numCalories, String diet, String exclusions) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL + MEAL_PLAN_URL)
                .queryParam("timeFrame", timeFrame)
                .queryParam("apiKey", API_KEY)
                .queryParamIfPresent("targetCalories", Optional.ofNullable(numCalories))
                .queryParamIfPresent("diet", Optional.ofNullable(diet))
                .queryParamIfPresent("exclude", Optional.ofNullable(exclusions));
        return builder.toUriString();
    }
}
