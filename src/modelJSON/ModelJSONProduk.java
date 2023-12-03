package modelJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import node.NodeClass.NodeProduk;
import node.NodeJSON.NodeJSONProduk;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONProduk {
    public static String fname = "src/database/produk.json";
    private static NodeJSONProduk nodeJSONBarang = new NodeJSONProduk();

    public static boolean cekFile(){
        boolean cek = false;
        try {
            java.io.File file = new java.io.File(fname);
            if (file.exists()){
                cek = true;
            }
        } catch (Exception asu){
            System.out.println(asu.getMessage());
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
                JSONObject objUser = new JSONObject();
                objUser.put(nodeJSONBarang.getId_barang(), barang.getId_barang());
                objUser.put(nodeJSONBarang.getNamaBarang(), barang.getNamaBarang());
                objUser.put(nodeJSONBarang.getHarga(), barang.getHarga());
                objUser.put(nodeJSONBarang.getKategori(), barang.getKategori());
                objUser.put(nodeJSONBarang.getStok(), barang.getStok());
                arrayBarang.add(objUser);
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
                listBarang.add(new NodeProduk(id_Barang, nama,harga, kategori, stok));
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

    public void appendToFileJSON(List<NodeProduk> listBarang) {
        List<NodeProduk> barangList = readFromFile();

        if (barangList == null) {
            barangList = new ArrayList<>();
        }
        System.out.println(barangList.size());

        int i = 0;
        for (NodeProduk barang : barangList) {
            if (barang.getId_barang() == listBarang.get(i).getId_barang()) {
                System.out.println("id telah tersedia");
                return;
            }
        }

        barangList.addAll(listBarang);
        writeFileJSON(barangList);
    }

    public boolean deleteByIdJSONUser(int barangId) {
        List<NodeProduk> barangList = readFromFile();

        if(barangList != null){
            if (barangList.removeIf(barang -> barang.getId_barang() == barangId)){
                writeFileJSON(barangList);
                System.out.println("delete success");
                return true;
            } else {
                System.out.println("id tidak ditemukan");
                return false;
            }
        }

        System.out.println("data kosong");
        return false;
    }
    public boolean updateJSONNamaBarang(int barangId, String nama){
        List<NodeProduk> barangList = readFromFile();

        if (barangList != null){
            for (NodeProduk barang : barangList) {
                if (barang.getId_barang() == barangId){
                    barang.setNamaBarang(nama);
                    writeFileJSON(barangList);
                    System.out.println("Data berhasil di perbarui");
                    return true;
                }
            }
        }

        System.out.println("Data kosong");
        return false;
    }

    public boolean updateJSONStokBarang(int barangId, int stok){
        List<NodeProduk> barangList = readFromFile();

        if (barangList != null){
            for (NodeProduk barang : barangList) {
                if (barang.getId_barang() == barangId){
                    barang.setStok(stok);
                    writeFileJSON(barangList);
                    System.out.println("Data berhasil di perbarui");
                    return true;
                }
            }
        }

        System.out.println("Data kosong");
        return false;
    }

}
