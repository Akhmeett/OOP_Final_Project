package database;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataStorage {
    private static DataStorage instance;
    private Map<String, Object> data;
    private static final String FILE_PATH = "data.ser";

    private DataStorage() {
        data = new HashMap<>();
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public void save(String key, Object value) {
        data.put(key, value);
        serialize();
    }

    public Object load(String key) {
        deserialize();
        return data.get(key);
    }

    private void serialize() {
        try (ObjectOutputStream oos =
             new ObjectOutputStream(
             new FileOutputStream(FILE_PATH))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void deserialize() {
        File f = new File(FILE_PATH);
        if (!f.exists()) return;
        try (ObjectInputStream ois =
             new ObjectInputStream(
             new FileInputStream(FILE_PATH))) {
            data = (Map<String, Object>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }

    public Map<String, Object> getData() { return data; }
}
