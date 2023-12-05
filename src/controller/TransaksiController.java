package controller;

import model.ModelTransaksi;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeTransaksi;
import node.NodeClass.NodeUser;

import java.util.ArrayList;

public class TransaksiController {
    ModelTransaksi modelTransaksi = new ModelTransaksi();
    public void addTransaksi(NodeUser user, ArrayList<NodeProduk> produks){
        int total = 0;
        for (NodeProduk produk: produks){
            total+= produk.getHarga();
        }
        int id = modelTransaksi.getLastCode() + 1;

        NodeTransaksi nodeTransaksi = new NodeTransaksi(id, user, produks, total);
        modelTransaksi.addTransaksi(nodeTransaksi);
    }

    public ArrayList<NodeTransaksi> viewUserTransaksi(NodeUser user){
        ArrayList<NodeTransaksi> transaksis = modelTransaksi.viewAllTransaksi(user);
        return transaksis;
    }
}
