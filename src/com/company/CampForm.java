package com.company;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CampForm {
    private JFrame frame;
    private JButton parkTransport;
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
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu campFileMenu;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private JMenuItem saveCamp;
    private JMenuItem loadCamp;
    private Logger logger;

    public CampForm() {
        initialization();
        frame = new JFrame("Стоянки");
        frame.setSize(1200, 575);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkTransport);
        frame.getContentPane().add(groupBoxTake);
        frame.getContentPane().add(drawCamps);
        frame.getContentPane().add(campsGroupBox);
        frame.setJMenuBar(menuBar);
        frame.repaint();
    }

    public void initialization() {
        logger = LogManager.getLogger(CampForm.class);
        PropertyConfigurator.configure("src/com/company/log4j2.properties");
        listTransport = new LinkedList<>();
        campCollection = new CampCollection(890, 525);
        drawCamps = new DrawCamps(campCollection);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderCamps = BorderFactory.createTitledBorder("Стоянки");
        parkTransport = new JButton("Припарковать транспорт");
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
        parkTransport.setBounds(850, 10, 300, 90);
        parkTransport.addActionListener(e -> {
            createTransport();
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

        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        saveFile = new JMenuItem("Сохранить");
        saveFile.addActionListener(e -> {
            saveFile();
        });
        loadFile = new JMenuItem("Загрузить");
        loadFile.addActionListener(e -> {
            loadFile();
        });
        campFileMenu = new JMenu("Стоянка");
        saveCamp = new JMenuItem("Сохранить");
        saveCamp.addActionListener(e -> {
            saveCamp();
        });
        loadCamp = new JMenuItem("Загрузить");
        loadCamp.addActionListener(e -> {
            loadCamp();
        });
        fileMenu.add(saveFile);
        fileMenu.add(loadFile);
        campFileMenu.add(saveCamp);
        campFileMenu.add(loadCamp);
        menuBar.add(fileMenu);
        menuBar.add(campFileMenu);
    }

    private void createTransport() {
        if (listBoxCamps.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            TransportConfigPanel configPanel = new TransportConfigPanel(frame);
            Transport transport = configPanel.getTransport();
            if (transport == null) {
                return;
            }
            if (campCollection.get(listBoxCamps.getSelectedValue()).add(transport)) {
                logger.info("На стоянку " + listBoxCamps.getSelectedValue() + " был добавлен транспорт " + transport);
                frame.repaint();
            }
        } catch (CampOverflowException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
            logger.warn(e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
            logger.fatal(e.getMessage());
        }
    }

    private void placeIntoListTransport() {
        if (listBoxCamps.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Transport transport = campCollection.get(listBoxCamps.getSelectedValue()).delete(Integer.parseInt(placeTransport.getText()));
            if (transport != null) {
                listTransport.add((TrackedVehicle) transport);
                frame.repaint();
                logger.info("Со стоянки " + listBoxCamps.getSelectedValue() + " был изъят транспорт " + transport + " и помещен в коллекцию");
            }
        } catch (CampNotFoundException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Не найдено", JOptionPane.ERROR_MESSAGE);
            logger.warn(e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
            logger.fatal(e.getMessage());
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
            logger.info("Добавлена стоянка " + campsField.getText());
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
                logger.info("Стоянка " + listBoxCamps.getSelectedValue() + " удалена");
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void changeItemList() {
        drawCamps.setSelectedItem(listBoxCamps.getSelectedValue());
        if (listBoxCamps.getSelectedValue() != null) {
            logger.info("Стоянка " + listBoxCamps.getSelectedValue() + " выбрана пользователем");
        }
        frame.repaint();
    }

    private void takeTransportFrame() {
        if (!listTransport.isEmpty()) {
            ExcavatorForm excavatorForm = new ExcavatorForm();
            TrackedVehicle trackedVehicle = listTransport.get(0);
            excavatorForm.setTracked(Objects.requireNonNull(trackedVehicle));
            listTransport.remove(0);
            logger.info("Транспорт " + trackedVehicle + " был изъят из коллекции");
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Коллекция пуста", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFile() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                campCollection.saveData(fileSaveDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл сохранился", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные сохранены в файл " + fileSaveDialog.getSelectedFile().getPath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Ошибка при сохранении файла", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void loadFile() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                campCollection.loadData(fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные были загружены из файла " + fileOpenDialog.getSelectedFile().getPath());
                reloadLevels();
                frame.repaint();
            } catch (CampOverflowException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
                logger.warn(e.getMessage());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Файл не найден", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Некорректные данные", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void saveCamp() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        if (listBoxCamps.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Выберите стоянку", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                campCollection.saveData(fileSaveDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл сохранился", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные сохранены в файл " + fileSaveDialog.getSelectedFile().getPath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Ошибка при сохранении файла", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void loadCamp() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                campCollection.loadData(fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные были загружены из файла " + fileOpenDialog.getSelectedFile().getPath());
                reloadLevels();
                frame.repaint();
            } catch (CampOverflowException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
                logger.warn(e.getMessage());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Файл не найден", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Некорректные данные", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }
}
