package node.NodeClass;

public class NodeProduk {
    private int id_barang;
    private NodeUser user;
    private String namaBarang;
    private int harga;
    private String kategori;
    private int stok;

    public NodeProduk(int id_barang, String namaBarang, int harga, String kategori, int stok, NodeUser user) {
        this.id_barang = id_barang;
        this.user = user;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.kategori = kategori;
        this.stok = stok;
    }
    public NodeUser getUser() {
        return user;
    }

    public NodeProduk(NodeProduk that) {
        this(that.getId_barang(),that.getNamaBarang(),that.harga,that.getKategori(),that.stok, that.user);
    }

    public int getId_barang() {
        return id_barang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }

    public int getStok() {
        return stok;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String viewDataProduk(){
        String data = "Nama: "+namaBarang+"\nHarga: "+harga+"\nKategori: "+kategori+"\nStok: "+stok+"\n"+"seller: "+user.getNama();
        return data;
    }
}
