package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawCamps extends JPanel {

    private final CampCollection campCollection;
    private String selectedItem = null;

    public DrawCamps(CampCollection campCollection) {
        this.campCollection = campCollection;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (selectedItem != null) {
            if (campCollection != null) {
                campCollection.get(selectedItem).draw(g);
            }
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}
