package modelJSON;

import NodeJSON.NodeJSONUser;
import NodeUser.NodeUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONUser {
    String fname = "src/database/user.json";

    public boolean cekFile(){
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

    public JSONArray convertToArrayJSON(List<NodeUser> listUser){
        if (listUser == null){
            return null;
        } else {
            JSONArray arrayUser = new JSONArray();
            NodeJSONUser nodeJSONUser = new NodeJSONUser();
            for (NodeUser user:listUser) {
                JSONObject objUser = new JSONObject();
                objUser.put(nodeJSONUser.getId_user(), user.getId_user());
                objUser.put(nodeJSONUser.getNama(), user.getNama());
                objUser.put(nodeJSONUser.getEmail(), user.getEmail());
                arrayUser.add(objUser);
            }
            return arrayUser;
        }
    }

    public void writeFileJSON(List<NodeUser> listUser) {
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

    public List<NodeUser> convertToArrayLIst(JSONArray arrayUser){
        if(arrayUser==null){
            return null;
        } else {
            List<NodeUser> listUser = new ArrayList<>();
            for (Object objUser : arrayUser) {
                JSONObject user = (JSONObject) objUser;
                NodeJSONUser nodeJSONUser = new NodeJSONUser();
                int id_user = Integer.parseInt(user.get(nodeJSONUser.getId_user()).toString());
                String nama = user.get(nodeJSONUser.getNama()).toString();
                String email = user.get(nodeJSONUser.getEmail()).toString();
                listUser.add(new NodeUser(id_user, nama, email));
            }
            return listUser;
        }
    }

    public List<NodeUser> readFromFile(){
        if (!cekFile()){
            return null;
        }

        List<NodeUser> listUser = null;
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(fname);
            org.json.simple.JSONArray arrayUser = (org.json.simple.JSONArray) parser.parse(reader);
            listUser = convertToArrayLIst(arrayUser);
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("error: " + e.getMessage());
        } catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }
        return listUser;
    }

    public void appendToFileJSON(List<NodeUser> listUser) {
        List<NodeUser> existingList = readFromFile();

        if (existingList == null) {
            existingList = new ArrayList<>();
        }

        existingList.addAll(listUser);

        writeFileJSON(existingList);
    }

    public void deleteById(int id) {

        List<NodeUser> existingList = readFromFile();

        if (existingList != null) {
            existingList.removeIf(user -> user.getId_user() == id);

            writeFileJSON(existingList);
        }
    }

}
