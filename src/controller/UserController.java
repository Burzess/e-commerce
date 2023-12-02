package controller;

import model.ModelUser;
import node.NodeClass.NodeUser;
import view.UserView;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private ModelUser modelUser;

    public UserController() {
        this.modelUser = new ModelUser();
    }

    public void addUser(String name, String username, String password) {
        if (cekUsername(username)) {
            int userId = modelUser.getLasIdUser();
            userId++;
            NodeUser newUser = new NodeUser(userId, name, username, password);
            modelUser.addUser(newUser);
        } else {
            System.out.println("Username sudah ada, gunakan username lain.");
        }
    }

    public boolean cekUsername(String username) {
        List<NodeUser> userList = modelUser.getUserList();

        if (userList != null) {
            for (NodeUser user : userList) {
                if (user.getUserName().equals(username)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void updateUser(int userId, String newName, String newUsername, String newPassword) {

        NodeUser existingUser = findUserById(userId);

        if (existingUser != null) {
            NodeUser updatedUser = new NodeUser(userId, newName, newUsername, newPassword);
            modelUser.updateUser(updatedUser);
        } else {
            System.out.println("User not found!");
        }
    }

    public void deleteUser(int userId) {

        NodeUser existingUser = findUserById(userId);

        if (existingUser != null) {
            modelUser.deleteUser(userId);
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    public NodeUser findUserById(int userId) {
        List<NodeUser> userList = modelUser.getUserList();

        if (userList != null) {
            for (NodeUser user : userList) {
                if (user.getId_user() == userId) {
                    return user;
                }
            }
        }

        return null;
    }
}
