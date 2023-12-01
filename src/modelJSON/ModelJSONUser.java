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
import java.util.List;

public class ModelJSONUser {
    String fname = "src/database/user.json";
    private NodeJSONUser nodeJSONUser = new NodeJSONUser();

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
            for (NodeUser user:listUser) {
                JSONObject objUser = new JSONObject();
                objUser.put(nodeJSONUser.getId_user(), user.getId_user());
                objUser.put(nodeJSONUser.getNama(), user.getNama());
                objUser.put(nodeJSONUser.getUserName(), user.getUserName());
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
                String user_name = user.get(nodeJSONUser.getUserName()).toString();
                String email = user.get(nodeJSONUser.getEmail()).toString();
                listUser.add(new NodeUser(id_user, nama,user_name, email));
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

    public void appendToFileJSON(List<NodeUser> listUser) {
        List<NodeUser> userList = readFromFile();

        if (userList == null) {
            userList = new ArrayList<>();
        }
        System.out.println(userList.size());

        int i = 0;
        for (NodeUser user : userList) {
            if (user.getId_user() == listUser.get(i).getId_user()) {
                System.out.println("id telah tersedia");
                return;
            }
        }

        userList.addAll(listUser);
        writeFileJSON(userList);

    }

    public boolean deleteByIdJSONUser(int userId) {
        List<NodeUser> userList = readFromFile();

        if(userList != null){
            if (userList.removeIf(user -> user.getId_user() == userId)){
                writeFileJSON(userList);
                System.out.println("delete success");
                return true;
            } else {
                System.out.println("id tidak ditemukan");
                return false;
            }
        }

        System.out.println("data kosong");
        return false;
    }

    public boolean updateJSONUser(int userId, String email){
        List<NodeUser> userList = readFromFile();

        if (userList != null){
            for (NodeUser user : userList) {
                if (user.getId_user() == userId){
                    user.setEmail(email);
                    writeFileJSON(userList);
                    System.out.println("Data berhasil di perbarui");
                    return true;
                }
            }
        }

        System.out.println("Data kosong");
        return false;
    }


}
