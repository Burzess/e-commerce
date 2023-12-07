import view.KeranjangView;
import view.ProdukView;
import view.UserView;

import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);
    static int loggedInUserId = -1;

    public static void main(String[] args) {
        UserView userView = new UserView();
        ProdukView produkView = new ProdukView();
        KeranjangView keranjangView = new KeranjangView(produkView, userView);
        userView.setKeranjangView(keranjangView);

        App.run(userView, keranjangView, produkView);
    }

    public static void displayFirstMenu() {
        System.out.print("""
                1. Login
                2. Daftar Akun
                0. Close
                Masukan Pilihan:\s""");
    }

    public static void displayMainMenu() {
        System.out.println("""
                            ==================HOME=================
                            1. Cari Barang
                            2. Tambahkan ke Keranjang
                            3. Profil
                            4. Checkout
                            0. Logout
                            Masukan pilihan:\s""");
    }

    public static void run(UserView userView, KeranjangView keranjangView, ProdukView produkView) {
        int choice;
        do {
            System.out.println("======WELCOME TO THE MASKUL SHOOP======");

            if (loggedInUserId == -1) {
                displayFirstMenu();
                choice = input.nextInt();
                input.nextLine();
                handleUserChoice(choice, userView, keranjangView, produkView);
            } else {
                displayMainMenu();
                choice = input.nextInt();
                input.nextLine();
                if (choice == 0) {
                    loggedInUserId = -1;
                    System.out.println("Logout berhasil");
                } else {
                    handleMainMenuChoice(choice, produkView, keranjangView, userView, loggedInUserId);
                }
            }

        } while (choice != 0);
    }

    private static void handleUserChoice(int choice, UserView userView, KeranjangView keranjangView, ProdukView produkView) {
        switch (choice) {
            case 1:
                loggedInUserId = userView.login();
                break;
            case 2:
                System.out.println("======DAFTAR AKUN======");
                userView.addUser();
                break;
            default:
                System.out.println("Pilihan tidak valid");
        }
    }

    private static void handleMainMenuChoice(int choice, ProdukView produkView, KeranjangView keranjangView, UserView userView, int userId) {
        switch (choice) {
            case 1 -> produkView.searchPrduk();
            case 2 -> keranjangView.addBarang(userId);
            case 3 -> userView.profil(userId);
            default -> System.out.println("Pilihan tidak valid");
        }
    }
}
