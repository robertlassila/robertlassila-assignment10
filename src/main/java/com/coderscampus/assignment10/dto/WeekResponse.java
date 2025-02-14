package com.coderscampus.assignment10.dto;

import java.util.Map;

public class WeekResponse {
    private Map<String, DayResponse> week;

    public Map<String, DayResponse> getWeek() {
        return week;
    }
    public void setWeek(Map<String, DayResponse> week) {
        this.week = week;
    }
}
