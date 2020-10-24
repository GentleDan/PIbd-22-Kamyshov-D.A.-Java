package com.company;

import java.awt.*;

public class Excavator extends TrackedVehicle {

    public Color dopColor;
    public boolean flasher;
    public boolean ladle;
    public boolean stand;
    private Adding adding;

    public float getStartPosX() {
        return startPosX;
    }

    protected void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
    }

    public boolean isFlasher() {
        return flasher;
    }

    protected void setFlasher(boolean flasher) {
        this.flasher = flasher;
    }

    public boolean isLadle() {
        return ladle;
    }

    protected void setLadle(boolean ladle) {
        this.ladle = ladle;
    }

    public boolean isStand() {
        return stand;
    }

    protected void setStand(boolean stand) {
        this.stand = stand;
    }

    public Excavator(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean flasher, boolean ladle, boolean stand, int add, int number) {
        super(maxSpeed, weight, mainColor, 100, 100);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.flasher = flasher;
        this.ladle = ladle;
        this.stand = stand;
        switch (add) {
            case 0:
                adding = new TrackCircle(number);
                break;
            case 1:
                adding = new TrackCross(number);
                break;
            case 2:
                adding = new TrackRect(number);
                break;
        }
    }

    public void draw(Graphics g) {
        //ковш
        if (ladle) {
            int[] Ladle_pt_x = new int[10];
            Ladle_pt_x[0] = startPosX + 160;
            Ladle_pt_x[1] = startPosX + 200;
            Ladle_pt_x[2] = startPosX + 260;
            Ladle_pt_x[3] = startPosX + 250;
            Ladle_pt_x[4] = startPosX + 230;
            Ladle_pt_x[5] = startPosX + 220;
            Ladle_pt_x[6] = startPosX + 200;
            Ladle_pt_x[7] = startPosX + 220;
            Ladle_pt_x[8] = startPosX + 200;
            Ladle_pt_x[9] = startPosX + 160;
            int[] Ladle_pt_y = new int[10];
            Ladle_pt_y[0] = startPosY + 40;
            Ladle_pt_y[1] = startPosY;
            Ladle_pt_y[2] = startPosY + 60;
            Ladle_pt_y[3] = startPosY + 80;
            Ladle_pt_y[4] = startPosY + 60;
            Ladle_pt_y[5] = startPosY + 80;
            Ladle_pt_y[6] = startPosY + 60;
            Ladle_pt_y[7] = startPosY + 40;
            Ladle_pt_y[8] = startPosY + 20;
            Ladle_pt_y[9] = startPosY + 60;
            g.setColor(mainColor);
            g.fillPolygon(Ladle_pt_x, Ladle_pt_y, 10);
            g.setColor(Color.BLACK);
            g.drawPolygon(Ladle_pt_x, Ladle_pt_y, 10);
        }
        //подставка
        if (stand) {
            g.drawLine(startPosX + 50, startPosY + 70, startPosX + 20, startPosY + 130);
            g.drawLine(startPosX, startPosY + 130, startPosX + 40, startPosY + 130);
        }
        //фонарик
        if (flasher) {
            g.setColor(Color.RED);
            g.fillRect(startPosX + 70, startPosY + 5, trackedVehicleWidth - 92, trackedVehicleHeight - 85);
            g.setColor(Color.BLACK);
            g.drawRect(startPosX + 70, startPosY + 5, trackedVehicleWidth - 92, trackedVehicleHeight - 85);
        }
        super.draw(g);
        adding.draw(g, startPosX, startPosY, trackedVehicleWidth, trackedVehicleHeight);
    }
}
