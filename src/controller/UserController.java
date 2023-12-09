package controller;

import model.ModelProduk;
import model.ModelUser;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    ModelUser modelUser;


    public UserController() {
        this.modelUser = new ModelUser();
    }

    public void addUser(String name, String username, String password, KeranjangController k) {
        if (cekUsername(username)) {
            int userId = modelUser.getLasIdUser();
            userId++;
            NodeUser newUser = new NodeUser(userId, name, username, password);
            modelUser.addUser(newUser);
            k.modelKeranjang.addKeranjang(newUser);
        } else {
            System.out.println("Username sudah ada, gunakan username lain.");
        }
    }

    public boolean cekUsername(String username) {
        List<NodeUser> userList = modelUser.getUserList();

        if (userList != null) {
            for (NodeUser user : userList) {
                if (user.getUsername().equals(username)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean updateUser(int userId, String newName, String newUsername, String newPassword, boolean status, int saldo) {

        NodeUser existingUser = findUserById(userId);

        if (existingUser != null) {
            NodeUser updatedUser = new NodeUser(userId, existingUser.getNama(), existingUser.getUsername(), existingUser.getPassword());
            if (!newName.isEmpty()){
                updatedUser.setNama(newName);
            } else if(!newUsername.isEmpty()){
                updatedUser.setUsername(newUsername);
            } else if (!newPassword.isEmpty()) {
                updatedUser.setPassword(newPassword);
            } else if (status){
                updatedUser.setStatus(status);
            } else if (saldo != 0) {
                updatedUser.setSaldo(saldo);
            }

            modelUser.updateUser(updatedUser);
            return true;
        }

        return false;
    }

    public void updateUser(NodeUser user, String update){

        String[] stuff = update.split("-");
        modelUser.apdetUser(user, Integer.parseInt(stuff[0]), stuff[1]);
    }

    public boolean deleteUser(int userId) {

        NodeUser existingUser = findUserById(userId);

        if (existingUser != null) {
            modelUser.deleteUser(userId);
            return true;
        }

        return false;
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

    public static NodeUser findUserById(int userId, ArrayList<NodeUser> userList) {
        if (userList != null) {
            for (NodeUser user : userList) {
                if (user.getId_user() == userId) {
                    return user;
                }
            }
        }

        return null;
    }

    public int authenticateUser(String username, String password){
        for (int i = 0; i < modelUser.getUserList().size(); i++) {
            if (modelUser.getUserList().get(i).getUsername().equals(username)) {
                if (modelUser.getUserList().get(i).getPassword().equals(password)) {
                    return i;
                } else  {
                    System.out.println("username atau password salah");
                    return -1;
                }
            }
        }

        System.out.println("username tidak tersedia");
        return -1;
    }

    public  NodeUser loginFunc(String username, String password){
        for (NodeUser user: modelUser.getUserList()){
            if (user.getUsername().equals(username)){
                if (user.getPassword().equals(password)){
                    return user;
                } else{
                    System.out.println("username atau password salah");
                    return null;
                }
            }
        }

        System.out.println("username tidak terdaftar");
        return null;
    }
}
