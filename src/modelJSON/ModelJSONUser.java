package modelJSON;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import node.NodeClass.NodeUser;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONUser {
    static final String fname = "src/database/user.json";

    public static boolean cekFile(){
        boolean cek = false;
        try {
            File file = new File(fname);
            if (file.exists()){
                cek = true;
            }
        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
        }

        return cek;
    }

    public static void createFileJSON() {
        try {
            File file = new File(fname);

            if (file.createNewFile()) {
                System.out.println("File JSON berhasil dibuat: " + fname);
            } else {
                System.out.println("File JSON sudah ada: " + fname);
            }
        } catch (IOException e) {
            System.out.println("Error saat membuat file JSON: " + e.getMessage());
        }
    }

    public static void writeFileJSON(List<NodeUser> listUser) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(listUser);

        try (FileWriter file = new FileWriter(fname)) {
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
    public static List<NodeUser> convertToArrayLIst(JsonArray arrayUser){
        if(arrayUser==null){
            return null;
        }
        Type userListType = new TypeToken<ArrayList<NodeUser>>() {}.getType();
        Gson gson =new Gson();
        return gson.fromJson(arrayUser, userListType);
    }

    public static List<NodeUser> readFromFile() {
        if (!cekFile()) {
            createFileJSON();
            return null;
        }

        List<NodeUser> userList = null;
        try (Reader reader = new FileReader(fname)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);

            if (jsonElement.isJsonArray()) {
                JsonArray arrayUser = jsonElement.getAsJsonArray();
                userList = convertToArrayLIst(arrayUser);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return userList;
    }

}