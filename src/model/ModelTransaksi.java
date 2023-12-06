package model;

import modelJSON.ModelJSONKeranjang;
import modelJSON.ModelJSONTransaksi;
import node.NodeClass.NodeTransaksi;
import node.NodeClass.NodeUser;

import java.util.ArrayList;
import java.util.List;

public class ModelTransaksi {
    public static List<NodeTransaksi> transaksiList;

    public ModelTransaksi() {
        transaksiList = ModelJSONTransaksi.readFromFile();
        if (transaksiList == null) {
            transaksiList = new ArrayList<>();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down. Saving data to JSON file...");
            ModelJSONTransaksi.writeFileJSON(transaksiList);
        }));
    }

    public void addTransaksi(NodeTransaksi transaksi){
        transaksiList.add(transaksi);
    }

    public ArrayList<NodeTransaksi> viewAllTransaksi(NodeUser user){
        ArrayList<NodeTransaksi> listTransaksi = new ArrayList<>();
        for (NodeTransaksi transaksi: transaksiList){
            if (transaksi.user.getNama().equals(user.getNama())){
                listTransaksi.add(transaksi);
            }
        }
        return listTransaksi;
    }

    public int getLastCode(){
        int idx = transaksiList.size() -1;
        return transaksiList.get(idx).id_transaksi;
    }
}
