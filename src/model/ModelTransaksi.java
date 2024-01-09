package model;

import com.google.gson.reflect.TypeToken;
import modelJSON.ModelJSON;
import node.NodeClass.NodeTransaksi;
import node.NodeClass.NodeUser;

import java.util.ArrayList;

public class ModelTransaksi {
    public static ArrayList<NodeTransaksi> transaksiList;
    ModelJSON modelJSON;

    public ModelTransaksi() {
        modelJSON = new ModelJSON<>("src/database/transaksi.json");
        transaksiList = modelJSON.readFromFile(new TypeToken<ArrayList<NodeTransaksi>>() {
        }.getType());

        if (transaksiList == null) {
            transaksiList = new ArrayList<>();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down. Saving data to JSON file...");
            modelJSON.writeToFile(transaksiList);
        }));
    }

    public void addTransaksi(NodeTransaksi transaksi){
        transaksiList.add(transaksi);
    }

    public ArrayList<NodeTransaksi> viewAllTransaksi(NodeUser user){
        ArrayList<NodeTransaksi> listTransaksi = new ArrayList<>();
        for (NodeTransaksi transaksi: transaksiList){
            if (transaksi.user.getId_user()==(user.getId_user())){
                listTransaksi.add(transaksi);
            }
        }
        return listTransaksi;
    }

    public int getLastCode(){
        if (transaksiList.size()==0){
            return -1;
        }
        int idx = transaksiList.size() -1;
        return transaksiList.get(idx).id_transaksi;
    }

    public boolean isUangCukup(NodeUser user, int total){
        if (user.getSaldo()>total){
            return true;
        }
        System.out.println("Saldo Anda Tidak Cukup :)");
        return false;
    }
}
