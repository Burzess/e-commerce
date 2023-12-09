package view;

import controller.KeranjangController;
import controller.UserController;
import node.NodeClass.NodeUser;

import java.util.Scanner;

public class MainView {
    static Scanner input = new Scanner(System.in);
    static UserController userController = new UserController();
    static KeranjangController keranjangController = new KeranjangController();

    public static NodeUser login(){
        System.out.print("Masukkan Username: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Password: ");
        String pass = input.nextLine();
        NodeUser logged = userController.loginFunc(nama, pass);
        if (logged!=null){
            return logged;
        }

        return null;
    }

    public static void updateUser(NodeUser user){
        System.out.print("""
                            Gunakan command [opsi-value] (untuk opsi 1-4)
                            contoh: 1-Fulan; 4-10000
                            Masukkan Command:\s""");
        String command = input.nextLine();
        userController.updateUser(user, command);

    }

    public static void viewKeranjang(NodeUser user){
        keranjangController.vieww(user);
    }
}
