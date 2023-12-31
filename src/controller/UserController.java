package controller;

import model.ModelUser;
import node.NodeClass.NodeUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserController {
    ModelUser modelUser;


    public UserController() {
        this.modelUser = new ModelUser();
    }

    public void addUser(String name, String username, String password, KeranjangController k) {
        if (cekUsername(username)) {
            int userId = modelUser.getLasIdUser()+1;
            NodeUser newUser = new NodeUser(userId, name, username, password);
            modelUser.create(newUser);
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

    public boolean updateUser(int userId, String newName, String newUsername, String newPassword, boolean newStatus, int newSaldo) {
        NodeUser existingUser = findUserById(userId);

        if (existingUser != null) {
            NodeUser updatedUser = new NodeUser(userId, existingUser.getNama(), existingUser.getUsername(), existingUser.getPassword(), existingUser.getSaldo(), existingUser.isStatus());

            if (!newName.isEmpty()) {
                updatedUser.setNama(newName);
            } else if (!newUsername.isEmpty()) {
                updatedUser.setUsername(newUsername);
            } else if (!newPassword.isEmpty()) {
                updatedUser.setPassword(newPassword);
            }

            if (newSaldo != 0) {
                int saldo = newSaldo;
                updatedUser.setSaldo(existingUser.getSaldo() + saldo);
            }

            if (newStatus) {
                updatedUser.setStatus(newStatus);
            }
            modelUser.update(updatedUser);
            return true;
        }

        return false;
    }

    public boolean updateUser(NodeUser user, String update){
        if (Objects.equals(update, "5")){
            user.setStatus(true);
            return true;
        }
        try {
            String[] stuff = update.split("-");
            modelUser.apdetUser(user, Integer.parseInt(stuff[0]), stuff[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Command Salah :D ");
        }
        return true;
    }

    public boolean deleteUser(int userId) {

        NodeUser existingUser = findUserById(userId);

        if (existingUser != null) {
            modelUser.delete(userId);
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
