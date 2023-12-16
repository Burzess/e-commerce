package view;

import controller.KeranjangController;
import controller.ProdukController;
import controller.TransaksiController;
import controller.UserController;
import model.ModelProduk;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

import java.util.List;
import java.util.Scanner;

public class MainView {
    static Scanner input = new Scanner(System.in);
    static UserController userController = new UserController();
    static KeranjangController keranjangController = new KeranjangController();
    static ProdukView produkView = new ProdukView();
    static ProdukController produkController = new ProdukController();
    static UserView userView = new UserView();
    static KeranjangView keranjangView = new KeranjangView(produkView,userView);
    static UserView userView2 = new UserView(keranjangView);
    static TransaksiController transaksiController = new TransaksiController();

    public static NodeUser login(){
        System.out.print("Masukkan Username: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Password: ");
        String pass = input.nextLine();
        return userController.loginFunc(nama, pass);
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
        boolean cek = keranjangController.vieww(user);
        if (cek){
            System.out.println("Gunakan koma bila perlu (2,8,7): ");
            System.out.println("Pilih id barang yang akan di checkout: ");
            System.out.print("atau input n untuk kembali: ");
            String barang = input.nextLine();
            if (barang.equals("n")){
                return;
            }
            transaksiController.addTransaksi(user, barang);
        }
    }

    public static void sellBarang(NodeUser user){
        produkView.addProdukView(user);
    }

    public static void getBarangDagangan(NodeUser user){
        List<NodeProduk> barang = produkController.getDaganganUser(user);
        if (barang!=null){
            for (NodeProduk produk: barang){
                System.out.println(produk.viewDataProduk());
            }
            System.out.print("""
                               opsi: \s
                               1. Edit Barang
                               2. Kembali ke beranda
                               Masukkan pilihan: \s""");
            int op = input.nextInt();
            input.nextLine();
            if (op==1){
                produkView.updateProduk();
            }
        }
    }

    public static void viewAllBarang(NodeUser user){
        for (NodeProduk produk: ModelProduk.produkList){
            System.out.println(produk.viewDataProduk());
        }
        System.out.println("Masukkan kode barang dan jumlah bila ingin menambah ke keranjang");
        System.out.println("contoh: 3-1, 1-10");
        System.out.print("atau input n untuk kembali: ");
        String op = input.nextLine();
        if (!op.equals("n")){
            keranjangController.addProduk(user.getId_user(), op);
            System.out.println("Berhasil menambah wishlist!");
        }

    }

    public static void searchProduk(NodeUser user){
        boolean cek = produkView.searchPrduk();
        if (cek){
            System.out.println("Masukkan kode barang dan jumlah bila ingin menambah ke keranjang");
            System.out.println("contoh: 3-1, 1-10");
            System.out.print("atau input n untuk kembali: ");
            String op = input.nextLine();
            if (!op.equals("n")){
                keranjangController.addProduk(user.getId_user(), op);
                System.out.println("Berhasil menambah wishlist!");
            }
        }
    }

    public static void addUser(){
        userView2.addUser();
    }
}

