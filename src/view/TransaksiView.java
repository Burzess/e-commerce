package view;

import controller.TransaksiController;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeTransaksi;
import node.NodeClass.NodeUser;

import java.util.ArrayList;

public class TransaksiView {
    public static TransaksiController transaksiController = new TransaksiController();
    public void viewAllUserTransaksi(NodeUser user){
        ArrayList<NodeTransaksi> userTransaksi = transaksiController.viewUserTransaksi(user);
        for (NodeTransaksi transaksi: userTransaksi){
            transaksi.view();
        }
    }

//    public static void addTransaksiView(NodeUser user, ArrayList<NodeProduk> produks){
//        transaksiController.addTransaksi(user, produks);
//    }
}
