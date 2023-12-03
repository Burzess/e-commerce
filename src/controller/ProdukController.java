package controller;

import model.ModelProduk;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

public class ProdukController {
    ModelProduk modelProduk = new ModelProduk();
    private int id = 0;
    public void addProduk(String nB, int hg, String ktg, int stok, NodeUser user){
        if(ModelProduk.cekBarang(nB)){
            NodeProduk produk = new NodeProduk(id, nB, hg, ktg, stok, user);
            ModelProduk.addProdukModel(produk);
            id++;
            System.out.println("Produk Berhasil ditambah!!");
        } else {
            System.out.println("Nama barang sudah ada, gunakan nama lain :D");
        }
    }
    public void updateProduk(int id, String update){
        NodeProduk nodeProduk = ModelProduk.searchProduk(id);
        String[] stuff = update.split("/");
        ModelProduk.updateProduk(Integer.parseInt(stuff[0]), stuff[1], nodeProduk);
    }

    public String viewProduk(int id){
        NodeProduk produk = ModelProduk.searchProduk(id);
        if (produk != null){
            return produk.viewDataProduk();
        }
        return null;
    }
}
