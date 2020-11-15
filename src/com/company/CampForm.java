package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.LinkedList;
import java.util.List;

public class CampForm {
    private JFrame frame;
    private JButton parkTracked;
    private JButton parkExcavator;
    private JButton takeTransport;
    private JButton addCamp;
    private JButton deleteCamp;
    private JButton putTransportIntoList;
    private JTextField placeTransport;
    private JTextField countPlaceTransport;
    private DrawCamps drawCamps;
    private JPanel groupBoxTake;
    private JPanel campsGroupBox;
    private JLabel placeText;
    private JLabel placeCountText;
    private JTextField campsField;
    private JList<String> listBoxCamps;
    private Border borderTake;
    private Border borderCamps;
    private DefaultListModel<String> campList;
    private List<TrackedVehicle> listTransport;
    private CampCollection campCollection;

    public CampForm() {
        initialization();
        frame = new JFrame("Стоянки");
        frame.setSize(1200, 564);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkTracked);
        frame.getContentPane().add(parkExcavator);
        frame.getContentPane().add(groupBoxTake);
        frame.getContentPane().add(drawCamps);
        frame.getContentPane().add(campsGroupBox);
        frame.repaint();
    }

    public void initialization() {
        listTransport = new LinkedList<>();
        campCollection = new CampCollection(890, 525);
        drawCamps = new DrawCamps(campCollection);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderCamps = BorderFactory.createTitledBorder("Стоянки");
        parkTracked = new JButton("Припарковать гусеничную машину");
        parkExcavator = new JButton("Припарковать экскаватор");
        putTransportIntoList = new JButton("Поместить в список");
        addCamp = new JButton("Добавить стоянку");
        deleteCamp = new JButton("Удалить стоянку");
        placeTransport = new JTextField();
        countPlaceTransport = new JTextField();
        takeTransport = new JButton("Забрать из списка");
        placeText = new JLabel("Место: ");
        placeCountText = new JLabel("Кол-во: ");
        campsField = new JTextField();
        campList = new DefaultListModel<>();
        listBoxCamps = new JList<>(campList);
        groupBoxTake = new JPanel();
        groupBoxTake.setLayout(null);
        groupBoxTake.add(placeText);
        groupBoxTake.add(placeTransport);
        groupBoxTake.add(takeTransport);
        groupBoxTake.add(putTransportIntoList);
        parkTracked.setBounds(850, 12, 300, 40);
        parkTracked.addActionListener(e -> {
            createTracked();
        });
        parkExcavator.setBounds(850, 60, 300, 40);
        parkExcavator.addActionListener(e -> {
            createExcavator();
        });
        groupBoxTake.setBounds(880, 110, 250, 160);
        placeText.setBounds(90, 20, 60, 30);
        placeTransport.setBounds(135, 20, 30, 30);
        putTransportIntoList.setBounds(40, 70, 170, 30);
        putTransportIntoList.addActionListener(e -> {
            placeIntoListTransport();
        });
        takeTransport.setBounds(40, 110, 170, 30);
        takeTransport.addActionListener(e -> {
            takeTransportFrame();
        });
        groupBoxTake.setBorder(borderTake);
        drawCamps.setBounds(0, 0, 890, 525);
        campsGroupBox = new JPanel();
        campsGroupBox.setLayout(null);
        campsGroupBox.setBounds(880, 270, 250, 240);
        campsGroupBox.setBorder(borderCamps);
        campsField.setBounds(50, 30, 150, 20);
        listBoxCamps.setBounds(50, 90, 150, 100);
        listBoxCamps.addListSelectionListener(e -> {
            changeItemList();
        });
        addCamp.setBounds(50, 55, 150, 30);
        addCamp.addActionListener(e -> {
            addCamp();
        });
        deleteCamp.setBounds(50, 200, 150, 30);
        deleteCamp.addActionListener(e -> {
            deleteCamp();
        });
        campsGroupBox.add(campsField);
        campsGroupBox.add(listBoxCamps);
        campsGroupBox.add(addCamp);
        campsGroupBox.add(deleteCamp);
        placeCountText.setBounds(40, 20, 60, 30);
        countPlaceTransport.setBounds(85, 20, 30, 30);
    }

    private void createTracked() {
        if (listBoxCamps.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            if (colorDialog.getColor() != null) {
                TrackedVehicle transport = new TrackedVehicle(100, 1000, colorDialog.getColor());
                if (campCollection.get(listBoxCamps.getSelectedValue()).add(transport)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createExcavator() {
        if (listBoxCamps.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            if (colorDialog.getColor() != null) {
                JColorChooser otherColorDialog = new JColorChooser();
                JOptionPane.showMessageDialog(frame, otherColorDialog);
                if (otherColorDialog.getColor() != null) {
                    TrackedVehicle transport = new Excavator(100, 1000, colorDialog.getColor(), otherColorDialog.getColor(),
                            true, true, true, 0, 0);
                    if (campCollection.get(listBoxCamps.getSelectedValue()).add(transport)) {
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void placeIntoListTransport() {
        if (listBoxCamps.getSelectedIndex() >= 0) {
            if (!placeTransport.getText().equals("")) {
                try {
                    TrackedVehicle transport = (TrackedVehicle) campCollection.get(listBoxCamps.getSelectedValue()).delete(Integer.parseInt(placeTransport.getText()));
                    if (transport != null) {
                        listTransport.add(transport);
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reloadLevels() {
        int index = listBoxCamps.getSelectedIndex();
        campList.removeAllElements();
        int i = 0;
        for (String name : campCollection.keys()) {
            campList.add(i, name);
            i++;
        }
        int itemsCount = campList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxCamps.setSelectedIndex(0);
        } else if (index >= 0 && index < itemsCount) {
            listBoxCamps.setSelectedIndex(index);
        }
    }

    private void addCamp() {
        if (!campsField.getText().equals("")) {
            campCollection.addCamp(campsField.getText());
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Введите название стоянки", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCamp() {
        if (listBoxCamps.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Удалить стоянку " + listBoxCamps.getSelectedValue() + "?", "Удаление",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                campCollection.deleteCamp(listBoxCamps.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void changeItemList() {
        drawCamps.setSelectedItem(listBoxCamps.getSelectedValue());
        frame.repaint();
    }

    private void takeTransportFrame() {
        if (!listTransport.isEmpty()) {
            ExcavatorForm excavatorForm = new ExcavatorForm();
            excavatorForm.setTracked(listTransport.get(0));
            listTransport.remove(0);
            frame.repaint();
        }
    }
}
