package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawPicture extends JPanel {

    private Excavator excavator;

    public void paintComponent(Graphics g) {
        if (excavator != null) {
            excavator.drawPicture(g);
        }
    }

    public void setCar(Excavator ex) {
        this.excavator = ex;
    }

    public Excavator getCar() {
        return excavator;
    }

}
