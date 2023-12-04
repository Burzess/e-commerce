package model;

import modelJSON.ModelJSONProduk;
import node.NodeClass.NodeProduk;

import java.util.ArrayList;
import java.util.List;

public class ModelProduk {
    public static List<NodeProduk> produkList;

    public ModelProduk() {
        this.produkList = ModelJSONProduk.readFromFile();
        if (produkList == null){
            this.produkList = new ArrayList<>();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down. Saving data to JSON file...");
            ModelJSONProduk.writeFileJSON(produkList);
        }));
    }
    public static void addProdukModel(NodeProduk produk){
        produkList.add(produk);
    }
    public static NodeProduk searchProduk(int id){
        for (NodeProduk produk: produkList){
            if (produk.getId_barang() == id){
                return produk;
            }
        }
        return null;
    }

    public static int searchProduk(int id, ArrayList<NodeProduk> produkList){
        for (NodeProduk produk: produkList){
            if (produk.getId_barang() == id){
                return id;
            }
        }
        return -1;
    }

    public List<NodeProduk> seachProduk(String namaProduk){
        List<NodeProduk> hasilPencarian = new ArrayList<>();

        String[] target = namaProduk.toLowerCase().split("\\s+");
        if (produkList != null){
            for (NodeProduk produk : produkList) {
                boolean match = true;
                String listNamaProduk = produk.getNamaBarang().toLowerCase();

                for (String kata : target) {
                    if (!listNamaProduk.contains(kata)){
                        match = false;
                        break;
                    }
                }

                if (match){
                    hasilPencarian.add(produk);
                }
            }
        }

        return hasilPencarian;
    }


    public static void updateProduk(int opsi, String data, NodeProduk updateProduk){
        switch (opsi){
            case 1-> updateProduk.setNamaBarang(data);
            case 2-> updateProduk.setHarga(Integer.parseInt(data));
            case 3->updateProduk.setKategori(data);
            case 4-> updateProduk.setStok(Integer.parseInt(data));
        }
    }

    public static boolean cekBarang(String nama) {
        if (produkList != null) {
            for (NodeProduk barang : produkList) {
                if (barang.getNamaBarang().equals(nama)) {
                    return false;
                }
            }
        }
        return true;
    }
}
