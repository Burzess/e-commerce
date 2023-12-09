package node.NodeClass;

import java.util.ArrayList;

public class NodeKeranjang {
    private int Id;
    private NodeUser user;
    public ArrayList<NodeProduk> listBarang = null;
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

    public void validasiProduk(ArrayList<NodeProduk> listValid) {
        for (NodeProduk p: listBarang) {
            for (NodeProduk v: listValid) {
                if (p.getId_barang()!=v.getId_barang()) continue;

                boolean vNamaProduk = p.getNamaBarang().equals(v.getNamaBarang());
                boolean vKategori = p.getKategori().equals(v.getKategori());
                boolean vHarga = p.getNamaBarang().equals(v.getHarga());

                if (!vNamaProduk) p.setNamaBarang(v.getNamaBarang());
                if (!vKategori) p.setKategori(v.getKategori());
                if (!vHarga) p.setHarga(v.getHarga());
            }
        }
    }

    public void validasiUser(ArrayList<NodeUser> listValid) {
        for (NodeUser u: listValid) {
            if (user.getId_user() != u.getId_user()) continue;

            boolean vName = user.getNama().equals(u.getNama());
            boolean vUsername = user.getUsername().equals(u.getUsername());
            boolean vPass = user.getPassword().equals(u.getPassword());
            boolean vSaldo = user.getSaldo() == u.getSaldo();

            if (!vName) user.setNama(u.getNama());
            if (!vUsername) user.setUsername(u.getUsername());
            if (!vPass) user.setPassword(u.getPassword());
            if (!vSaldo) user.setSaldo(u.getSaldo());
            user.setStatus(u.isStatus());
        }
    }

    public boolean isProdukValid(ArrayList<NodeProduk> produkValid) {
        for (NodeProduk vProduk: produkValid) {

        }
        return true;
    }

    public void viewbarang() {
        for (NodeProduk g: listBarang) {
            System.out.println(g.getId_barang());
            System.out.println(g.getNamaBarang());
        }
    }
}