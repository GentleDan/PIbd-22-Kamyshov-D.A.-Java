package com.company;

public enum TrackCount {

    four,
    five,
    six;

    public static TrackCount getCount(int count) {
        switch (count) {
            case 4:
                return four;
            case 5:
                return five;
            case 6:
                return six;
        }
        return null;
    }
}
