package model;

import node.NodeClass.NodeProduk;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeUser;

import java.util.ArrayList;

public class ModelKeranjang {
    int counter = 0;
    ArrayList<NodeProduk> barangGlobal;
    ArrayList<NodeKeranjang> listKeranjang;
    public ModelKeranjang(ArrayList<NodeProduk> listBarang) {
        counter = 0;
        listKeranjang = new ArrayList<>();
        barangGlobal = listBarang;
    }
    public void addKeranjang(NodeUser u) {
        counter++;
        listKeranjang.add(new NodeKeranjang(u));
    }
    public boolean addBarang(int idKeranjang, String id_Stok) {
        NodeKeranjang keranjang = listKeranjang.get(idKeranjang);
        String[] strBarang = id_Stok.split("-");
        int idProduk = Integer.parseInt(strBarang[0]);
        int stokBarang = Integer.parseInt(strBarang[1]);
        int found = ModelProduk.searchProduk(idProduk,barangGlobal);
        if (found == 0) return false;
        NodeProduk temp = ModelProduk.produkList.get(found);
        temp.setStok(stokBarang);
        keranjang.addBarang(temp);
        return true;
    }
    public boolean delBarang(int idKeranjang, int Id) {
        int found = searchId(Id);
        if (found == 0) return false;
        listKeranjang.get(idKeranjang).deleteBarang(Id);
        return true;
    }
    public int searchId(int id) {
        int found = 0;
        for (NodeKeranjang k: listKeranjang) {
            if (k.getId() == id) {
                found = k.getId();
            }
        }
        return found;
    }
    public boolean viewKeranjangbyId(int Id) {
        int found = searchId(Id);
        if (found == 0) return false;
        NodeKeranjang target = listKeranjang.get(Id);
        target.viewbarang();
        return true;
    }
}