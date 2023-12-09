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
        if (produkList == null) return null;
        for (NodeProduk produk: produkList){
            if (produk.getId_barang() == id){
                return produk;
            }
        }
        return null;
    }

    public static NodeProduk searchProduk(int id, ArrayList<NodeProduk> produkList){
        for (NodeProduk produk: produkList){
            if (produk.getId_barang() == id){
                return produk;
            }
        }
        return null;
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

    public void deleteProdukUser(int idUser) {
        if (!produkList.isEmpty()) {
            List<NodeProduk> tempProduk = new ArrayList<>();
            for (NodeProduk produk : produkList) {
                if (produk.getUser().getId_user() == idUser) {
                    tempProduk.add(produk);
                }
            }

            produkList.removeAll(tempProduk);

        } else {
            System.out.println("Produk list kosong.");
        }
    }



    public static void updateProduk(int opsi, String data, NodeProduk updateProduk){
        switch (opsi){
            case 1-> updateProduk.setNamaBarang(data);
            case 2-> updateProduk.setHarga(Integer.parseInt(data));
            case 3-> updateProduk.setKategori(data);
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
    public static boolean cekBarang(int id, ArrayList<NodeProduk> list) {
        if (list == null) return false;
        for (NodeProduk k: list) {
            if (k.getId_barang() == id) {
                return false;
            }
        }
        return true;
    }

    public int getLastCode(){
        int idx;
        if(produkList.isEmpty()) {
            return -1;
        } else {
            idx = produkList.size() - 1;
            System.out.println(idx);
            return produkList.get(idx).getId_barang();
        }
    }
}
