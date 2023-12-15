package modelJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;
import node.NodeJSON.NodeJSONProduk;
import node.NodeJSON.NodeJSONUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ModelJSONKeranjang {
    static final String fname = "src/database/keranjang.json";

    public static boolean cekFile(){
        boolean cek = false;
        try {
            java.io.File file = new java.io.File(fname);
            if (file.exists()){
                cek = true;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
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

    public static ArrayList<NodeKeranjang> readFromFile(){
        if (!cekFile()){
            createFileJSON();
            return null;
        }

        ArrayList<NodeKeranjang> listKeranjang = null;
        try (Reader reader = new FileReader(fname)) {
            JsonArray tempJson = JsonParser.parseReader(reader).getAsJsonArray();
            listKeranjang = convertToArrayList(tempJson);
        } catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }
        return listKeranjang;
    }

    public static void writeFileJSON(ArrayList<NodeKeranjang> listKeranjang) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(listKeranjang);

        try (FileWriter file = new FileWriter(fname)) {
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static ArrayList<NodeKeranjang> convertToArrayList(JsonArray arrayKeranjang){
        if (arrayKeranjang == null) return null;
        Type KeranjangListType = new TypeToken<ArrayList<NodeKeranjang>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(arrayKeranjang, KeranjangListType);
    }

    public static NodeUser convertObjUser(JSONObject userObject){
        NodeJSONUser userJson = new NodeJSONUser();

        int id_user = Integer.parseInt(userObject.get(userJson.getId_user()).toString());
        String nama = userObject.get(userJson.getNama()).toString();
        String user_name = userObject.get(userJson.getUserName()).toString();
        String password = userObject.get(userJson.getPassword()).toString();
        int saldo = Integer.parseInt(userObject.get(userJson.getSaldo()).toString());
        boolean status = Boolean.parseBoolean(userObject.get(userJson.getStatus()).toString());

        return new NodeUser(id_user, nama, user_name, password, saldo, status);
    }

    public static ArrayList<NodeProduk> convertJSONArrayProduk(JSONArray arrayProduk, NodeUser user){
        if(arrayProduk==null){
            return null;
        } else {
            ArrayList<NodeProduk> listBarang = new ArrayList<>();
            for (Object objProduk : arrayProduk) {
                JSONObject barang = (JSONObject) objProduk;
                NodeJSONProduk nodeJSONBarang = new NodeJSONProduk();
                int id_Barang = Integer.parseInt(barang.get(nodeJSONBarang.getId_barang()).toString());
                String nama = barang.get(nodeJSONBarang.getNamaBarang()).toString();
                int harga = Integer.parseInt(barang.get(nodeJSONBarang.getHarga()).toString());
                String kategori = barang.get(nodeJSONBarang.getKategori()).toString();
                int stok = Integer.parseInt(barang.get(nodeJSONBarang.getStok()).toString());
                listBarang.add(new NodeProduk(id_Barang, nama,harga, kategori, stok, user));
            }
            return listBarang;
        }
    }

}
