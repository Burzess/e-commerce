package node.NodeJSON;

import node.NodeClass.NodeBarang;
import node.NodeClass.NodeUser;

import java.util.ArrayList;

public class NodeJSONKeranjang {
    private String Id;
    private NodeJSONUser user;
    private ArrayList<NodeJSONBarang> listBarang;
    private String totalHarga;
    public NodeJSONKeranjang() {
        Id = "idKeranjang";
        user = new NodeJSONUser();
        listBarang = new ArrayList<>();
        totalHarga = "totalHarga";
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public NodeJSONUser getUser() {
        return user;
    }

    public void setUser(NodeJSONUser user) {
        this.user = user;
    }

    public ArrayList<NodeJSONBarang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(ArrayList<NodeJSONBarang> listBarang) {
        this.listBarang = listBarang;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }
}
