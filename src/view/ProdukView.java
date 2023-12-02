package view;

import controller.ProdukController;

import java.util.Scanner;

public class ProdukView {
    private ProdukController produkController;
    private Scanner input;

    public ProdukView(ProdukController produkController, Scanner scanner) {
        this.produkController = produkController;
        this.input = scanner;
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

