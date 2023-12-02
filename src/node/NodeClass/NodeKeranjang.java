package node.NodeClass;

import java.util.ArrayList;

public class NodeKeranjang {
    private int Id;
    private NodeUser user;
    ArrayList<NodeBarang> listBarang;
    int totalHarga;
    public NodeKeranjang(NodeUser u) {
        user = u;
        Id = u.getId_user();
        listBarang = new ArrayList<>();
    }
    public void addBarang(NodeBarang p) {
        NodeBarang temp = new NodeBarang(p);
        listBarang.add(temp);
    }

    public void deleteBarang(int id) {;
        listBarang.remove(id);
    }

    public int getId() {
        return Id;
    }

    public void viewbarang() {
        for (NodeBarang g: listBarang) {
            System.out.println(g.getId_barang());
            System.out.println(g.getNamaBarang());
        }
    }
}
