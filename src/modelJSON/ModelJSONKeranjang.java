package modelJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import node.NodeClass.NodeBarang;
import node.NodeClass.NodeKeranjang;
import node.NodeJSON.NodeJSONBarang;
import node.NodeJSON.NodeJSONKeranjang;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONKeranjang {
    String fname = "src/database/keranjang.json";
    private NodeJSONKeranjang nodeJSONKeranjang = new NodeJSONKeranjang();

    public boolean cekFile(){
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

    public JSONArray convertToArrayJSON(List<NodeKeranjang> listKeranjang){
        if (listKeranjang == null){
            return null;
        } else {
            JSONArray arrayKeranjang = new JSONArray();
            for (NodeKeranjang keranjang:listKeranjang) {
                JSONObject objUser = new JSONObject();
                objUser.put(nodeJSONKeranjang.getId(), keranjang.getId());
                objUser.put(nodeJSONKeranjang.getUser(), keranjang.getUser());
                objUser.put(nodeJSONKeranjang.getListBarang(), keranjang.getListBarang());
                objUser.put(nodeJSONKeranjang.getTotalHarga(), keranjang.getListBarang());
                arrayKeranjang.add(objUser);
            }
            return arrayKeranjang;
        }
    }

    public void writeFileJSON(List<NodeKeranjang> listKeranjang) {
        JSONArray arrayKeranjang = convertToArrayJSON(listKeranjang);

        try (FileWriter file = new FileWriter(fname)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(arrayKeranjang);
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public List<NodeBarang> convertToArrayLIst(JSONArray arrayKeranjang){
        if(arrayKeranjang==null){
            return null;
        } else {
            List<NodeBarang> listBarang = new ArrayList<>();
            for (Object objKeranjang : arrayKeranjang) {
                JSONObject keranjang = (JSONObject) objKeranjang;
                NodeJSONKeranjang nodeJSONKeranjang = new NodeJSONKeranjang();
                int id_Barang = Integer.parseInt(barang.get(nodeJSONBarang.getId_barang()).toString());
                String nama = barang.get(nodeJSONBarang.getNamaBarang()).toString();
                int harga = Integer.parseInt(barang.get(nodeJSONBarang.getHarga()).toString());
                String kategori = barang.get(nodeJSONBarang.getKategori()).toString();
                int stok = Integer.parseInt(barang.get(nodeJSONBarang.getStok()).toString());
                listBarang.add(new NodeBarang(id_Barang, nama,harga, kategori, stok));
            }
            return listBarang;
        }
    }

    public List<NodeBarang> readFromFile(){
        if (!cekFile()){
            return null;
        }

        List<NodeBarang> listBarang = null;
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

    public void appendToFileJSON(List<NodeBarang> listBarang) {
        List<NodeBarang> barangList = readFromFile();

        if (barangList == null) {
            barangList = new ArrayList<>();
        }
        System.out.println(barangList.size());

        int i = 0;
        for (NodeBarang barang : barangList) {
            if (barang.getId_barang() == listBarang.get(i).getId_barang()) {
                System.out.println("id telah tersedia");
                return;
            }
        }

        barangList.addAll(listBarang);
        writeFileJSON(barangList);
    }

    public boolean deleteByIdJSONUser(int barangId) {
        List<NodeBarang> barangList = readFromFile();

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
        List<NodeBarang> barangList = readFromFile();

        if (barangList != null){
            for (NodeBarang barang : barangList) {
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
        List<NodeBarang> barangList = readFromFile();

        if (barangList != null){
            for (NodeBarang barang : barangList) {
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
