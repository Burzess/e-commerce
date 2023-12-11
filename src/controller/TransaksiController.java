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

public class TransaksiController {
    ModelTransaksi modelTransaksi = new ModelTransaksi();
    public boolean addTransaksi(NodeUser user, String idbarang){
        if (idbarang.length() == 1){
            transaksiTunggal(user, idbarang);
            return true;
        }
        try {
            String[] stuff = idbarang.split(",");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Command Salah :D ");
        }

//        for (int i = 0; i<)

        ArrayList<NodeProduk> produks = new ArrayList<>();
        int total = 0;
        for (NodeProduk produk: produks){
            total+= produk.getHarga();
        }
        int id = modelTransaksi.getLastCode() + 1;

        NodeTransaksi nodeTransaksi = new NodeTransaksi(id, user, produks, total);
        modelTransaksi.addTransaksi(nodeTransaksi);
        return true;
    }


    public ArrayList<NodeTransaksi> viewUserTransaksi(NodeUser user){
        ArrayList<NodeTransaksi> transaksis = modelTransaksi.viewAllTransaksi(user);
        return transaksis;
    }
    public boolean transaksiTunggal(NodeUser user, String idbarang){
        NodeKeranjang keranjang = ModelKeranjang.searchIdKeranjang(user.getId_user());
        NodeProduk barang = keranjang.listBarang.get(Integer.parseInt(idbarang));
        NodeProduk barangReal = ModelProduk.searchProduk(Integer.parseInt(idbarang));
        int total = barang.getHarga();
        boolean cek = ProdukController.cekSisaStok(barangReal, barang.getStok());
        boolean cek2 = modelTransaksi.isUangCukup(user, total);

        if (cek==true && cek2==true){
            int id = modelTransaksi.getLastCode() + 1;
            ArrayList<NodeProduk> produks = new ArrayList<>();
            produks.add(barang);
            user.setSaldo(-total);
            NodeUser user1 = ModelUser.userList.get(barangReal.getUser().getId_user());
            user1.setSaldo(total);
            barangReal.setStok(-barang.getStok());
            KeranjangController.delProduk(keranjang.getId(), barang.getId_barang());
            NodeTransaksi nodeTransaksi = new NodeTransaksi(id, user, produks, total);
            modelTransaksi.addTransaksi(nodeTransaksi);

            System.out.println("Berhasil Membeli Barang!");
            System.out.println("Barang akan segera tiba di rumah anda ;)");
            return true;
        }
        return false;
    }
}
