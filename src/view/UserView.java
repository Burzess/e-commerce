package view;

import node.NodeClass.NodeUser;
import controller.UserController;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private UserController userController;
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

        System.out.println("Enter updated user details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("usename: ");
        String username = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();

        userController.updateUser(userId,name, username, password);
        System.out.println("User updated successfully!");
    }

    public void deleteUser() {
        System.out.print("Enter the ID of the user to delete: ");
        int userId = scanner.nextInt();
        userController.deleteUser(userId);

        System.out.println("User deleted successfully!");
    }
}
