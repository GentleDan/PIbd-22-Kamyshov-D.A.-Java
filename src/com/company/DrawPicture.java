package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawPicture extends JPanel {

    private Transport transport;

    public void paintComponent(Graphics g) {
        if (transport != null) {
            transport.draw(g);
        }
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Transport getCar() {
        return transport;
    }
}
