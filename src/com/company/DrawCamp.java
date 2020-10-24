package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawCamp extends JPanel {
    private final Camp<TrackedVehicle, Adding> trackedVehicleCamp;

    public DrawCamp(Camp<TrackedVehicle, Adding> trackedVehicleCamp) {
        this.trackedVehicleCamp = trackedVehicleCamp;
    }

    protected void paintComponent(Graphics g) {
        if (trackedVehicleCamp != null) {
            trackedVehicleCamp.draw(g);
        }
    }

    public Camp<TrackedVehicle, Adding> getTrackedVehicleCamp() {
        return trackedVehicleCamp;
    }
}
