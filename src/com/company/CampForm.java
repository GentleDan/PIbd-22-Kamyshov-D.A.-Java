package com.company;

import javax.swing.*;
import javax.swing.border.Border;

public class CampForm {
    private JFrame frame;
    private JButton parkTracked;
    private JButton parkExcavator;
    private JButton takeTransport;
    private JButton compareEquality;
    private JButton compareInequality;
    private JTextField placeTransport;
    private JTextField countPlaceTransport;
    private Camp<TrackedVehicle, Adding> camp;
    private DrawCamp drawCamp;
    private JPanel groupBox;
    private JPanel equateGroupBox;
    private JLabel placeText;
    private JLabel placeCountText;
    private Border borderTake;
    private Border borderCompare;

    public CampForm() {
        initialization();
        frame = new JFrame("Стоянка");
        frame.setSize(1200, 564);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkTracked);
        frame.getContentPane().add(parkExcavator);
        frame.getContentPane().add(groupBox);
        frame.getContentPane().add(equateGroupBox);
        frame.getContentPane().add(drawCamp);
        frame.repaint();
    }

    public void initialization() {
        camp = new Camp<>(890, 525);
        drawCamp = new DrawCamp(camp);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderCompare = BorderFactory.createTitledBorder("Сравнить");
        parkTracked = new JButton("Припарковать гусеничную машину");
        parkExcavator = new JButton("Припарковать экскаватор");
        compareEquality = new JButton("==");
        compareInequality = new JButton("!=");
        placeTransport = new JTextField();
        countPlaceTransport = new JTextField();
        takeTransport = new JButton("Забрать");
        placeText = new JLabel("Место: ");
        placeCountText = new JLabel("Кол-во: ");
        groupBox = new JPanel();
        groupBox.setLayout(null);
        groupBox.add(placeText);
        groupBox.add(placeTransport);
        groupBox.add(takeTransport);
        parkTracked.setBounds(850, 12, 300, 40);
        parkTracked.addActionListener(e -> createTracked());
        parkExcavator.setBounds(850, 60, 300, 40);
        parkExcavator.addActionListener(e -> createExcavator());
        groupBox.setBounds(930, 150, 150, 100);
        placeText.setBounds(40, 20, 60, 30);
        placeTransport.setBounds(85, 20, 30, 30);
        takeTransport.setBounds(40, 60, 90, 30);
        takeTransport.addActionListener(e -> takeTracked());
        groupBox.setBorder(borderTake);
        drawCamp.setBounds(0, 0, 890, 525);
        equateGroupBox = new JPanel();
        equateGroupBox.setLayout(null);
        equateGroupBox.setBorder(borderCompare);
        equateGroupBox.add(compareEquality);
        equateGroupBox.add(compareInequality);
        equateGroupBox.add(countPlaceTransport);
        equateGroupBox.add(placeCountText);
        equateGroupBox.setBounds(930, 300, 150, 150);
        placeCountText.setBounds(40, 20, 60, 30);
        countPlaceTransport.setBounds(85, 20, 30, 30);
        compareInequality.setBounds(40, 60, 90, 30);
        compareInequality.addActionListener(e -> compare(compareInequality.getText()));
        compareEquality.setBounds(40, 100, 90, 30);
        compareEquality.addActionListener(e -> compare(compareEquality.getText()));
    }

    private void createTracked() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            TrackedVehicle transport = new TrackedVehicle(100, 1000, colorDialog.getColor());
            if (camp.add(transport)) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
            }
        }
    }

    private void createExcavator() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            JColorChooser otherColorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, otherColorDialog);
            if (otherColorDialog.getColor() != null) {
                TrackedVehicle transport = new Excavator(100, 1000, colorDialog.getColor(), otherColorDialog.getColor(),
                        true, true, true, 0, 0);
                if (camp.add(transport)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
                }
            }
        }
    }

    private void takeTracked() {
        if (!placeTransport.getText().equals("")) {
            try {
                TrackedVehicle transport = camp.delete(Integer.parseInt(placeTransport.getText()));
                if (transport != null) {
                    ExcavatorForm excavatorForm = new ExcavatorForm();
                    excavatorForm.setTracked(transport);
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Транспорта не существует");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Транспорта не существует");
            }
        }
    }

    private void compare(String comparison) {
        int number = Integer.parseInt(countPlaceTransport.getText());
        if (!countPlaceTransport.getText().equals("")) {
            if (comparison.equals("==")) {
                if (camp.equal(number)) {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " равно количеству занятых мест на стоянке");
                } else {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " не равно количеству занятых мест на стоянке");
                }
            }
            if (comparison.equals("!=")) {
                if (camp.inequal(number)) {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " не равно количеству занятых мест на стоянке");
                } else {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " равно количеству занятых мест на стоянке");
                }
            }
        }
    }
}
