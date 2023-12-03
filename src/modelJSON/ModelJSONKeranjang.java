package modelJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;
import node.NodeJSON.NodeJSONKeranjang;
import node.NodeJSON.NodeJSONProduk;
import node.NodeJSON.NodeJSONUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONKeranjang {
    static final String fname = "src/database/keranjang.json";
    private NodeJSONKeranjang nodeJSONKeranjang = new NodeJSONKeranjang();

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
                objUser.put(nodeJSONKeranjang.getTotalHarga(), keranjang.getTotalHarga());
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

    public List<NodeKeranjang> convertToArrayList(JSONArray arrayKeranjang){
        if(arrayKeranjang==null){
            return null;
        } else {
            List<NodeKeranjang> listKeranjang = new ArrayList<>();
            for (Object objKeranjang : arrayKeranjang) {
                JSONObject keranjang = (JSONObject) objKeranjang;
                NodeJSONKeranjang nodeJSONKeranjang = new NodeJSONKeranjang();
                int id_Keranjang = Integer.parseInt(keranjang.get(nodeJSONKeranjang.getId()).toString());

                JSONObject userObject = (JSONObject) keranjang.get(nodeJSONKeranjang.getUser());
                NodeUser nodeUser = convertObjUser(userObject);

                JSONArray arrayProduk = (JSONArray) keranjang.get(nodeJSONKeranjang.getListBarang());
                ArrayList<NodeProduk> listProduk = convertJSONArrayProduk(arrayProduk);
                int total = Integer.parseInt(keranjang.get(nodeJSONKeranjang.getTotalHarga()).toString());
                listKeranjang.add(new NodeKeranjang(id_Keranjang,nodeUser,listProduk,total));
            }
            return listKeranjang;
        }
    }

    public NodeUser convertObjUser(JSONObject userObject){
        NodeJSONUser userJson = new NodeJSONUser();
        int id_user = Integer.parseInt(userObject.get(userJson.getId_user()).toString());
        String nama = (userObject != null && userObject.get(userJson.getNama()) != null)
                ? String.valueOf(userObject.get(userJson.getNama()))
                : "";

        String user_name = (userObject != null && userObject.get(userJson.getUserName()) != null)
                ? String.valueOf(userObject.get(userJson.getUserName()))
                : "";

        String password = (userObject != null && userObject.get(userJson.getPassword()) != null)
                ? String.valueOf(userObject.get(userJson.getPassword()))
                : "";
        return new NodeUser(id_user, nama, user_name, password);
    }

    public ArrayList<NodeProduk> convertJSONArrayProduk(JSONArray arrayProduk){
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
                listBarang.add(new NodeProduk(id_Barang, nama,harga, kategori, stok));
            }
            return listBarang;
        }
    }

    public List<NodeKeranjang> readFromFile(){
        if (!cekFile()){
            return null;
        }

        List<NodeKeranjang> listKeranjang = null;
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(fname);
            JSONArray arrayBarang = (JSONArray) parser.parse(reader);
            listKeranjang = convertToArrayList(arrayBarang);
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("error: " + e.getMessage());
        } catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }
        return listKeranjang;
    }

}
