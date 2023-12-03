package node.NodeJSON;

public class    NodeJSONProduk {
    private String  id_barang;
    private String namaBarang;
    private String  harga;
    private String kategori;
    private String  stok;

    public NodeJSONProduk() {
        this.id_barang = "id_barang";
        this.namaBarang = "namaBarang";
        this.harga = "harga";
        this.kategori = "kategori";
        this.stok = "stok";
    }

    public String getId_barang() {
        return id_barang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }

    public String getStok() {
        return stok;
    }
}
