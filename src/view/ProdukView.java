package view;

import controller.ProdukController;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

import java.util.List;
import java.util.Scanner;

public class ProdukView {
    ProdukController produkController = new ProdukController();
    private final Scanner input = new Scanner(System.in);

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
        System.out.print("Masukkan id produk: ");
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
                    Masukkan Command:\s""");
            String comm = input.nextLine();
            produkController.updateProduk(a, comm);
        }
    }

    public boolean searchPrduk(){
        System.out.print("""
                ===========PENCARIAN PRODUK===========
                masukan nama produk yang ingin di cari:\s""");
        String namaProduk = input.nextLine();
        List<NodeProduk> hasilProduk = produkController.searchProduk(namaProduk);
        System.out.println("hasil pencarian: ");
        if (hasilProduk != null){
            for (NodeProduk produk : hasilProduk) {
                System.out.println("----------------------------");
                System.out.println("ID      : " + produk.getId_barang());
                System.out.println("Nama    : " + produk.getNamaBarang());
                System.out.println("Harga   : " + produk.getHarga());
                System.out.println("Kategori: " + produk.getKategori());
                System.out.println("Stok    : " + produk.getStok());
                System.out.println("Penjual : "+produk.getUser().getNama());
            }

            return true;
        } else {
            System.out.println("Produk yang anda cari tidak ada");
        }
        return false;
    }
}

