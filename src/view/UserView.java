package view;

import controller.UserController;
import node.NodeClass.NodeUser;

import java.util.Scanner;

public class UserView {
    UserController userController;

    KeranjangView keranjangView;

    private Scanner scanner;

    public UserView() {
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
    }
    public UserView(KeranjangView k) {
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
    }

    public void setKeranjangView(KeranjangView keranjangView) {
        this.keranjangView = keranjangView;
    }

    public void addUser() {
        System.out.println("Masukan detail user:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("username: ");
        String username = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();

        userController.addUser(name, username, password,keranjangView.keranjang);

        System.out.println("User added successfully!");
    }

    public void profil(int idUser) {

        int choice;
        do {
            NodeUser detailDataUser = userController.findUserById(idUser);
            System.out.println("\n\n=============PROFIL=============");
            System.out.println(detailDataUser.getNama());
            System.out.println(detailDataUser.getUsername());
            System.out.println(detailDataUser.getPassword());
            System.out.println(detailDataUser.getSaldo());
            System.out.println("================================\n");

            System.out.print("""
                    1. Update Nama
                    2. Update Username
                    3. Update Password
                    4. Update Status
                    5. Tambah Saldo
                    6. Hapus Akun
                    0. Back to Home
                    Masukan Pilihan:\s""");
            choice = scanner.nextInt();
            scanner.nextLine();
            String name = "", username = "", password = "";
            boolean status = false;
            int saldo = 0;

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
                case 4 -> {
                    System.out.println("""
                            ARE YOUT HUMAN?
                            YES or NO:\s""");
                    String input = scanner.nextLine();
                    if (input.equals("yes") || input.equals("YES")) {
                        status = true;
                    }
                }
                case 5 -> {
                    System.out.print("""
                            Masukan Jumlah Saldo yang ingin di tambahkan!
                            Saldo:\s""");
                    saldo = scanner.nextInt();
                    scanner.nextLine();
                }
                case 6 -> {
                    userController.deleteUser(idUser);
                    choice = 0;
                }
            }

            if (userController.updateUser(idUser, name, username, password, status, saldo)) {
                if (!name.isEmpty()) {
                    System.out.println("Nama Berhasil di Perbarui");
                } else if (!username.isEmpty()) {
                    System.out.println("username Berhasil di Perbarui");
                } else if (!password.isEmpty()) {
                    System.out.println("password berhasil di Perbarui");
                } else if (status) {
                    System.out.println("""
                            Status status akun anda berhasil di perbarui!
                            anda bisa melakukan pembelian dan penjualan barang""");
                } else if (saldo != 0) {
                    System.out.println("Saldo berhasil ditambahkan");
                }
            }
        } while (choice != 0);
    }

    public void deleteUser(int idUser) {
        System.out.print("Apakah anda yakin ingin menghapus akun anda?(yes/no): ");
        String confirm = scanner.nextLine();
        if (confirm.equals("yes") || confirm.equals("YES")) {
            if (userController.deleteUser(idUser)) {
                System.out.println("User Delete successfully");
            }
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