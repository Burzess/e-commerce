package node.NodeClass;

import java.util.ArrayList;

public class NodeKeranjang {
    private int Id;
    private NodeUser user;
    public ArrayList<NodeProduk> listBarang;
    public int totalHarga = 0;

    public NodeKeranjang(NodeUser u) {
        user = u;
        Id = u.getId_user();
        listBarang = new ArrayList<>();
    }

    public NodeKeranjang(int id, NodeUser u, ArrayList<NodeProduk> listProduk, int total) {
        Id = id;
        user = u;
        listBarang = listProduk;
        totalHarga = total;
    }

    public void addBarang(NodeProduk p) {
        NodeProduk temp = new NodeProduk(p);
        listBarang.add(temp);
    }

    public void deleteBarang(int id) {;
        listBarang.remove(id);
    }

    public int getId() {
        return Id;
    }

    public int getTotal() {
        int total = 0;
        for (NodeProduk p: listBarang) {
            total+=(p.getHarga() * p.getStok());
        }
        return total;
    }

    public NodeUser getUser() {
        return user;
    }

    public void viewbarang() {
        for (NodeProduk g: listBarang) {
            System.out.println(g.getId_barang());
            System.out.println(g.getNamaBarang());
        }
    }
}