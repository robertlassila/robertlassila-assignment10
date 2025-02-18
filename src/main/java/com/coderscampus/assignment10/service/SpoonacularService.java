package com.coderscampus.assignment10.service;

import com.coderscampus.assignment10.dto.DayResponse;
import com.coderscampus.assignment10.dto.WeekResponse;
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
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("timeFrame", timeFrame)
                .queryParam("apiKey", API_KEY);

        if (numCalories != null) builder.queryParam("targetCalories", numCalories);
        if (diet != null) builder.queryParam("diet", diet);
        if (exclusions != null) builder.queryParam("exclude", exclusions);

        return builder.toUriString();
    }
}
