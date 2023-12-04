package model;

import modelJSON.ModelJSONKeranjang;
import modelJSON.ModelJSONTransaksi;
import node.NodeClass.NodeTransaksi;

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
}
