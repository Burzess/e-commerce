package node.NodeClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    public int id_transaksi;
    public NodeUser user;
    public List<NodeProduk> produkList;
    public LocalDate tanggal;
    public int totalHarga;

    public Transaksi(int id_transaksi, NodeUser user, int total) {
        this.id_transaksi = id_transaksi;
        this.user = user;
        this.produkList = new ArrayList<>();
        this.totalHarga = total;
    }
}
