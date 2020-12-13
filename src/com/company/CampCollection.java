package com.company;

import java.io.*;
import java.security.KeyException;
import java.util.*;

public class CampCollection {

    private final Map<String, Camp<Transport, Adding>> campStages;

    private final int pictureWidth;

    private final int pictureHeight;

    private final String separator = ":";

    public CampCollection(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        campStages = new HashMap<>();
    }

    public Set<String> keys() {
        return campStages.keySet();
    }

    public void addCamp(String name) {
        if (campStages.containsKey(name)) {
            return;
        }
        campStages.put(name, new Camp<>(pictureWidth, pictureHeight));
    }

    public void deleteCamp(String name) {
        campStages.remove(name);
    }

    public Camp<Transport, Adding> get(String name) {
        if (campStages.containsKey(name)) {
            return campStages.get(name);
        }
        return null;
    }

    public void saveData(String filename) throws IOException {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            fileWriter.write("CampCollection\n");
            for (Map.Entry<String, Camp<Transport, Adding>> level : campStages.entrySet()) {
                fileWriter.write("Camp" + separator + level.getKey() + '\n');
                Transport vehicle;
                for (int i = 0; (vehicle = level.getValue().get(i)) != null; i++) {
                    if (vehicle.getClass().getSimpleName().equals("Truck")) {
                        fileWriter.write("TrackedVehicle" + separator);
                    } else if (vehicle.getClass().getSimpleName().equals("Tanker")) {
                        fileWriter.write("Excavator" + separator);
                    }
                    fileWriter.write(vehicle.toString() + '\n');
                }
            }
        }
    }

    public void loadData(String filename) throws IOException, CampOverflowException {
        if (!(new File(filename).exists())) {
            throw new FileNotFoundException("Файл " + filename + " не найден");
        }
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            if (scanner.nextLine().contains("CampCollection")) {
                campStages.clear();
            } else {
                throw new IllegalArgumentException("Неверный формат файла");
            }
            Transport vehicle = null;
            String key = "";
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("Camp")) {
                    key = line.split(separator)[1];
                    campStages.put(key, new Camp<>(pictureWidth, pictureHeight));
                } else if (line.contains(separator)) {
                    if (line.contains("TrackedVehicle")) {
                        vehicle = new TrackedVehicle(line.split(separator)[1]);
                    } else if (line.contains("Excavator")) {
                        vehicle = new Excavator(line.split(separator)[1]);
                    }
                    if (!(campStages.get(key).add(vehicle))) {
                        throw new CampOverflowException();
                    }
                }
            }
        }
    }

    public void saveCamp(String filename, String key) throws IOException, KeyException {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            if (campStages.containsKey(key)) {
                fileWriter.write("Camp" + separator + key + '\n');
            } else {
                throw new KeyException();
            }
            Transport vehicle;
            for (int i = 0; (vehicle = campStages.get(key).get(i)) != null; i++) {
                if (vehicle.getClass().getSimpleName().equals("TrackedVehicle")) {
                    fileWriter.write("TrackedVehicle" + separator);
                } else if (vehicle.getClass().getSimpleName().equals("Excavator")) {
                    fileWriter.write("Excavator" + separator);
                }
                fileWriter.write(vehicle.toString() + '\n');
            }
        }
    }

    public void loadCamp(String filename) throws IOException, CampOverflowException {
        if (!(new File(filename).exists())) {
            throw new FileNotFoundException("Файл " + filename + " не найден");
        }
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String key;
            String line;
            line = scanner.nextLine();
            if (line.contains("Camp:")) {
                key = line.split(separator)[1];
                if (campStages.containsKey(key)) {
                    campStages.get(key).clear();
                } else {
                    campStages.put(key, new Camp<>(pictureWidth, pictureHeight));
                }
            } else {
                throw new IllegalArgumentException("Неверный формат файла");
            }
            Transport vehicle = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(separator)) {
                    if (line.contains("TrackedVehicle")) {
                        vehicle = new TrackedVehicle(line.split(separator)[1]);
                    } else if (line.contains("Excavator")) {
                        vehicle = new Excavator(line.split(separator)[1]);
                    }
                    if (!(campStages.get(key).add(vehicle))) {
                        throw new CampOverflowException();
                    }
                }
            }
        }
    }

    public Transport get(String name, int index) {
        if (campStages.containsKey(name)) {
            return campStages.get(name).get(index);
        }
        return null;
    }
}
