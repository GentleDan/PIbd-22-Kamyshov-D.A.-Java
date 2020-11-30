package com.company;

import java.awt.*;

public class TrackCross implements Adding {

    private TrackCount countTrack;

    public TrackCross(int number) {
        setDigit(number);
    }

    @Override
    public void setDigit(int number) {
        this.countTrack = TrackCount.getCount(number);
    }

    @Override
    public void draw(Graphics g, int startPosX, int startPosY, int excavatorWidth, int excavatorHeight) {
        g.setColor(Color.BLUE);
        g.drawLine(startPosX + 40, startPosY + 120, startPosX + 160, startPosY + 120);
        g.drawLine(startPosX + 55, startPosY + 110, startPosX + 55, startPosY + 130);
        g.drawLine(startPosX + 85, startPosY + 110, startPosX + 85, startPosY + 130);
        g.drawLine(startPosX + 115, startPosY + 110, startPosX + 115, startPosY + 130);
        g.drawLine(startPosX + 145, startPosY + 110, startPosX + 145, startPosY + 130);
        if (countTrack == TrackCount.five || countTrack == TrackCount.six) {
            g.setColor(Color.BLACK);
            g.fillOval(startPosX + 10, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
            g.drawLine(startPosX + 15, startPosY + 106, startPosX + 155, startPosY + 106);
            g.drawLine(startPosX + 15, startPosY + 133, startPosX + 155, startPosY + 133);
            g.setColor(Color.BLUE);
            g.drawLine(startPosX + 10, startPosY + 120, startPosX + 160, startPosY + 120);
            g.drawLine(startPosX + 25, startPosY + 110, startPosX + 25, startPosY + 130);
        }
        if (countTrack == TrackCount.six) {
            g.setColor(Color.BLACK);
            g.fillOval(startPosX + 160, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
            g.drawLine(startPosX + 25, startPosY + 106, startPosX + 185, startPosY + 106);
            g.drawLine(startPosX + 25, startPosY + 133, startPosX + 185, startPosY + 133);
            g.setColor(Color.BLUE);
            g.drawLine(startPosX + 10, startPosY + 120, startPosX + 190, startPosY + 120);
            g.drawLine(startPosX + 175, startPosY + 110, startPosX + 175, startPosY + 130);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '.' + countTrack.ordinal();
    }
}
