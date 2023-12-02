package model;

import node.NodeClass.NodeUser;
import modelJSON.ModelJSONUser;

import java.util.ArrayList;
import java.util.List;

public class ModelUser {
    private List<NodeUser> userList;

    public ModelUser() {
        this.userList = ModelJSONUser.readFromFile();
        if (userList == null){
            this.userList = new ArrayList<>();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down. Saving data to JSON file...");
            ModelJSONUser.writeFileJSON(userList);
        }));
    }

    public List<NodeUser> getUserList() {
        return userList;
    }

    public int getLasIdUser(){
        int id = 0;
        for (NodeUser user : userList) {
            id = user.getId_user();
        }
        return id;
    }
    public void addUser(NodeUser user) {
        this.userList.add(user);
    }

    public void updateUser(NodeUser updatedUser) {
        for (int i = 0; i < this.userList.size(); i++) {
            if (this.userList.get(i).getId_user() == updatedUser.getId_user()) {
                this.userList.set(i, updatedUser);
                break;
            }
        }
    }

    public void deleteUser(int userId) {
        this.userList.removeIf(user -> user.getId_user() == userId);
    }
}
