package node.NodeJSON;

import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

import java.time.LocalDate;
import java.util.List;

public class NodeJSONTransaksi {
    public String id_transaksi;
    public String user;
    public String produkList;
    public String tanggal;
    public String totalHarga;

    public NodeJSONTransaksi() {
        this.id_transaksi = "id_transaksi";
        this.user = "user";
        this.produkList = "produkList";
        this.tanggal = "tanggal";
        this.totalHarga = "totalHarga";
    }
}
