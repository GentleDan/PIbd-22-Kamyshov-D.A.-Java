package com.company;

import javax.swing.*;
import java.awt.*;

public class ExcavatorForm {
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private Transport transport;
    private final JFrame frame;
    private final DrawPicture draw;

    public void direction(JButton button) {
        String temp = button.getName();
        if (transport != null) {
            switch (temp) {
                case "Up":
                    transport.moveTransport(Direction.Up);
                    break;
                case "Down":
                    transport.moveTransport(Direction.Down);
                    break;
                case "Left":
                    transport.moveTransport(Direction.Left);
                    break;
                case "Right":
                    transport.moveTransport(Direction.Right);
                    break;
            }
        }
        frame.repaint();
    }

    public void initialization() {
        Icon up = new ImageIcon("Images/up.png");
        Icon down = new ImageIcon("Images/down.png");
        Icon left = new ImageIcon("Images/left.png");
        Icon right = new ImageIcon("Images/right.png");
        upButton = new JButton(up);
        upButton.setName("Up");
        upButton.setBounds(800, 300, 30, 30);
        upButton.addActionListener(e -> direction(upButton));

        downButton = new JButton(down);
        downButton.setName("Down");
        downButton.setBounds(800, 400, 30, 30);
        downButton.addActionListener(e -> direction(downButton));

        rightButton = new JButton(right);
        rightButton.setName("Right");
        rightButton.setBounds(850, 350, 30, 30);
        rightButton.addActionListener(e -> direction(rightButton));

        leftButton = new JButton(left);
        leftButton.setName("Left");
        leftButton.setBounds(750, 350, 30, 30);
        leftButton.addActionListener(e -> direction(leftButton));
    }

    public ExcavatorForm() {
        draw = new DrawPicture();
        frame = new JFrame("Экскаватор");
        frame.setSize(900, 500);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }

    public void setTracked(Transport transport) {
        this.transport = transport;
        draw.setTransport(transport);
        frame.repaint();
    }
}
