package view;

import controller.KeranjangController;
import controller.ProdukController;
import controller.UserController;
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

    public static NodeUser login(){
        System.out.print("Masukkan Username: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Password: ");
        String pass = input.nextLine();
        NodeUser logged = userController.loginFunc(nama, pass);
        return logged;
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

    public static void sellBarang(NodeUser user){
        produkView.addProdukView(user);
    }

    public static void getBarangDagangan(NodeUser user){
        List<NodeProduk> barang = produkController.getDaganganUser(user);
        if (barang!=null){
            for (NodeProduk produk: barang){
                String data = produk.viewDataProduk();
                System.out.println(data);
            }
        }
    }
}

