package com.afkl.travel.exercise.model;

public enum LocationType {
    COUNTRY("country"),
    CITY("city"),
    AIRPORT("airport");
    private final String text;
    LocationType(final String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
