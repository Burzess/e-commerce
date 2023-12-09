package controller;

import model.ModelKeranjang;
import model.ModelProduk;
import model.ModelUser;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

import java.util.ArrayList;

public class KeranjangController {
    ModelKeranjang modelKeranjang;
    ModelProduk modelProduk;
    ModelUser modelUser;

    public KeranjangController(ProdukController cProduk, UserController cUser) {
        modelKeranjang = new ModelKeranjang((ArrayList<NodeProduk>) ModelProduk.produkList);
        modelProduk = cProduk.modelProduk;
        modelUser = cUser.modelUser;
    }

    public void addKeranjang(int Id) {
        NodeUser target = modelUser.getIdUser(Id);
        if (target == null) {
            System.out.println("Maaf Id User tidak ditemukan");
            return;
        }
        modelKeranjang.addKeranjang(target);
    }

    public void addProduk(int idKeranjang, String Stuff) {
        refreshKeranjang();
        NodeKeranjang target = modelKeranjang.searchIdKeranjang(idKeranjang);
        if (target == null) {
            System.out.println("Maaf Id Kerajang tidak ditemukan");
            return;
        }
        String[] filter = Stuff.split(",\\s+");
        for (String p: filter) {
            boolean status = modelKeranjang.addBarang(target.getId(),p);
            if (!status) System.out.println("Id barang not found");
        }
    }

    public void delKeranjang(int idKeranjang) {
        NodeKeranjang found = modelKeranjang.searchIdKeranjang(idKeranjang);
        if (found == null) {
            System.out.println("Maaf produk id not found");
            return;
        }
        modelKeranjang.delKeranjang(idKeranjang);
    }

    public void delProduk(int idKeranjang, int idProduk) {
        refreshKeranjang();
        NodeKeranjang targetKeranjang = modelKeranjang.searchIdKeranjang(idKeranjang);
        if (targetKeranjang == null) {
            System.out.println("Maaf Id keranjang not found");
            return;
        }
        ArrayList<NodeProduk> tempListBarang = modelKeranjang.getBarangGlobal();
        NodeProduk targetProduk = ModelProduk.searchProduk(idProduk,tempListBarang);
        if (targetProduk == null){
            System.out.println("Maaf Id produk not found");
            return;
        }
        boolean status = modelKeranjang.delBarang(idKeranjang,idProduk);
//        System.out.println("delete contro status "+status);
    }

    public void refreshKeranjang() {
        modelKeranjang.refreshUser((ArrayList<NodeUser>) modelUser.getUserList());
        modelKeranjang.refreshProduk();
    }

    public void viewAll() {
        refreshKeranjang();
        ArrayList<NodeKeranjang> listKeranjang = modelKeranjang.getListKeranjang();
        if (listKeranjang.isEmpty()) {
            System.out.println("Keranjang belanja kosong.");
        } else {
            System.out.println("Isi keranjang belanja:");

            for (NodeKeranjang keranjang : listKeranjang) {
                System.out.println("ID Keranjang: " + keranjang.getId());
                System.out.println("Barang dalam keranjang:");

                ArrayList<NodeProduk> barangList = keranjang.listBarang;
                for (NodeProduk barang : barangList) {
                    System.out.println("- Nama Barang: " + barang.getNamaBarang());
                    System.out.println("  Harga: " + barang.getHarga());
                    System.out.println("  Stok: " + barang.getStok());
                }
                System.out.println("Total Harga: " + keranjang.totalHarga);

                System.out.println();
            }
        }
    }
}
