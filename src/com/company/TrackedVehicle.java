package com.company;

import java.awt.*;

public class TrackedVehicle extends Vehicle implements Comparable<TrackedVehicle>{

    protected int trackedVehicleWidth = 100;
    protected int trackedVehicleHeight = 100;
    protected double changeWidth = 3.1;
    protected double changeHeight = 2.1;
    protected String separator = ";";

    public TrackedVehicle(int maxSpeed, float weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
    }

    protected TrackedVehicle(int maxSpeed, float weight, Color mainColor, int carWidth, int carHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.trackedVehicleWidth = carWidth;
        this.trackedVehicleHeight = carHeight;
    }

    public TrackedVehicle(String info) {
        String[] strs = info.split(separator);
        if (strs.length == 3) {
            maxSpeed = Integer.parseInt(strs[0]);
            weight = Float.parseFloat(strs[1]);
            mainColor = Color.decode(strs[2]);
        }
    }

    @Override
    public void draw(Graphics g) {
        //корпус
        int[] Exc_pt_x = new int[6];
        Exc_pt_x[0] = startPosX + 60;
        Exc_pt_x[1] = startPosX + 160;
        Exc_pt_x[2] = startPosX + 160;
        Exc_pt_x[3] = startPosX + 160;
        Exc_pt_x[4] = startPosX + 60;
        Exc_pt_x[5] = startPosX + 60;
        int[] Exc_pt_y = new int[6];
        Exc_pt_y[0] = startPosY + 20;
        Exc_pt_y[1] = startPosY + 20;
        Exc_pt_y[2] = startPosY + 40;
        Exc_pt_y[3] = startPosY + 100;
        Exc_pt_y[4] = startPosY + 100;
        Exc_pt_y[5] = startPosY + 20;
        g.setColor(mainColor);
        g.fillRect(startPosX + 40, startPosY + 60, trackedVehicleWidth - 65, trackedVehicleHeight - 60);
        g.setColor(Color.BLACK);
        g.drawRect(startPosX + 40, startPosY + 60, trackedVehicleWidth - 65, trackedVehicleHeight - 60);
        g.setColor(mainColor);
        g.fillPolygon(Exc_pt_x, Exc_pt_y, 6);
        g.setColor(Color.BLACK);
        g.drawPolygon(Exc_pt_x, Exc_pt_y, 6);
        //дверь
        g.setColor(Color.GRAY);
        g.fillRect(startPosX + 110, startPosY + 40, trackedVehicleWidth - 65, trackedVehicleHeight - 60);
        g.setColor(Color.BLACK);
        g.fillRect(startPosX + 110, startPosY + 60, trackedVehicleWidth - 65, trackedVehicleHeight - 80);
        //начальное кол-во колес
        g.fillOval(startPosX + 40, startPosY + 105, trackedVehicleWidth - 70, trackedVehicleHeight - 70);
        g.fillOval(startPosX + 70, startPosY + 105, trackedVehicleWidth - 70, trackedVehicleHeight - 70);
        g.fillOval(startPosX + 100, startPosY + 105, trackedVehicleWidth - 70, trackedVehicleHeight - 70);
        g.fillOval(startPosX + 130, startPosY + 105, trackedVehicleWidth - 70, trackedVehicleHeight - 70);
        g.drawLine(startPosX + 45, startPosY + 106, startPosX + 155, startPosY + 106);
        g.drawLine(startPosX + 45, startPosY + 133, startPosX + 155, startPosY + 133);
    }

    @Override
    public void moveTransport(Direction direction) {
        float step = maxSpeed * 100 / weight;
        switch (direction) {
            case Right:
                if (startPosX + step < pictureWidth - trackedVehicleWidth * changeWidth) {
                    startPosX += step;
                }
                break;
            case Left:
                if (startPosX - step > 0) {
                    startPosX -= step;
                }
                break;
            case Up:
                if (startPosY - step > 0) {
                    startPosY -= step;
                }

                break;
            case Down:
                if (startPosY + step < pictureHeight - trackedVehicleHeight * changeHeight) {
                    startPosY += step;
                }
                break;
        }
    }

    @Override
    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRGB();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof TrackedVehicle trackedVehicleObject)) {
            return false;
        }
        return equals(trackedVehicleObject);
    }

    public boolean equals(TrackedVehicle other) {
        if (other == null) {
            return false;
        }
        if (!this.getClass().getSimpleName().equals(other.getClass().getSimpleName())) {
            return false;
        }
        if (maxSpeed != other.maxSpeed) {
            return false;
        }
        if (weight != other.weight) {
            return false;
        }
        if (mainColor.getRGB() != other.mainColor.getRGB()) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(TrackedVehicle trackedVehicle) {
        if (maxSpeed != trackedVehicle.maxSpeed) {
            return Integer.compare(maxSpeed, trackedVehicle.maxSpeed);
        }
        if (weight != trackedVehicle.weight) {
            return Float.compare(weight, trackedVehicle.weight);
        }
        if (mainColor.getRGB() != trackedVehicle.mainColor.getRGB()) {
            return Integer.compare(mainColor.getRGB(), trackedVehicle.getMainColor().getRGB());
        }
        return 0;
    }
}
