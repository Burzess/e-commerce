package node.NodeClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NodeTransaksi {
    public int id_transaksi;
    public NodeUser user;
    public List<NodeProduk> produkList;
    public LocalDate tanggal;
    public int totalHarga;

    public NodeTransaksi(int id_transaksi, NodeUser user, List<NodeProduk> produkList, int total) {
        this.id_transaksi = id_transaksi;
        this.user = user;
        this.produkList = produkList;
        this.totalHarga = total;
        this.tanggal = LocalDate.now();
    }
}
