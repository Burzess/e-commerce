package modelJSON;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import node.NodeClass.NodeTransaksi;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONTransaksi {
    private static final String fname = "src/database/transaksi.json";
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


    public static void writeFileJSON(List<NodeTransaksi> transaksiList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(transaksiList);

        try (FileWriter file = new FileWriter(fname)) {
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static List<NodeTransaksi> convertToArrayLIst(JsonArray arrayTransaksi){
        if(arrayTransaksi==null){
            return null;
        }
        Type produkListType = new TypeToken<ArrayList<NodeTransaksi>>() {}.getType();
        Gson gson =new Gson();
        return gson.fromJson(arrayTransaksi, produkListType);
    }

    public static List<NodeTransaksi> readFromFile(){
        if (!cekFile()){
            createFileJSON();
            return null;
        }

        List<NodeTransaksi> transaksiList = null;
        try (Reader reader = new FileReader(fname)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);

            if (jsonElement.isJsonArray()) {
                JsonArray arrayTransaksi = jsonElement.getAsJsonArray();
                transaksiList = convertToArrayLIst(arrayTransaksi);
            }
        } catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }
        return transaksiList;
    }
}
