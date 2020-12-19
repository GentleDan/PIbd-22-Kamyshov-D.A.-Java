package com.company;

import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.util.Iterator;

public class Excavator extends TrackedVehicle implements Comparable<TrackedVehicle>, Iterator<String> {

    public Color dopColor;
    public boolean flasher;
    public boolean ladle;
    public boolean stand;
    private Adding adding;
    private int current;

    public float getStartPosX() {
        return startPosX;
    }

    public Color getDopColor() {
        return dopColor;
    }

    public Adding getAdding() {
        return adding;
    }

    public void setAdding(Adding adding) {
        this.adding = adding;
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

    public void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }

    public Excavator(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean flasher, boolean ladle, boolean stand) {
        super(maxSpeed, weight, mainColor, 100, 100);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.flasher = flasher;
        this.ladle = ladle;
        this.stand = stand;
        current = -1;
    }

    public Excavator(String info) {
        super("");
        String[] strs = info.split(separator);
        if (strs.length == 8) {
            maxSpeed = Integer.parseInt(strs[0]);
            weight = Float.parseFloat(strs[1]);
            mainColor = Color.decode(strs[2]);
            dopColor = Color.decode(strs[3]);
            flasher = Boolean.parseBoolean(strs[4]);
            ladle = Boolean.parseBoolean(strs[5]);
            stand = Boolean.parseBoolean(strs[6]);
            if (strs[7].contains("null")) {
                adding = null;
            } else {
                String[] argsAddition = strs[7].split("\\.");
                int digit = Integer.parseInt(argsAddition[1]);
                switch (argsAddition[0]) {
                    case "TrackCircle" -> adding = new TrackCircle(digit);
                    case "TrackCross" -> adding = new TrackCross(digit);
                    case "TrackRect" -> adding = new TrackRect(digit);
                }
            }
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
            g.setColor(dopColor);
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
        if (adding != null) {
            adding.draw(g, startPosX, startPosY, trackedVehicleWidth, trackedVehicleHeight);
        }
    }

    @Override
    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRGB() + separator + dopColor.getRGB() + separator
                + flasher + separator + ladle + separator + stand + separator + adding;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Excavator excavatorObject)) {
            return false;
        }
        return equals(excavatorObject);
    }

    public boolean equals(Excavator other) {
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
        if (dopColor.getRGB() != other.dopColor.getRGB()) {
            return false;
        }
        if (flasher != other.flasher) {
            return false;
        }
        if (ladle != other.ladle) {
            return false;
        }
        if (stand != other.stand) {
            return false;
        }
        if (adding != null && other.adding != null && !(adding.toString().equals(other.adding.toString()))) {
            return false;
        }
        if (adding == null ^ other.adding == null) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(TrackedVehicle trackedVehicle) {
        Excavator excavator = (Excavator) trackedVehicle;
        if (dopColor.getRGB() != excavator.dopColor.getRGB()) {
            return Integer.compare(dopColor.getRGB(), excavator.getDopColor().getRGB());
        }
        if (flasher != excavator.flasher) {
            return Boolean.compare(flasher, excavator.flasher);
        }
        if (ladle != excavator.ladle) {
            return Boolean.compare(ladle, excavator.ladle);
        }
        if (stand != excavator.stand) {
            return Boolean.compare(stand, excavator.stand);
        }
        if (adding == null && excavator.adding != null) {
            return 1;
        }
        if (adding != null && excavator.adding == null) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean hasNext() {
        if(current > 7){
            current = -1;
            return false;
        }
        return true;
    }

    @Override
    public String next() {
        current++;
        switch (current){
            case 0 -> {
                return String.valueOf(maxSpeed);
            }
            case 1 -> {
                return String.valueOf(weight);
            }
            case 2 -> {
                return String.valueOf(mainColor.getRGB());
            }
            case 3 -> {
                return String.valueOf(dopColor.getRGB());
            }
            case 4 -> {
                return String.valueOf(flasher);
            }
            case 5 -> {
                return String.valueOf(ladle);
            }
            case 6 -> {
                return String.valueOf(stand);
            }
            case 7 -> {
                return String.valueOf(adding);
            }
        }
        return null;
    }

/*    private void printInfo(){
        for(String info : this){
            System.out.println(info);
        }
    }*/
}
