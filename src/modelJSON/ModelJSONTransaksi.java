package modelJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeTransaksi;
import node.NodeClass.NodeUser;
import node.NodeJSON.NodeJSONProduk;
import node.NodeJSON.NodeJSONTransaksi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONTransaksi {
    private static String fname = "src/database/transaksi.json";
    private static NodeJSONTransaksi nodeJSONTransaksi = new NodeJSONTransaksi();
    public static boolean cekFile(){
        boolean cek = false;
        try {
            java.io.File file = new java.io.File(fname);
            if (file.exists()){
                cek = true;
            }
        } catch (Exception u){
            System.out.println(u.getMessage());
        }
        return cek;
    }

    public static void createFileJSON() {
        try {
            File file = new File(fname);

            if (file.createNewFile()) {
                System.out.println("File JSON berhasil dibuat: " + fname);
            } else {
                System.out.println("File JSON sudah ada: " + fname);
            }
        } catch (IOException e) {
            System.out.println("Error saat membuat file JSON: " + e.getMessage());
        }
    }

    public static JSONArray convertToArrayJSON(List<NodeTransaksi> transaksiList){
        if (transaksiList == null){
            return null;
        } else {
            JSONArray arrayTransaksi = new JSONArray();
            for (NodeTransaksi transaksi:transaksiList) {
                JSONObject objTransaksi = new JSONObject();

                objTransaksi.put(nodeJSONTransaksi.id_transaksi, transaksi.id_transaksi);
                objTransaksi.put(nodeJSONTransaksi.user, transaksi.user);
                objTransaksi.put(nodeJSONTransaksi.produkList, transaksi.produkList);
                objTransaksi.put(nodeJSONTransaksi.tanggal, transaksi.tanggal);
                objTransaksi.put(nodeJSONTransaksi.totalHarga, transaksi.totalHarga);
                arrayTransaksi.add(objTransaksi);
            }
            return arrayTransaksi;
        }
    }

    public static void writeFileJSON(List<NodeTransaksi> transaksiList) {
        JSONArray arrayBarang = convertToArrayJSON(transaksiList);

        try (FileWriter file = new FileWriter(fname)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(arrayBarang);
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static List<NodeTransaksi> convertToArrayLIst(JSONArray arrayTransaksi){
        if(arrayTransaksi==null){
            return null;
        } else {
            List<NodeTransaksi> transaksiList = new ArrayList<>();
            for (Object objTransaksi : arrayTransaksi) {

                JSONObject transaksi = (JSONObject) objTransaksi;
                NodeJSONTransaksi nodeJSONTransaksi = new NodeJSONTransaksi();

                int id_Transaksi = Integer.parseInt(transaksi.get(nodeJSONTransaksi.id_transaksi).toString());

                JSONObject userObj = (JSONObject) transaksi.get(nodeJSONTransaksi.user);
                NodeUser userr = ModelJSONKeranjang.convertObjUser(userObj);

                JSONArray arrayProduk = (JSONArray) transaksi.get(nodeJSONTransaksi.produkList);
                ArrayList<NodeProduk> listProduk = ModelJSONKeranjang.convertJSONArrayProduk(arrayProduk, userr);

                int harga = Integer.parseInt(transaksi.get(nodeJSONTransaksi.totalHarga).toString());


                transaksiList.add(new NodeTransaksi(id_Transaksi, userr, listProduk, harga));
            }
            return transaksiList;
        }
    }

    public static List<NodeTransaksi> readFromFile(){
        if (!cekFile()){
            createFileJSON();
            return null;
        }

        List<NodeTransaksi> transaksiList = null;
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(fname);
            JSONArray arrayTransaksi = (JSONArray) parser.parse(reader);
            transaksiList = convertToArrayLIst(arrayTransaksi);
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("error: " + e.getMessage());
        } catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }
        return transaksiList;
    }
}
