package com.company;

import java.awt.*;

public class Camp<T extends Transport, G extends Adding> {

    private final Object[] places;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int placeSizeWidth = 280;

    private final int placeSizeHeight = 160;

    public Camp(int picWidth, int picHeight) {
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        places = new Object[width * height];
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    public boolean add(T vehicle) {
        int changeHeight = 10;
        int width = pictureWidth / placeSizeWidth;

        for (int i = 0; i < places.length; i++) {
            if (CheckFreePlace(i)) {
                vehicle.setPosition(i / width * placeSizeWidth + changeHeight,
                        i % width * placeSizeHeight + changeHeight, pictureWidth,
                        pictureHeight);
                places[i] = vehicle;
                return true;
            }
        }
        return false;
    }

    public T delete(int index) {
        if (index < 0 || index > places.length) {
            return null;
        }
        if (!CheckFreePlace(index)) {
            T vehicle = (T) places[index];
            places[index] = null;
            return vehicle;
        }
        return null;
    }

    private boolean CheckFreePlace(int indexPlace) {
        return places[indexPlace] == null;
    }

    public boolean equal(int count) {
        int countPlaces = 0;
        for (Object object : places) {
            if (object != null) {
                countPlaces++;
            }
        }
        return countPlaces == count;
    }

    public boolean inequal(int count) {
        int countPlaces = 0;
        for (Object object : places) {
            if (object != null) {
                countPlaces++;
            }
        }
        return countPlaces != count;
    }

    public void draw(Graphics g) {
        drawMarking(g);
        for (Object place : places) {
            if (place != null) {
                T placeT = (T) place;
                placeT.draw(g);
            }
        }
    }

    private void drawMarking(Graphics g) {
        double changeLong = 1.2;
        for (int i = 0; i < pictureWidth / placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / placeSizeHeight + 1; ++j) {
                g.drawLine(i * placeSizeWidth, j * placeSizeHeight, (int) (i *
                        placeSizeWidth + placeSizeWidth / changeLong), j * placeSizeHeight);
            }
            g.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth,
                    (pictureHeight / placeSizeHeight) * placeSizeHeight);
        }
    }
}
