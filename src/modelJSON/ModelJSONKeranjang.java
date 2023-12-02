package modelJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;
import node.NodeJSON.NodeJSONKeranjang;
import node.NodeJSON.NodeJSONUser;
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
                NodeJSONUser userJson = (NodeJSONUser) keranjang.get(nodeJSONKeranjang.getUser());
                int id_User = Integer.parseInt(userJson.getId_user());
                NodeUser user = (NodeUser) keranjang.get(nodeJSONKeranjang.getUser());
                ArrayList<NodeProduk> listProduk = (ArrayList<NodeProduk>) keranjang.get(nodeJSONKeranjang.getListBarang());
                int total = Integer.parseInt(keranjang.get(nodeJSONKeranjang.getTotalHarga()).toString());
                listKeranjang.add(new NodeKeranjang(id_Keranjang,user,listProduk,total));
            }
            return listKeranjang;
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

    public void appendToFileJSON(List<NodeKeranjang> listKeranjang) {
        List<NodeKeranjang> keranjangList = readFromFile();

        if (keranjangList == null) {
            keranjangList = new ArrayList<>();
        }
        System.out.println(keranjangList.size());

        int i = 0;
        for (NodeKeranjang keranjang : keranjangList) {
            if (keranjang.getId() == listKeranjang.get(i).getId()) {
                System.out.println("id telah tersedia");
                return;
            }
        }

        keranjangList.addAll(listKeranjang);
        writeFileJSON(keranjangList);
    }
}
