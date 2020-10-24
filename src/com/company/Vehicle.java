package com.company;

import java.awt.*;

public abstract class Vehicle implements Transport {

    protected int startPosX;

    protected int startPosY;

    protected int pictureWidth;

    protected int pictureHeight;

    protected int vehicleWight = 100;

    protected int vehicleHeight = 100;

    public int maxSpeed;

    public float weight;

    public Color mainColor;

    public void setPosition(int x, int y, int width, int height) {
        if (x >= 0 && x + vehicleWight < width && y >= 0 && y + vehicleHeight < height) {
            startPosX = x;
            startPosY = y;
            pictureWidth = width;
            pictureHeight = height;
        }
    }

    public abstract void draw(Graphics g);

    public abstract void moveTransport(Direction direction);

    public int getMaxSpeed() {
        return maxSpeed;
    }

    protected void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getWeight() {
        return weight;
    }

    protected void setWeight(float weight) {
        this.weight = weight;
    }

    public Color getMainColor() {
        return mainColor;
    }

    protected void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }
}
