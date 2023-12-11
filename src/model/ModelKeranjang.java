package model;

import modelJSON.ModelJSONKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeUser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelKeranjang {
    private ArrayList<NodeProduk> barangGlobal;
    private static ArrayList<NodeKeranjang> listKeranjang;

    public ModelKeranjang(ArrayList<NodeProduk> listBarang) {
        listKeranjang = ModelJSONKeranjang.readFromFile();
        if (listKeranjang == null) {
            listKeranjang = new ArrayList<>();
        }
        barangGlobal = listBarang;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down. Saving data to JSON file...");
            ModelJSONKeranjang.writeFileJSON(listKeranjang);
        }));
    }

    public boolean addKeranjang(NodeUser u) {
        boolean status = checkKeranjang(u,listKeranjang);
        if (!status) return false;
        listKeranjang.add(new NodeKeranjang(u));
        return true;
    }

    private static boolean checkKeranjang(NodeUser u, ArrayList<NodeKeranjang> list) {
        if (list == null) return false;
        for (NodeKeranjang k: list) {
            if (k.getUser().getId_user() == u.getId_user()) {
                return false;
            }
        }
        return true;
    }

    public boolean addBarang(int idKeranjang, String id_Stok) {
        NodeKeranjang keranjang = searchIdKeranjang(idKeranjang);
        // filter id_Stok
        String[] strBarang = id_Stok.split("-");
        for (String f: strBarang) {
            boolean status = isStringDigitsOnly(f);
            if (status) continue;
            f = filterStringNumberOnly(f);
        }
        // convert String to Integer
        int idProduk = Integer.parseInt(strBarang[0]);
        int stokBarang = Integer.parseInt(strBarang[1]);
//        System.out.println("id "+idProduk+" stok "+stokBarang);
        //
        NodeProduk targetProduk = ModelProduk.searchProduk(idProduk,barangGlobal);
//        System.out.println("status " +found);
        if (targetProduk == null) return false;
        boolean statusProduk = ModelProduk.cekBarang(targetProduk.getId_barang(),keranjang.listBarang);
//        System.out.println("status produk "+statusProduk);
        if (!statusProduk) return false;
        NodeProduk copy = new NodeProduk(targetProduk);
        if (stokBarang > targetProduk.getStok()) return false;
        copy.setStok(stokBarang);
        keranjang.addBarang(copy);
        keranjang.totalHarga = keranjang.getTotal();
        return true;
    }

    public boolean delKeranjang(int idKeranjang) {
//        System.out.println("yang di hapus "+idKeranjang);
        NodeKeranjang target = searchIdKeranjang(idKeranjang);
//        System.out.println("target di hapus "+target);
        if (target == null) return false;
        for (NodeKeranjang k: listKeranjang) {
            if (k.getId() == target.getId()) {
                listKeranjang.removeIf(nodeKeranjang -> nodeKeranjang.getId()== target.getId());
                return true;
            }
        }
        return false;
    }

    public boolean delBarang(int idKeranjang, int idBarang) {
        NodeKeranjang foundKeranjang = searchIdKeranjang(idKeranjang);
        if (foundKeranjang == null) return false;
        NodeProduk foundProduk = ModelProduk.searchProduk(idBarang,foundKeranjang.listBarang);
        if (foundProduk == null) return false;
        System.out.println("list barang "+foundKeranjang.listBarang);
        boolean statusDel = foundKeranjang.listBarang.removeIf(nodeProduk -> nodeProduk.getId_barang() == idBarang);
        if (!statusDel) return false;
        foundKeranjang.totalHarga = foundKeranjang.getTotal();
        return true;
    }

    public static NodeKeranjang searchIdKeranjang(int id) {
        for (NodeKeranjang k: listKeranjang) {
            if (k.getId() == id) {
                return k;
            }
        }
        return null;
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