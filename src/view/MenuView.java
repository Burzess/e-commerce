package view;

public class MenuView {
    public static void displayFirstMenu() {
        System.out.print("""
                <[Welcome to Aplikasi oren kw]>
                 1. Login
                 2. Daftar Akun
                 0. Close
                Masukan Pilihan:\s""");
    }

    public static void displayMainMenu() {
        System.out.print("""
                            <[-HOME-]>
                             1. Cari Barang
                             2. Tambahkan ke Keranjang
                             3. Profil
                             4. Checkout
                             0. Logout
                            Masukan pilihan:\s""");
    }

    public static void displayMainMenuSeller() {
        System.out.print("""
                            <[-HOME-]>
                             1. Cari Barang
                             2. Tambahkan ke Keranjang
                             3. Profil
                             4. Checkout
                             5. Jual Barang
                             0. Logout
                            Masukan pilihan:\s""");
    }

    public static void displayProfile(){
        System.out.println("""
                            1. Update Nama
                            2. Update Username
                            3. Update Password
                            4. Tambah Saldo
                            5. Update Status
                            0. Back to Home
                            """);
    }

    public static void displayProfilePremium(){
        System.out.println("""
                            1. Update Nama
                            2. Update Username
                            3. Update Password
                            4. Tambah Saldo
                            0. Back to Home
                            """);
    }

    public static void ulang(){
        System.out.println("Kembali ke menu");
    }
}
