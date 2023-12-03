package view;

import controller.ProdukController;
import node.NodeClass.NodeUser;

import java.util.Scanner;

public class ProdukView {
    private ProdukController produkController;
    private Scanner input;

    public void addProdukView(NodeUser user){
        System.out.println("---Add Produk---");
        System.out.print("Masukkan Nama Produk: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Harga: ");
        int hg = input.nextInt();
        input.nextLine();
        System.out.print("Masukkan Kategori: ");
        String ktg = input.nextLine();
        System.out.print("Masukkan Jumlah Stok: ");
        int stok = input.nextInt();
        input.nextLine();
        produkController.addProduk(nama, hg, ktg, stok, user);
    }

    public int viewwProduk(){
        System.out.println("Masukkan id produk: ");
        int id = input.nextInt();
        input.nextLine();
        String data = produkController.viewProduk(id);
        if (data != null) {
            System.out.println("Produk ditemukan: ");
            System.out.println(data);
            return id;
        }
        System.out.println("data tidak ditemukan");
        return -1;
    }
    
    public void updateProduk(){
        int a = viewwProduk();
        if (a != -1){
            System.out.print("""
                    Command Update:
                      1 -> Nama
                      2 -> Harga
                      3 -> Kategori
                      4 -> Stok
                    contoh = 1/Keranjang Bayi
                    Masukkan Command: 
                    """);
            String comm = input.nextLine();
            produkController.updateProduk(a, comm);
            System.out.println("Berhasil update");
        }
    }
}

