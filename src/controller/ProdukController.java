package controller;

import model.ModelProduk;
import node.NodeClass.NodeProduk;

public class ProdukController {
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
