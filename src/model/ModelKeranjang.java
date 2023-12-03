package model;

import modelJSON.ModelJSONKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeUser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelKeranjang {
    ArrayList<NodeProduk> barangGlobal;
    ArrayList<NodeKeranjang> listKeranjang;

    public ModelKeranjang(ArrayList<NodeProduk> listBarang) {
        listKeranjang = ModelJSONKeranjang.readFromFile();
        if (listKeranjang == null) {
            listKeranjang = new ArrayList<>();
        }
        System.out.println(listKeranjang.isEmpty());
        barangGlobal = listBarang;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down. Saving data to JSON file...");
            ModelJSONKeranjang.writeFileJSON(listKeranjang);
        }));
    }

    public void addKeranjang(NodeUser u) {
        listKeranjang.add(new NodeKeranjang(u));
    }

    public boolean addBarang(int idKeranjang, String id_Stok) {
        NodeKeranjang keranjang = listKeranjang.get(idKeranjang-1);
        String[] strBarang = id_Stok.split("-");
        for (String f: strBarang) {
            boolean status = isStringDigitsOnly(f);
            if (status) continue;
            f = filterStringNumberOnly(f);
        }
        int idProduk = Integer.parseInt(strBarang[0]);
        int stokBarang = Integer.parseInt(strBarang[1]);
//        System.out.println("id "+idProduk+" stok "+stokBarang);
        int found = ModelProduk.searchProduk(idProduk,barangGlobal);
//        System.out.println("status " +found);
        if (found < 0) return false;
        NodeProduk temp = barangGlobal.get(found-1);
        NodeProduk copy = new NodeProduk(temp);
        copy.setStok(stokBarang);
        keranjang.addBarang(copy);
        return true;
    }

    public boolean delKeranjang(int idKeranjang) {
        int target = searchIdKeranjang(idKeranjang);
        if (target == 0) return false;
        listKeranjang.remove(idKeranjang);
        return true;
    }

    public boolean delBarang(int idKeranjang, int Id) {
        int found = searchIdKeranjang(Id);
        if (found == 0) return false;
        listKeranjang.get(idKeranjang).deleteBarang(Id);
        return true;
    }

    public int searchIdKeranjang(int id) {
        int found = 0;
        for (NodeKeranjang k: listKeranjang) {
            if (k.getId() == id) {
                found = k.getId();
            }
        }
        return found;
    }

    public static boolean isStringDigitsOnly(String s) {
        boolean status = Pattern.matches("^[0-9]+$",s);
        if (status) return true;
        return false;
    }

    public static String filterStringNumberOnly(String s) {
        boolean status = isStringDigitsOnly(s);
        if (status) return s;
//            System.out.println(Pattern.matches("^[0-9]+$",strBarang[1]));
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher filter = pattern.matcher(s);
        StringBuffer filteredString = new StringBuffer();
        while (filter.find()) {
            filteredString.append(filter.group());
        }
        String result = filteredString.toString();
        return result;
    }

    public ArrayList<NodeProduk> getBarangGlobal() {
        return barangGlobal;
    }

    public ArrayList<NodeKeranjang> getListKeranjang() {
        return listKeranjang;
    }
}