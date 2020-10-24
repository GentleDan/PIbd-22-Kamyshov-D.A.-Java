package com.company;

import java.awt.*;

public class Track {

    private TrackCount count;

    public void setNumber(int number) {
        count = TrackCount.getCount(number);
    }

    public void DrawTrack(Graphics g, int startPosX, int startPosY, int excavatorWidth, int excavatorHeight) {
        g.fillOval(startPosX + 40, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
        g.fillOval(startPosX + 70, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
        g.fillOval(startPosX + 100, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
        g.fillOval(startPosX + 130, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
        g.drawLine(startPosX + 45, startPosY + 106, startPosX + 155, startPosY + 106);
        g.drawLine(startPosX + 45, startPosY + 133, startPosX + 155, startPosY + 133);
        if (count == TrackCount.five || count == TrackCount.six) {
            g.fillOval(startPosX + 10, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
            g.drawLine(startPosX + 15, startPosY + 106, startPosX + 155, startPosY + 106);
            g.drawLine(startPosX + 15, startPosY + 133, startPosX + 155, startPosY + 133);
        }
        if (count == TrackCount.six) {
            g.fillOval(startPosX + 160, startPosY + 105, excavatorWidth - 70, excavatorHeight - 70);
            g.drawLine(startPosX + 25, startPosY + 106, startPosX + 185, startPosY + 106);
            g.drawLine(startPosX + 25, startPosY + 133, startPosX + 185, startPosY + 133);
        }
    }
}
