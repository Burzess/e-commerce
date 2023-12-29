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
import java.util.Scanner;

public class TransaksiController {
    ModelTransaksi modelTransaksi = new ModelTransaksi();
    static Scanner input = new Scanner(System.in);


    public void addTransaksi(NodeUser user, String idProduks){
        String stuff[] = idProduks.split(",");
        ArrayList<NodeProduk> produks = new ArrayList<>();
        NodeKeranjang keranjang = ModelKeranjang.searchIdKeranjang(user.getId_user());
        int total2 = 0;
        int id = modelTransaksi.getLastCode() + 1;

        for (String t: stuff){
            NodeProduk toTransaksi = addKeranjangToTransaksi(keranjang, Integer.parseInt(t));
            if (toTransaksi == null){
                return;
            }
            boolean cek2 = modelTransaksi.isUangCukup(user, toTransaksi.getHarga()* toTransaksi.getStok());
            if (cek2){
                int idSeller = toTransaksi.getUser().getId_user();
                NodeUser seller = ModelUser.searchUserById(idSeller);
                int total = toTransaksi.getHarga()*toTransaksi.getStok();
                total2 += total;

                produks.add(toTransaksi);
                System.out.println("Total: " + total2);
                System.out.println("Saldo anda: "+ user.getSaldo());
                System.out.print("Konfirmasi pembelian? [y/n]: ");
                String con = input.nextLine();
                if (con.equals("y")){
                    NodeTransaksi nodeTransaksi = new NodeTransaksi(id, user, produks, total2);
                    modelTransaksi.addTransaksi(nodeTransaksi);

                    cashFlow(user, seller, total);

                    KeranjangController.delProduk(keranjang.getId(), toTransaksi.getId_barang());

                    System.out.println("\nBerhasil Membeli Barang!");
                    System.out.println("Barang akan segera tiba di rumah anda ;)");
                    return;
                }else {
                    System.out.println("Tidak jadi membeli barang 😔");
                }
            }
        }


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
