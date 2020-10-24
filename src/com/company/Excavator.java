package com.company;

import java.awt.*;

public class Excavator {
    private int startPosX;
    private int startPosY;
    private int pictureWidth;
    private int pictureHeight;
    private final int excavatorWidth = 100;
    private final int excavatorHeight = 100;
    private final double changeWidth = 2.7;
    private final double changeHeight = 1.7;
    public int maxSpeed;
    public float weight;
    public Color mainColor;
    public Color dopColor;
    public boolean flasher;
    public boolean ladle;
    public boolean stand;
    private Track track;

    public float getStartPosX() {
        return startPosX;
    }

    private void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    private void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getWeight() {
        return weight;
    }

    private void setWeight(float weight) {
        this.weight = weight;
    }

    public Color getMainColor() {
        return mainColor;
    }

    private void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    public Color getDopColor() {
        return dopColor;
    }

    private void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }

    public boolean isFlasher() {
        return flasher;
    }

    private void setFlasher(boolean flasher) {
        this.flasher = flasher;
    }

    public boolean isLadle() {
        return ladle;
    }

    private void setLadle(boolean ladle) {
        this.ladle = ladle;
    }

    public boolean isStand() {
        return stand;
    }

    private void setStand(boolean stand) {
        this.stand = stand;
    }

    public Excavator(int maxSpeed, float weight, Color mainColor, Color dopColor,
                     boolean flasher, boolean ladle, boolean stand, int trackCount) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.flasher = flasher;
        this.ladle = ladle;
        this.stand = stand;
        track = new Track();
        track.setNumber(trackCount);
    }

    public void setPosition(int x, int y, int width, int height) {
        if (x >= 0 && x + excavatorWidth < width && y >= 0 && y + excavatorHeight < height) {
            startPosX = x;
            startPosY = y;
            pictureWidth = width;
            pictureHeight = height;
        }

    }

    public void moveTrans(Direction dir) {
        float step = maxSpeed * 100 / weight;
        switch (dir) {
            case Right:
                if (startPosX + step < pictureWidth - excavatorWidth * changeWidth) {
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
                if (startPosY + step < pictureHeight - excavatorHeight * changeHeight) {
                    startPosY += step;
                }
                break;
        }
    }

    public void drawPicture(Graphics g) {
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
            g.setColor(dopColor);
            g.fillPolygon(Ladle_pt_x, Ladle_pt_y, 10);
            g.setColor(mainColor);
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
            g.fillRect(startPosX + 70, startPosY + 5, excavatorWidth - 92, excavatorHeight - 85);
            g.setColor(mainColor);
            g.drawRect(startPosX + 70, startPosY + 5, excavatorWidth - 92, excavatorHeight - 85);
        }
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
        g.setColor(dopColor);
        g.fillRect(startPosX + 40, startPosY + 60, excavatorWidth - 65, excavatorHeight - 60);
        g.setColor(mainColor);
        g.drawRect(startPosX + 40, startPosY + 60, excavatorWidth - 65, excavatorHeight - 60);
        g.setColor(dopColor);
        g.fillPolygon(Exc_pt_x, Exc_pt_y, 6);
        g.setColor(mainColor);
        g.drawPolygon(Exc_pt_x, Exc_pt_y, 6);
        //дверь
        g.setColor(Color.GRAY);
        g.fillRect(startPosX + 110, startPosY + 40, excavatorWidth - 65, excavatorHeight - 60);
        g.setColor(mainColor);
        g.fillRect(startPosX + 110, startPosY + 60, excavatorWidth - 65, excavatorHeight - 80);
        track.DrawTrack(g, startPosX, startPosY, excavatorWidth, excavatorHeight);
    }
}
