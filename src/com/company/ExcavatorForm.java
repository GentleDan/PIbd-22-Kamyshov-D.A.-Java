package com.company;

import javax.swing.*;
import java.awt.*;

public class ExcavatorForm {

    private JButton createTrackedButton;
    private JButton createExcavatorButton;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JComboBox<String> choiceCountTrackButton;
    private JComboBox<String> choiceAddingButton;
    private Transport transport;
    private JFrame frame;
    private DrawPicture draw;

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

        createTrackedButton = new JButton("Create Tracked");
        createTrackedButton.setBounds(0, 0, 130, 30);
        createTrackedButton.addActionListener(e -> {
            transport = new TrackedVehicle(100 + ((int) (Math.random() * 300)), 1000 + ((int) (Math.random() * 2000)), Color.YELLOW);
            transport.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            draw.setTransport(transport);
            frame.repaint();
        });

        createExcavatorButton = new JButton("Create Excavator");
        createExcavatorButton.setBounds(150, 0, 140, 30);
        createExcavatorButton.addActionListener(e -> {
            transport = new Excavator(100 + ((int) (Math.random() * 300)), 1000 + ((int) (Math.random() * 2000)), Color.YELLOW, Color.YELLOW,
                    true, true, false, choiceAddingButton.getSelectedIndex(), choiceCountTrackButton.getSelectedIndex());
            transport.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            draw.setTransport(transport);
            frame.repaint();
        });

        choiceAddingButton = new JComboBox<>(new String[]{"Circle", "Cross", "Rectangle"});
        choiceAddingButton.setBounds(0, 40, 130, 30);

        choiceCountTrackButton = new JComboBox<>(new String[]{"4 track", "5 track", "6 track"});
        choiceCountTrackButton.setBounds(150, 40, 130, 30);
    }

    public ExcavatorForm() {
        draw = new DrawPicture();
        frame = new JFrame("Экскаватор");
        frame.setSize(900, 500);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(createTrackedButton);
        frame.getContentPane().add(createExcavatorButton);
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(choiceAddingButton);
        frame.getContentPane().add(choiceCountTrackButton);
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
