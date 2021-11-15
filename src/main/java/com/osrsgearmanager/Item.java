package com.osrsgearmanager;

import javafx.scene.image.Image;

import java.util.Map;

public class Item {

    private String name;
    private String id;
    private Image icon;
    private Map<String, Double> stats;

    public Item(String name, String id, Image icon, Map<String, Double> itemStats) {
        this.name = name;
        this.id = id;
        this.icon = icon;
        this.stats = itemStats;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Image getIcon() {
        return icon;
    }

    public Map<String, Double> getStats() {
        return stats;
    }
}
