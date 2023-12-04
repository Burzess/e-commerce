package controller;

import model.ModelKeranjang;
import model.ModelProduk;
import model.ModelUser;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

import java.util.ArrayList;

public class KeranjangController {
    private ModelKeranjang modelKeranjang;
    private ArrayList<NodeUser> userList;

    public KeranjangController(ArrayList<NodeProduk> produkGlobal, ArrayList<NodeUser> listUser) {
        modelKeranjang = new ModelKeranjang(produkGlobal);
        userList = listUser;
    }

    public void addKeranjang(int Id) {
        NodeUser target = UserController.findUserById(Id,userList);
        if (target == null) {
            System.out.println("Maaf Id User tidak ditemukan");
            return;
        }
        modelKeranjang.addKeranjang(target);
    }

    public void addProduk(int idKeranjang, String Stuff) {
        NodeKeranjang target = modelKeranjang.searchIdKeranjang(idKeranjang);
        if (target == null) {
            System.out.println("Maaf Id Kerajang tidak ditemukan");
            return;
        }
        String[] filter = Stuff.split(",");
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
}
