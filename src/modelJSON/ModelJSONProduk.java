package modelJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;
import node.NodeJSON.NodeJSONProduk;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONProduk {
    private static String fname = "src/database/produk.json";
    private static NodeJSONProduk nodeJSONBarang = new NodeJSONProduk();

    public static boolean cekFile(){
        boolean cek = false;
        try {
            java.io.File file = new java.io.File(fname);
            if (file.exists()){
                cek = true;
            }
        } catch (Exception a){
            System.out.println(a.getMessage());
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

    public static JSONArray convertToArrayJSON(List<NodeProduk> listBarang){
        if (listBarang == null){
            return null;
        } else {
            JSONArray arrayBarang = new JSONArray();
            for (NodeProduk barang:listBarang) {
                JSONObject objProduk = new JSONObject();
                objProduk.put(nodeJSONBarang.getId_barang(), barang.getId_barang());
                objProduk.put(nodeJSONBarang.getNamaBarang(), barang.getNamaBarang());
                objProduk.put(nodeJSONBarang.getHarga(), barang.getHarga());
                objProduk.put(nodeJSONBarang.getKategori(), barang.getKategori());
                objProduk.put(nodeJSONBarang.getStok(), barang.getStok());
                objProduk.put(nodeJSONBarang.getUser(), barang.getUser());
                arrayBarang.add(objProduk);
            }
            return arrayBarang;
        }
    }

    public static void writeFileJSON(List<NodeProduk> listBarang) {
        JSONArray arrayBarang = convertToArrayJSON(listBarang);

        try (FileWriter file = new FileWriter(fname)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(arrayBarang);
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static List<NodeProduk> convertToArrayLIst(JSONArray arrayBarang){
        if(arrayBarang==null){
            return null;
        } else {
            List<NodeProduk> listBarang = new ArrayList<>();
            for (Object objBarang : arrayBarang) {

                JSONObject barang = (JSONObject) objBarang;
                NodeJSONProduk nodeJSONBarang = new NodeJSONProduk();

                int id_Barang = Integer.parseInt(barang.get(nodeJSONBarang.getId_barang()).toString());
                String nama = barang.get(nodeJSONBarang.getNamaBarang()).toString();
                int harga = Integer.parseInt(barang.get(nodeJSONBarang.getHarga()).toString());
                String kategori = barang.get(nodeJSONBarang.getKategori()).toString();
                int stok = Integer.parseInt(barang.get(nodeJSONBarang.getStok()).toString());

                JSONObject userObj = (JSONObject) barang.get(nodeJSONBarang.getUser());
                NodeUser userr = (NodeUser) ModelJSONKeranjang.convertObjUser(userObj);

                listBarang.add(new NodeProduk(id_Barang, nama,harga, kategori, stok, userr));
            }
            return listBarang;
        }
    }

    public static List<NodeProduk> readFromFile(){
        if (!cekFile()){
            createFileJSON();
            return null;
        }

        List<NodeProduk> listBarang = null;
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(fname);
            JSONArray arrayBarang = (JSONArray) parser.parse(reader);
            listBarang = convertToArrayLIst(arrayBarang);
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("error: " + e.getMessage());
        } catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }
        return listBarang;
    }

}
