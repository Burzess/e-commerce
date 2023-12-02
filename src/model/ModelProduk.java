package model;

import modelJSON.ModelJSONProduk;
import modelJSON.ModelJSONUser;
import node.NodeClass.NodeProduk;

import java.util.ArrayList;
import java.util.List;

public class ModelProduk {
    public static List<NodeProduk> produkList;
    private int id = 0;

    public ModelProduk(List<NodeProduk> produkList) {
        this.produkList = ModelJSONProduk.readFromFile();
        if (produkList == null){
            this.produkList = new ArrayList<>();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down. Saving data to JSON file...");
            ModelJSONProduk.writeFileJSON(produkList);
        }));
    }

//    public List<NodeProduk> getProdukList() {
//        return produkList;
//    }

    public static NodeProduk searchProduk(int id){
        for (NodeProduk produk: produkList){
            if (produk.getId_barang() == id){
                return produk;
            }
        }
        return null;
    }
    public void addProduk(NodeProduk produk){
        this.produkList.add(produk);
    }

    public static void updateProduk(int opsi, String data, NodeProduk updateProduk){
        switch (opsi){
            case 1-> updateProduk.setNamaBarang(data);
            case 2-> updateProduk.setHarga(Integer.parseInt(data));
            case 3->updateProduk.setKategori(data);
            case 4-> updateProduk.setStok(Integer.parseInt(data));
        }
    }
}
