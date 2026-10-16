
package com.example.smartmallcompanion.models;

public class Store {

    private String name;
    private String category;
    private String floor;
    private String description;
    private String offer;

    public Store(String name, String category, String floor) {
        this(name, category, floor, "Discover this store at the mall.",
                "Check for current offers");
    }

    public Store(String name, String category, String floor,
                 String description, String offer) {
        this.name = name;
        this.category = category;
        this.floor = floor;
        this.description = description;
        this.offer = offer;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getFloor() {
        return floor;
    }

    public String getDescription() {
        return description;
    }

    public String getOffer() {
        return offer;
    }
}
