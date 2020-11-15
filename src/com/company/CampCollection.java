package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CampCollection {

    private final Map<String, Camp<Transport, Adding>> campStages;

    private final int pictureWidth;

    private final int pictureHeight;

    public CampCollection(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        campStages = new HashMap<>();
    }

    public Set<String> keys() {
        return campStages.keySet();
    }

    public void addCamp(String name) {
        if (campStages.containsKey(name)) {
            return;
        }
        campStages.put(name, new Camp<>(pictureWidth, pictureHeight));
    }

    public void deleteCamp(String name) {
        campStages.remove(name);
    }

    public Camp<Transport, Adding> get(String name) {
        if (campStages.containsKey(name)) {
            return campStages.get(name);
        }
        return null;
    }

    public Transport get(String name, int index) {
        if (campStages.containsKey(name)) {
            return campStages.get(name).get(index);
        }
        return null;
    }
}
