import view.UserView;

import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        UserView userView = new UserView();
        App.run(userView);
    }
    public static void run(UserView userView){
        System.out.println("======WELCOME TO THE MASKUL SHOOP======");
        System.out.print("""
                1. Login
                2. Daftar Akun
                3. Cari Barang
                0. Close
                Masukan Pilihan:\s""");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice){
            case 1 -> {
                int id_user = userView.login();
                if (id_user != -1) {
                    System.out.println("""
                            1. Cari Barang
                            2. Keranjang
                            3. Profil
                            0. Exit
                            Masukan pilihan:\s""");
                    int choice1 = input.nextInt();
                    input.nextLine();
                    switch (choice1){

                    }
                }
            }

            case 2 -> {

            }

            case 3 -> {

            }
            default -> {
                System.out.println("Pilihan tidak valid");
            }
        }
    }
}