package controller;

import model.ModelProduk;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

import java.util.List;

public class ProdukController {
    ModelProduk modelProduk = new ModelProduk();

    public void addProduk(String nB, int hg, String ktg, int stok, NodeUser user){
        if(ModelProduk.cekBarang(nB)){
            int id = modelProduk.getLastCode() + 1;
            NodeProduk produk = new NodeProduk(id, nB, hg, ktg, stok, user);
            ModelProduk.addProdukModel(produk);
            System.out.println("Produk Berhasil ditambah!!");
        } else {
            System.out.println("Nama barang sudah ada, gunakan nama lain :D");
        }
    }
    public void updateProduk(int id, String update){
        NodeProduk nodeProduk = ModelProduk.searchProduk(id);
        try {
            String[] stuff = update.split("/");
            ModelProduk.updateProduk(Integer.parseInt(stuff[0]), stuff[1], nodeProduk);
            System.out.println("Berhasil update");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Command Salah :D ");
        }
    }

    public void deletProduk(int idUser){
        modelProduk.deleteProdukUser(idUser);
    }

    public String viewProduk(int id){
        NodeProduk produk = ModelProduk.searchProduk(id);
        if (produk != null){
            return produk.viewDataProduk();
        }
        return null;
    }

    public List<NodeProduk> searchProduk(String namaProduk){
        List<NodeProduk> hasilProduk = modelProduk.seachProduk(namaProduk);
        if (hasilProduk != null){
            return hasilProduk;
        }

        return null;
    }

    public List<NodeProduk> getDaganganUser(NodeUser user){
        List<NodeProduk> barang = modelProduk.getBarangUser(user);
        if (barang.size() == 0){
            System.out.println("Tidak ada barang");
            return null;
        }

        return  barang;
    }
}
