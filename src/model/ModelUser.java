package model;

import com.google.gson.reflect.TypeToken;
import modelJSON.ModelJSON;
import node.NodeClass.NodeUser;

import java.util.ArrayList;
import java.util.List;

public class ModelUser implements CRUD<NodeUser> {
    public static ArrayList<NodeUser> userList;
    ModelJSON<NodeUser> modelJSON;

    public ModelUser() {
        modelJSON = new ModelJSON<>("src/database/user.json");
        userList = modelJSON.readFromFile(new TypeToken<ArrayList<NodeUser>>() {
        }.getType());
        if (userList == null){
            userList = new ArrayList<>();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down. Saving data to JSON file...");
            modelJSON.writeToFile(userList);
        }));
    }

    public List<NodeUser> getUserList() {
        return userList;
    }

    public int getLasIdUser(){
        int idx;
        if(userList.isEmpty()) {
            return -1;
        } else {
            idx = userList.size() - 1;
            System.out.println(idx);
            return userList.get(idx).getId_user();
        }
    }
    public void create(NodeUser user) {
        userList.add(user);
    }

    public void update(NodeUser updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId_user() == updatedUser.getId_user()) {
                userList.set(i, updatedUser);
                break;
            }
        }
    }

    public void apdetUser(NodeUser user, int opsi , String data){
        NodeUser apdet = userList.get(user.getId_user());
        switch (opsi){
            case 1->apdet.setNama(data);
            case 2->apdet.setUsername(data);
            case 3->apdet.setPassword(data);
            case 4->apdet.setSaldo(Integer.parseInt(data));
        }
        System.out.println("Berhasil update!");
    }

    public void delete(int userId) {
        userList.removeIf(user -> user.getId_user() == userId);
    }

    public NodeUser getIdUser(int idUser){
        for (NodeUser user : userList) {
            if (user.getId_user() == idUser){
                return user;
            }
        }
        return null;
    }

    public static NodeUser searchUserById(int id){
        for (NodeUser user: userList){
            if (user.getId_user()==id){
                return user;
            }
        }
        return null;
    }

    public NodeUser read(int idUser){
        for (NodeUser user : userList) {
            if (user.getId_user() == idUser){
                return user;
            }
        }
        return null;
    }


}
