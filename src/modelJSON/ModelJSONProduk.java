package modelJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;
import node.NodeJSON.NodeJSONProduk;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
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

    public static ArrayList<NodeProduk> convertToArrayLIst(JsonArray arrayBarang){
        if(arrayBarang==null){
            return null;
        }
        Type produkListType = new TypeToken<ArrayList<NodeProduk>>() {}.getType();
        Gson gson =new Gson();
        return gson.fromJson(arrayBarang, produkListType);
    }

    public static List<NodeProduk> readFromFile(){
        if (!cekFile()){
            createFileJSON();
            return null;
        }

        List<NodeProduk> listBarang = null;

        try(Reader reader = new FileReader(fname)){
            JsonArray arrayBarang = JsonParser.parseReader(reader).getAsJsonArray();
            listBarang = convertToArrayLIst(arrayBarang);
        } catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }
        return listBarang;
    }

}
