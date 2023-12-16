package modelJSON;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import node.NodeClass.NodeProduk;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ModelJSONProduk {
    private static final String fname = "src/database/produk.json";

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


    public static void writeFileJSON(List<NodeProduk> listBarang) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(listBarang);

        try (FileWriter file = new FileWriter(fname)) {
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
            JsonElement jsonElement = JsonParser.parseReader(reader);

            if (jsonElement.isJsonArray()) {
                JsonArray arrayBarang = jsonElement.getAsJsonArray();
                listBarang = convertToArrayLIst(arrayBarang);
            }
        } catch (IOException e){
            System.out.println("error: " + e.getMessage());
        }
        return listBarang;
    }

}
