package controller;

import model.ModelKeranjang;
import model.ModelProduk;
import model.ModelUser;
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

    public static void main(String[] args) {
        ModelUser u = new ModelUser();
//        u.addUser(new NodeUser(1,"Alif","sleepy","ikan"));
        System.out.println(u.getUserList().get(0).getNama());
        ArrayList<NodeProduk> a = new ArrayList<>();
        a.add(new NodeProduk(1,"wiskas",10000,"makanan",10));
        a.add(new NodeProduk(2,"sarden",18000,"makanan",8));
        a.add(new NodeProduk(3,"king",20000,"makanan",3));
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
        int target = modelKeranjang.searchIdKeranjang(idKeranjang);
        if (target == 0) {
            System.out.println("Maaf Id Kerajang tidak ditemukan");
            return;
        }
        String[] filter = Stuff.split(",");
        for (String p: filter) {
            boolean status = modelKeranjang.addBarang(target,p);
            if (!status) System.out.println("Id barang not found");
        }
    }

    public void delKeranjang(int idKeranjang) {
        int found = modelKeranjang.searchIdKeranjang(idKeranjang);
        if (found == 0) {
            System.out.println("Maaf produk id not found");
            return;
        }
        modelKeranjang.delKeranjang(idKeranjang);
    }

    public void delProduk(int idKeranjang, int idProduk) {
        int targetKeranjang = modelKeranjang.searchIdKeranjang(idKeranjang);
        if (targetKeranjang == 0) {
            System.out.println("Maaf Id keranjang not found");
            return;
        }
        ArrayList<NodeProduk> tempListBarang = modelKeranjang.getBarangGlobal();
        int targetProduk = ModelProduk.searchProduk(idProduk,tempListBarang);
        if (targetProduk == 0){
            System.out.println("Maaf Id produk not found");
            return;
        }
        boolean status = modelKeranjang.delBarang(idKeranjang,idProduk);
    }

    public void viewKeranjang() {

    }
    public ModelKeranjang getModelKeranjang() {
        return modelKeranjang;
    }
}
