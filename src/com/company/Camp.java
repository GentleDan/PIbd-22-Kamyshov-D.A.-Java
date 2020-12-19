package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Camp<T extends Transport, G extends Adding> implements Iterator<T>, Iterable<T> {

    private final List<T> places;

    private final int maxCount;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int placeSizeWidth = 280;

    private final int placeSizeHeight = 160;

    private int currentIndex;

    public Camp(int picWidth, int picHeight) {
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        maxCount = width * height;
        places = new ArrayList<>();
        currentIndex = -1;
    }

    public boolean add(T vehicle) throws CampOverflowException, CampAlreadyHaveException {
        if (places.size() >= maxCount) {
            throw new CampOverflowException();
        }
        if (places.contains(vehicle)) {
            throw new CampAlreadyHaveException();
        }
        places.add(vehicle);
        return true;
    }

    public T delete(int index) throws CampNotFoundException {
        if (index < 0 || index >= places.size()) {
            throw new CampNotFoundException(index);
        }
        T vehicle = places.get(index);
        places.remove(index);
        return vehicle;
    }

    public void draw(Graphics g) {
        int changeHeight = 10;
        int width = pictureWidth / placeSizeWidth;
        drawMarking(g);
        for (int i = 0; i < places.size(); i++) {
            places.get(i).setPosition(i / width * placeSizeWidth + changeHeight,
                    i % width * placeSizeHeight + changeHeight, pictureWidth, pictureHeight);
            places.get(i).draw(g);
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

    public T get(int index) {
        if (index > -1 && index < places.size()) {
            return places.get(index);
        }
        return null;
    }

    public void sort() {
        places.sort((Comparator<? super T>) new TrackedVehicleComparer());
    }

    public void clear() {
        places.clear();
    }

    @Override
    public boolean hasNext() {
        return currentIndex < places.size() - 1;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentIndex++;
        return places.get(currentIndex);
    }

    @Override
    public Iterator<T> iterator() {
        currentIndex = -1;
        return this;
    }
}
