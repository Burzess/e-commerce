package modelJSON;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import node.NodeClass.NodeUser;
import node.NodeJSON.NodeJSONUser;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONUser {
    static String fname = "src/database/user.json";
    private static NodeJSONUser nodeJSONUser = new NodeJSONUser();

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

    public static JsonArray convertToArrayJSON(List<NodeUser> listUser){
        return new Gson().toJsonTree(listUser).getAsJsonArray();
    }

    public static void writeFileJSON(List<NodeUser> listUser) {
        JsonArray arrayUser = convertToArrayJSON(listUser);

        try (FileWriter file = new FileWriter(fname)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(arrayUser);
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static List<NodeUser> convertToArrayLIst(JsonArray arrayUser){
        Gson gson = new Gson();
        Type userlisttype = new TypeToken<ArrayList<NodeUser>>() {
        }.getType();
        ArrayList<NodeUser> userArrayList = gson.fromJson(arrayUser, userlisttype);
        return userArrayList;
    }

    public static List<NodeUser> readFromFile(){
        if (!cekFile()){
            createFileJSON();
            return null;
        }
        List<NodeUser> userlist = new ArrayList<>();
        try (Reader reader = new FileReader(fname)) {
            JsonArray jsonUser = new JsonParser().parse(reader).getAsJsonArray();
            userlist = convertToArrayLIst(jsonUser);
        } catch (IOException e) {
            System.out.println("Data Kosong. Error :");
            e.printStackTrace();
        } catch (JsonParseException e) {
            System.out.println("File Kosong");
        }
        return userlist;
    }

}
