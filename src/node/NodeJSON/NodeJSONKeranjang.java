package node.NodeJSON;

import java.util.ArrayList;

public class NodeJSONKeranjang {
    private String Id;
    private String user;
    private String  listBarang;
    private String totalHarga;
    public NodeJSONKeranjang() {
        Id = "idKeranjang";
        user = "userObject";
        listBarang = "listBarang";
        totalHarga = "totalHarga";
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String  user) {
        this.user = user;
    }

    public String  getListBarang() {
        return listBarang;
    }

    public void setListBarang(String listBarang) {
        this.listBarang = listBarang;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }
}
