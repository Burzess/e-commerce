package controller;

import model.ModelKeranjang;
import model.ModelProduk;
import model.ModelUser;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

import java.util.ArrayList;

public class KeranjangController {
    static ModelKeranjang modelKeranjang = new ModelKeranjang((ArrayList<NodeProduk>) ModelProduk.produkList);
    ModelProduk modelProduk;
    ModelUser modelUser;

    public KeranjangController(ProdukController cProduk, UserController cUser) {
        modelKeranjang = new ModelKeranjang((ArrayList<NodeProduk>) ModelProduk.produkList);
        modelProduk = cProduk.modelProduk;
        modelUser = cUser.modelUser;
    }

    public KeranjangController(){

    }

    public void addKeranjang(int Id) {
        NodeUser target = modelUser.getIdUser(Id);
        if (target == null) {
            System.out.println("Maaf Id User tidak ditemukan");
            return;
        }
        modelKeranjang.addKeranjang(target);
    }

    public boolean addProduk(int idKeranjang, String Stuff) {
        NodeKeranjang target = modelKeranjang.searchIdKeranjang(idKeranjang);
        if (target == null) {
            System.out.println("Maaf Id Kerajang tidak ditemukan");
            return false;
        }
        String[] filter = Stuff.split(",\\s+");
        for (String p: filter) {
            boolean status = modelKeranjang.addBarang(target.getId(),p);
            if (!status){
                continue;
            }

        }
        return true;
    }

    public void delKeranjang(int idKeranjang) {
        NodeKeranjang found = modelKeranjang.searchIdKeranjang(idKeranjang);
        if (found == null) {
            System.out.println("Maaf produk id not found");
            return;
        }
        modelKeranjang.delKeranjang(idKeranjang);
    }

    public static void delProduk(int idKeranjang, int idProduk) {
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
        modelKeranjang.delBarang(idKeranjang,idProduk);
//        System.out.println("delete contro status "+status);
    }

    public void viewAll() {
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

    public boolean vieww(NodeUser user){
        NodeKeranjang keranjang = modelKeranjang.searchIdKeranjang(user.getId_user());
        if (keranjang.listBarang.isEmpty()){
            System.out.println("Belum ada barang di keranjang");
            return false;
        }
        keranjang.viewbarang();
        return true;
    }



}
