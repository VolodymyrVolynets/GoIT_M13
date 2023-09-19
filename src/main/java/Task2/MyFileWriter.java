package Task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {
    public void writeFile(Object obj, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            System.out.println(gson.toJson(obj));
            FileWriter f = new FileWriter(filePath);
            gson.toJson(obj, f);
            f.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
