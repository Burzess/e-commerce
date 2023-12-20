package modelJSON;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ModelJSON<T> {
    private final String fname;

    public ModelJSON(String fname) {
        this.fname = fname;setupFile();
    }

    public boolean checkFile() {
        boolean status = false;
        if (new java.io.File(fname).exists()) {
            status = true;
        }
        return status;
    }

    public void setupFile() {
        try {
            if (!checkFile()) {
                File file = new File(fname);
                if (file.createNewFile()) {
                    try (FileWriter fileWriter = new FileWriter(file, true);
                         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                        bufferedWriter.write("[]");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

    public JsonArray convertListToJsonArray(ArrayList<T> list) {
        return new Gson().toJsonTree(list).getAsJsonArray();
    }

    private ArrayList<T> convertJsonArrayToList(JsonArray jsonArray, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(jsonArray, type);
    }

    public void writeToFile(ArrayList<T> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        try (FileWriter file = new FileWriter(fname)) {
            file.write(json);
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

    public ArrayList<T> readFromFile(Type type) {
        ArrayList<T> list = new ArrayList<>();
        try (Reader reader = new FileReader(fname)) {
            JsonArray jsonArray = new JsonParser().parse(reader).getAsJsonArray();
            list = convertJsonArrayToList(jsonArray, type);
        } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
        return list;
    }
}