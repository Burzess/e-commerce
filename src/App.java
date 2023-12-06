import view.KeranjangView;
import view.ProdukView;
import view.UserView;

import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);
    static boolean loged = false;

    public static void main(String[] args) {
        UserView userView = new UserView();
        ProdukView produkView = new ProdukView();
        KeranjangView keranjangView = new KeranjangView(produkView,userView);
        userView.setKeranjangView(keranjangView);
        App.run(userView, keranjangView, produkView);
    }

    public static void firstMenu(){
        System.out.print("""
                1. Login
                2. Daftar Akun
                0. Close
                Masukan Pilihan:\s""");
    }

    public static void mainMenu(){
        System.out.println("""
                            ============HOME============
                            1. Cari Barang
                            2. Tambahkan ke Keranjang
                            3. Profil
                            4. Checkout
                            0. Exit
                            Masukan pilihan:\s""");
    }


    public static void run(UserView userView, KeranjangView keranjangView, ProdukView produkView) {
        int choice;
        do {
            System.out.println("======WELCOME TO THE MASKUL SHOOP======");
            firstMenu();
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> {
                    int choice1 = 0;
                    int id_user;
                    do {
                        id_user = userView.login();
                        if (id_user != -1) {
                            mainMenu();
                            choice1 = input.nextInt();
                            input.nextLine();
                            switch (choice1) {
                                case 1 -> {
                                    produkView.searchPrduk();
                                }
                                case 2 -> {
                                    keranjangView.addBarang(id_user);
                                }
                                case 3 -> {
                                    userView.profil(id_user);
                                }
                            }
                        }

                    }while (choice1 != 0);
                }

                case 2 -> {
                    System.out.println("======DAFTAR AKUN======");
                    userView.addUser();
                }
                default -> {
                    System.out.println("Pilihan tidak valid");
                }
            }
        } while (choice != 0);
    }
}