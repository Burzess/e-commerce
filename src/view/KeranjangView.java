package view;

import controller.KeranjangController;

import java.util.Scanner;

public class KeranjangView {
    KeranjangController keranjang;
    Scanner input;

    public KeranjangView(ProdukView vProduk, UserView vUser) {
        keranjang = new KeranjangController(vProduk.produkController, vUser.userController);
        input = new Scanner(System.in);
    }

    public void addKeranjang() {
        System.out.println("---Add Keranjang---");
        System.out.println("Masukan id Keranjang");
        String tempStr = input.nextLine();
        int idKer = Integer.parseInt(tempStr);
        keranjang.addKeranjang(idKer);
    }

    public void addBarang(int idUser) {
        System.out.println("---Keranjang---");
        System.out.println("---Add Produk---");
        System.out.println("Masukan Produk id: ");
        System.out.println("contoh: idProduk-stokProduk 3-5");
        String tempStr = input.nextLine();
        keranjang.addProduk(idUser, tempStr);
    }

    public void delKeranjang() {
        System.out.println("---Delete Keranjang---");
        System.out.println("Masukan Id Keranjang yang ingin dihapus: ");
        String tempStr = input.nextLine();
        int idKer = Integer.parseInt(tempStr);
        keranjang.delKeranjang(idKer);
    }

    public void viewAllKeranjang() {
        keranjang.viewAll();
    }
}
