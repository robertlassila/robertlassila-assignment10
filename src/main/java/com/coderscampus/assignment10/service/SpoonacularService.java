package com.coderscampus.assignment10.service;

import com.coderscampus.assignment10.dto.DayResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SpoonacularService {

    private final String API_KEY = "474ffd97768c4c7d93e0b77c18748298";
    private final String BASE_URL = "https://api.spoonacular.com/mealplanner/generate";

    private final RestTemplate restTemplate = new RestTemplate();

    public DayResponse getDayMeals(String numCalories, String diet, String exclusions) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("timeFrame", "day")
                .queryParam("targetCalories", numCalories)
                .queryParam("diet", diet)
                .queryParam("exclude", exclusions)
                .queryParam("apiKey", API_KEY)
                .toUriString();

        ResponseEntity<DayResponse> response = restTemplate.getForEntity(url, DayResponse.class);
        return response.getBody();
    }
}
