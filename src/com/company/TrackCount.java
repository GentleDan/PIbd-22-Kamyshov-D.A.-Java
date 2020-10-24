package com.company;

public enum TrackCount {

    four,
    five,
    six;

    public static TrackCount getCount(int count) {
        switch (count) {
            case 0:
                return four;
            case 1:
                return five;
            case 2:
                return six;
        }
        return null;
    }
}
