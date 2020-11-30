package com.company;

import java.awt.*;

public class TrackRect implements Adding {

    private TrackCount countTrack;

    public TrackRect(int number) {
        setDigit(number);
    }

    @Override
    public void setDigit(int number) {
        this.countTrack = TrackCount.getCount(number);
    }

    @Override
    public void draw(Graphics g, int startPosX, int startPosY, int excavatorWidth, int excavatorHeight) {
        g.setColor(Color.CYAN);
        g.drawRect(startPosX + 45, startPosY + 110, excavatorWidth - 80, excavatorHeight - 80);
        g.drawRect(startPosX + 75, startPosY + 110, excavatorWidth - 80, excavatorHeight - 80);
        g.drawRect(startPosX + 105, startPosY + 110, excavatorWidth - 80, excavatorHeight - 80);
        g.drawRect(startPosX + 135, startPosY + 110, excavatorWidth - 80, excavatorHeight - 80);
        if (countTrack == TrackCount.five || countTrack == TrackCount.six) {
            g.setColor(Color.BLACK);
            g.fillOval(startPosX + 10, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
            g.drawLine(startPosX + 15, startPosY + 106, startPosX + 155, startPosY + 106);
            g.drawLine(startPosX + 15, startPosY + 133, startPosX + 155, startPosY + 133);
            g.setColor(Color.CYAN);
            g.drawRect(startPosX + 15, startPosY + 110, excavatorWidth - 80, excavatorHeight - 80);
        }
        if (countTrack == TrackCount.six) {
            g.setColor(Color.BLACK);
            g.fillOval(startPosX + 160, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
            g.drawLine(startPosX + 25, startPosY + 106, startPosX + 185, startPosY + 106);
            g.drawLine(startPosX + 25, startPosY + 133, startPosX + 185, startPosY + 133);
            g.setColor(Color.CYAN);
            g.drawRect(startPosX + 165, startPosY + 110, excavatorWidth - 80, excavatorHeight - 80);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '.' + countTrack.ordinal();
    }
}
