package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public boolean saveData(String filename) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            fileWriter.write("CampCollection\n");
            for (Map.Entry<String, Camp<Transport, Adding>> level : campStages.entrySet()) {
                fileWriter.write("Camp" + separator + level.getKey() + '\n');
                Transport transport;
                for (int i = 0; (transport = level.getValue().get(i)) != null; i++) {
                    if (transport.getClass().getSimpleName().equals("TrackedVehicle")) {
                        fileWriter.write("TrackedVehicle" + separator);
                    } else if (transport.getClass().getSimpleName().equals("Excavator")) {
                        fileWriter.write("Excavator" + separator);
                    }
                    fileWriter.write(transport.toString() + '\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadData(String filename) {
        if (!(new File(filename).exists())) {
            return false;
        }

        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            if (scanner.nextLine().contains("CampCollection")) {
                campStages.clear();
            } else {
                return false;
            }

            Transport transport = null;
            String key = "";
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("Camp")) {
                    key = line.split(separator)[1];
                    campStages.put(key, new Camp<>(pictureWidth, pictureHeight));
                } else if (line.contains(separator)) {
                    if (line.contains("TrackedVehicle")) {
                        transport = new TrackedVehicle(line.split(separator)[1]);
                    } else if (line.contains("Excavator")) {
                        transport = new Excavator(line.split(separator)[1]);
                    }
                    if (!(campStages.get(key).add(transport))) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean saveCamp(String filename, String key) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            if (campStages.containsKey(key))
                fileWriter.write("Camp" + separator + key + '\n');
            Transport transport;
            for (int i = 0; (transport = campStages.get(key).get(i)) != null; i++) {
                if (transport.getClass().getSimpleName().equals("TrackedVehicle")) {
                    fileWriter.write("TrackedVehicle" + separator);
                } else if (transport.getClass().getSimpleName().equals("Excavator")) {
                    fileWriter.write("Excavator" + separator);
                }
                fileWriter.write(transport.toString() + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadCamp(String filename) {
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
                return false;
            }
            Transport transport = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(separator)) {
                    if (line.contains("TrackedVehicle")) {
                        transport = new TrackedVehicle(line.split(separator)[1]);
                    } else if (line.contains("Excavator")) {
                        transport = new Excavator(line.split(separator)[1]);
                    }
                    if (!(campStages.get(key).add(transport))) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Transport get(String name, int index) {
        if (campStages.containsKey(name)) {
            return campStages.get(name).get(index);
        }
        return null;
    }
}
