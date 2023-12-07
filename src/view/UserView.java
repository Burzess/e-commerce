package view;

import controller.ProdukController;
import controller.UserController;
import node.NodeClass.NodeUser;

import java.util.Scanner;

public class UserView {
    public final UserController userController;
    private final Scanner scanner;
    ProdukController produkController;
    private KeranjangView keranjangView;
    ProdukView produkView;

    public UserView() {
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
        this.produkView = new ProdukView();
        this.produkController = new ProdukController();
    }

    public UserView(KeranjangView keranjangView) {
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
        this.keranjangView = keranjangView;
    }

    public void setKeranjangView(KeranjangView keranjangView) {
        this.keranjangView = keranjangView;
    }

    public void addUser() {
        System.out.println("Masukkan detail user:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        userController.addUser(name, username, password, keranjangView.keranjang);

        System.out.println("User added successfully!");
    }

    public void profil(int idUser) {
        int choice = 0;
        do {
            NodeUser detailDataUser = userController.findUserById(idUser);
            if (detailDataUser != null) {
                displayUserProfile(detailDataUser);

                if (detailDataUser.isStatus()) {
                    System.out.print("""
                            1. Update Nama
                            2. Update Username
                            3. Update Password
                            4. Update Status
                            5. Tambah Saldo
                            6. Tambah Barang Penjualan
                            7. Hapus Akun
                            0. Back to Home
                            Masukkan Pilihan:\s""");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    handleProfileChoice(detailDataUser, choice);
                } else {
                    System.out.print("""
                            1. Update Nama
                            2. Update Username
                            3. Update Password
                            4. Update Status
                            5. Tambah Saldo
                            6. Hapus Akun
                            0. Back to Home
                            Masukkan Pilihan:\s""");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    handleProfileChoice(idUser, choice);
                }
            } else{
                choice = 0;
            }

        } while (choice != 0);
    }

    private void displayUserProfile(NodeUser user) {
        System.out.println("\n\n=============PROFIL=============");
        System.out.println(user.getNama());
        System.out.println("username: " + user.getUsername());
        System.out.println("Saldo: " + user.getSaldo());
        if (user.isStatus()){
            System.out.println("status: Penjual");
        } else {
            System.out.println("status: Bukan Penjual");
        }
        System.out.println("================================\n");
    }

    private void handleProfileChoice(NodeUser nodeUser, int choice) {
        String name = "", username = "", password = "";
        boolean status = true;
        int saldo = 0, idUser = nodeUser.getId_user();

        switch (choice) {
            case 1 -> name = getUserInput("Name");
            case 2 -> username = getUserInput("Username");
            case 3 -> password = getUserInput("Password");
            case 4 -> status = confirmHuman();
            case 5 -> saldo = getSaldoInput();
            case 6 -> {
                produkView.addProdukView(nodeUser);
                return;
            }
            case 7 -> {
                userController.deleteUser(idUser);
                produkController.deletProduk(idUser);
            }
            case 0 -> {return;}

        }

        if (userController.updateUser(idUser, name, username, password, status, saldo)) {
            displaySuccessMessage(name, username, password, status, saldo);
        }
    }

    private void handleProfileChoice(int idUser, int choice) {
        String name = "", username = "", password = "";
        boolean status = false;
        int saldo = 0;

        switch (choice) {
            case 1 -> name = getUserInput("Name");
            case 2 -> username = getUserInput("Username");
            case 3 -> password = getUserInput("Password");
            case 4 -> status = confirmHuman();
            case 5 -> saldo = getSaldoInput();
            case 6 -> userController.deleteUser(idUser);
            case 0 -> {return;}
        }

        if (userController.updateUser(idUser, name, username, password, status, saldo)) {
            displaySuccessMessage(name, username, password, status, saldo);
        }
    }

    private String getUserInput(String fieldName) {
        System.out.print(fieldName + ": ");
        return scanner.nextLine();
    }

    private boolean confirmHuman() {
        System.out.println("""
                ARE YOU HUMAN?
                YES or NO:\s""");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("yes");
    }

    private int getSaldoInput() {
        System.out.print("""
                Masukkan Jumlah Saldo yang ingin ditambahkan!
                Saldo:\s""");
        return scanner.nextInt();
    }

    private void displaySuccessMessage(String name, String username, String password, boolean status, int saldo) {
        if (!name.isEmpty()) {
            System.out.println("\nNama Berhasil di Perbarui");
        } else if (!username.isEmpty()) {
            System.out.println("\nUsername Berhasil di Perbarui");
        } else if (!password.isEmpty()) {
            System.out.println("\nPassword berhasil di Perbarui");
        } else if (status) {
            System.out.println("""
                    \nStatus akun anda berhasil di perbarui!
                    Selamat Anda bisa melakukan pembelian dan penjualan barang""");
        } else if (saldo != 0) {
            System.out.println("\nSaldo berhasil ditambahkan");
        }
    }

    public void deleteUser(int idUser) {
        System.out.print("Apakah anda yakin ingin menghapus akun anda? (yes/no): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            if (userController.deleteUser(idUser)) {
                System.out.println("User Delete successfully");
            }
        }
    }

    public int login() {
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        return userController.authenticateUser(username, password);
    }
}
