package controller;

import model.ModelKeranjang;
import model.ModelProduk;
import model.ModelTransaksi;
import model.ModelUser;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeTransaksi;
import node.NodeClass.NodeUser;

import java.util.ArrayList;
import java.util.List;

public class TransaksiController {
    ModelTransaksi modelTransaksi = new ModelTransaksi();

    public boolean addTransaksi(NodeUser user, String idProduks){
        String stuff[]=idProduks.split(",");
        ArrayList<NodeProduk> produks = new ArrayList<>();
        NodeKeranjang keranjang = ModelKeranjang.searchIdKeranjang(user.getId_user());
        int total = 0;
        int total2 = 0;
        int id = modelTransaksi.getLastCode() + 1;

        for (String t: stuff){
            NodeProduk toTransaksi = addKeranjangToTransaksi(keranjang, Integer.parseInt(t));
            boolean cek2 = modelTransaksi.isUangCukup(user, toTransaksi.getHarga()* toTransaksi.getStok());
            if (cek2){
                int idSeller = toTransaksi.getUser().getId_user();
                NodeUser seller = ModelUser.searchUserById(idSeller);
                total += toTransaksi.getHarga()*toTransaksi.getStok();
                total2 += total;
                cashFlow(user, seller, total);

                produks.add(toTransaksi);
                KeranjangController.delProduk(keranjang.getId(), toTransaksi.getId_barang());
            }
//            System.out.println(t);
        }

        if (produks!=null){
            NodeTransaksi nodeTransaksi = new NodeTransaksi(id, user, produks, total2);
            modelTransaksi.addTransaksi(nodeTransaksi);

            System.out.println("Berhasil Membeli Barang!");
            System.out.println("Barang akan segera tiba di rumah anda ;)");
            return true;
        }
        return true;
    }

    public NodeProduk addKeranjangToTransaksi(NodeKeranjang keranjang, int idBarang){
        NodeProduk barang = ModelKeranjang.searchBarangInKeranjang(keranjang, idBarang);
        NodeProduk barangReal = ModelProduk.searchProduk(idBarang);

        boolean cek = ProdukController.cekSisaStok(barangReal, barang.getStok());
        if (cek){
            barangReal.setStok(-barang.getStok());
            return barang;
        }
        return null;
    }

    public void cashFlow(NodeUser buyer, NodeUser seller, int total){
        NodeUser pembeli = ModelUser.searchUserById(buyer.getId_user());
        NodeUser seler = ModelUser.userList.get(seller.getId_user());
        pembeli.setSaldo(-total);
        seler.setSaldo(total);
    }


    public ArrayList<NodeTransaksi> viewUserTransaksi(NodeUser user){
        ArrayList<NodeTransaksi> transaksis = modelTransaksi.viewAllTransaksi(user);
        return transaksis;
    }
}
