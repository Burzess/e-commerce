package modelJSON;

import node.NodeJSON.NodeJSONUser;
import node.NodeClass.NodeUser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public void createFileJSON() {
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

    public static JSONArray convertToArrayJSON(List<NodeUser> listUser){
        if (listUser == null){
            return null;
        } else {
            JSONArray arrayUser = new JSONArray();
            for (NodeUser user:listUser) {
                JSONObject objUser = new JSONObject();
                objUser.put(nodeJSONUser.getId_user(), user.getId_user());
                objUser.put(nodeJSONUser.getNama(), user.getNama());
                objUser.put(nodeJSONUser.getUserName(), user.getUserName());
                objUser.put(nodeJSONUser.getPassword(), user.getPassword());
                arrayUser.add(objUser);
            }
            return arrayUser;
        }
    }

    public static void writeFileJSON(List<NodeUser> listUser) {
        JSONArray arrayUser = convertToArrayJSON(listUser);

        try (FileWriter file = new FileWriter(fname)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(arrayUser);
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static List<NodeUser> convertToArrayLIst(JSONArray arrayUser){
        if(arrayUser==null){
            return null;
        } else {
            List<NodeUser> listUser = new ArrayList<>();
            for (Object objUser : arrayUser) {
                JSONObject user = (JSONObject) objUser;
                NodeJSONUser nodeJSONUser = new NodeJSONUser();
                int id_user = Integer.parseInt(user.get(nodeJSONUser.getId_user()).toString());
                String nama = user.get(nodeJSONUser.getNama()).toString();
                String user_name = user.get(nodeJSONUser.getUserName()).toString();
                String password = user.get(nodeJSONUser.getPassword()).toString();
                listUser.add(new NodeUser(id_user, nama,user_name, password));
            }
            return listUser;
        }
    }

    public static List<NodeUser> readFromFile(){
        if (!cekFile()){
            return null;
        }

        List<NodeUser> listUser = null;
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(fname);
            JSONArray arrayUser = (JSONArray) parser.parse(reader);
            listUser = convertToArrayLIst(arrayUser);
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("error: " + e.getMessage());
        } catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }
        return listUser;
    }

}
