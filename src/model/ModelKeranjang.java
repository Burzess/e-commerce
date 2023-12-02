package model;

import node.NodeClass.NodeProduk;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeUser;
import model.Fake;

import java.util.ArrayList;

public class ModelKeranjang {
    int counter = 0;
    Fake.FakeModelGoods barangGlobal;
    ArrayList <NodeKeranjang> listKeranjang;
    public ModelKeranjang(Fake.FakeModelGoods listBarang) {
        counter = 0;
        listKeranjang = new ArrayList<>();
        barangGlobal = listBarang;
    }
    public void addKeranjang(NodeUser u) {
        counter++;
        listKeranjang.add(new NodeKeranjang(u));
    }
    public void addBarang(int idKeranjang, String stuff) {
        int found = searchId(idKeranjang);
        if (found == 0) {
            System.out.println("Maaf id tidak ada");
        }
        String[] temp = stuff.split(",");
        for (String i: temp) {
            String[] eachStuff = i.split("-");
            int idBarang = Integer.parseInt(eachStuff[0]);
            idBarang = barangGlobal.searchById(idBarang);
            if (idBarang == 0) {
                continue;
            }
            NodeProduk tempBarang = new NodeProduk(barangGlobal.getGoods(idBarang));
            int stokBarang = Integer.parseInt(eachStuff[1]);
            tempBarang.setStok(stokBarang);
            listKeranjang.get(idKeranjang).addBarang(tempBarang);
            System.out.println("Id="+idBarang+"stok="+stokBarang);
        }
    }
    public void delBarang(int Id) {

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
    public void viewKeranjangbyId(int Id) {
        int found = searchId(Id);
        if (found == 0) {
            System.out.println("salah");
        }
        NodeKeranjang target = listKeranjang.get(Id);
        target.viewbarang();
    }
}
