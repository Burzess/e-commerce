package view;

import controller.UserController;

import java.util.Scanner;

public class UserView {
    UserController userController;
    private Scanner scanner;

    public UserView() {
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
    }

    public void addUser() {
        System.out.println("Masukan detail user:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("username: ");
        String username = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();

        userController.addUser(name, username, password);

        System.out.println("User added successfully!");
    }

    public void updateUser() {
        System.out.print("Enter the ID of the user to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("""
                1. Update Nama
                2. Update Username
                3. Update Password
                Masukan Pilihan:\s""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String name = "", username = "", password = "";

        switch (choice) {
            case 1 -> {
                System.out.print("Name: ");
                name = scanner.nextLine();
            }
            case 2 -> {
                System.out.print("usename: ");
                username = scanner.nextLine();
            }
            case 3 -> {
                System.out.print("password: ");
                password = scanner.nextLine();
            }
        }

        if (userController.updateUser(userId,name, username, password)){
            System.out.println("User updated successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    public void deleteUser() {
        System.out.print("Enter the ID of the user to delete: ");
        int userId = scanner.nextInt();
        if (userController.deleteUser(userId)){
            System.out.println("User Delete successfully");
        } else {
            System.out.println("User not found");
        }
    }

    public int login(){
        System.out.print("masukan username: ");
        String username = scanner.nextLine();
        System.out.print("masukan password: ");
        String password = scanner.nextLine();
        return userController.authenticateUser(username, password);
    }
}
