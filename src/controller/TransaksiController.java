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
    public boolean addTransaksi(NodeUser user, String idbarang) {
        ArrayList<NodeProduk> produks = new ArrayList<>();
        if (idbarang.length() == 1) {
            int id = modelTransaksi.getLastCode() + 1;
            NodeProduk produk = transaksiTunggal(user, idbarang);
            int total = produk.getHarga()*produk.getStok();
            produks.add(produk);
            NodeTransaksi nodeTransaksi = new NodeTransaksi(id, user, produks, total);
            modelTransaksi.addTransaksi(nodeTransaksi);

            System.out.println("Berhasil Membeli Barang!");
            System.out.println("Barang akan segera tiba di rumah anda ;)");
            return true;
        }
//        String[] stuff = new String[0];
            String[] stuff = idbarang.split(",");
//        try {
//            stuff = idbarang.split(",");
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Command Salah :D ");
//        }

        for (int i = 0; i < stuff.length;i++){
            NodeProduk produk = transaksiTunggal(user, String.valueOf(Integer.parseInt(stuff[i])));
            if (produk != null){
                produks.add(produk);
            }
        }

        int total = 0;
        for (NodeProduk produk : produks) {
            total += produk.getHarga();
        }
        int id = modelTransaksi.getLastCode() + 1;

        NodeTransaksi nodeTransaksi = new NodeTransaksi(id, user, produks, total);
        modelTransaksi.addTransaksi(nodeTransaksi);

        System.out.println("Berhasil Membeli Barang!");
        System.out.println("Barang akan segera tiba di rumah anda ;)");
        return true;
    }

    public NodeProduk transaksiTunggal(NodeUser user, String idbarang){
        NodeKeranjang keranjang = ModelKeranjang.searchIdKeranjang(user.getId_user());
        NodeProduk barang = keranjang.listBarang.get(Integer.parseInt(idbarang));
//        Wrong!!!
        NodeProduk barangReal = ModelProduk.searchProduk(Integer.parseInt(idbarang));
//        Wrong!! :DD KOnto
        int total = barang.getHarga()*barang.getStok();
        boolean cek = ProdukController.cekSisaStok(barangReal, barang.getStok());
        boolean cek2 = modelTransaksi.isUangCukup(user, total);

        if (cek==true && cek2==true){
            user.setSaldo(-total);
            NodeUser user1 = ModelUser.userList.get(barangReal.getUser().getId_user());
            user1.setSaldo(total);
            barangReal.setStok(-barang.getStok());
            KeranjangController.delProduk(keranjang.getId(), barang.getId_barang());

            return barang;
        }
        return null;
    }

    public ArrayList<NodeTransaksi> viewUserTransaksi(NodeUser user){
        ArrayList<NodeTransaksi> transaksis = modelTransaksi.viewAllTransaksi(user);
        return transaksis;
    }
}
