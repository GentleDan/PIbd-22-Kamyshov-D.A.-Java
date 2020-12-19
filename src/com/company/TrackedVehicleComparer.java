package com.company;

import java.util.Comparator;

public class TrackedVehicleComparer implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle first, Vehicle second) {
        if (!first.getClass().getSimpleName().equals(second.getClass().getSimpleName())) {
            return first.getClass().getSimpleName().compareTo(second.getClass().getSimpleName());
        }

        int result;
        switch (first.getClass().getSimpleName()) {
            case "TrackedVehicle" -> {
                result = comparerTrackedVehicle((TrackedVehicle) first, (TrackedVehicle) second);
                return result;
            }
            case "Excavator" -> {
                result = comparerExcavator((Excavator) first, (Excavator) second);
                return result;
            }
        }
        return 100;
    }

    private int comparerTrackedVehicle(TrackedVehicle first, TrackedVehicle second) {
        if (first.getMaxSpeed() != second.getMaxSpeed()) {
            return Integer.compare(first.getMaxSpeed(), second.getMaxSpeed());
        }
        if (first.getWeight() != second.getWeight()) {
            return Float.compare(first.getWeight(), second.getWeight());
        }
        if (first.getMainColor() != second.getMainColor()) {
            return Integer.compare(first.getMainColor().getRGB(), second.getMainColor().getRGB());
        }
        return 0;
    }

    private int comparerExcavator(Excavator first, Excavator second) {
        int result = comparerTrackedVehicle(first, second);
        if (result != 0) {
            return result;
        }

        if (first.getDopColor() != second.getDopColor()) {
            return Integer.compare(first.getDopColor().getRGB(), second.getDopColor().getRGB());
        }
        if (first.isFlasher() != second.isFlasher()) {
            return Boolean.compare(first.isFlasher(), second.isFlasher());
        }
        if (first.isLadle() != second.isLadle()) {
            return Boolean.compare(first.isLadle(), second.isLadle());
        }
        if (first.isStand() != second.isStand()) {
            return Boolean.compare(first.isStand(), second.isStand());
        }
        if (first.getAdding() != null && second.getAdding() != null
                && !(first.getAdding().toString().equals(second.getAdding().toString()))) {
            return first.getAdding().toString().compareTo(second.getAdding().toString());
        }
        if (first.getAdding() == null && second.getAdding() != null) {
            return 1;
        }
        if (first.getAdding() != null && second.getAdding() == null) {
            return -1;
        }
        return 0;
    }

}
