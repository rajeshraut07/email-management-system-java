package Gmail;

import java.io.*;
import java.util.List;
import java.util.ArrayList;


class DataStore {

    private static final String FILE_NAME = "gmail_data.db";

    // Save users to file
    static void save(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(users);

        } catch (Exception e) {
            System.out.println("Error saving data");
        }
    }

    // Load users from file
    static List<User> load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (List<User>) ois.readObject();

        } catch (Exception e) {
            return new ArrayList<>(); 
        }
    }
}
