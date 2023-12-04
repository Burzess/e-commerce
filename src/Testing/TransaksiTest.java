package Testing;

import model.ModelTransaksi;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeTransaksi;
import node.NodeClass.NodeUser;

import java.util.ArrayList;
import java.util.List;

public class TransaksiTest {
    private static ModelTransaksi modelTransaksi;

    public TransaksiTest() {
        modelTransaksi = new ModelTransaksi();
    }

    public static void main(String[] args) {
        TransaksiTest test = new TransaksiTest();
        NodeUser login = new NodeUser(99, "jimbe", "jimbeGanteng", "123");
        List<NodeProduk> produks = new ArrayList<>();
        produks.add(new NodeProduk());
        produks.add(new NodeProduk());
        produks.add(new NodeProduk());
        NodeTransaksi transaksi = new NodeTransaksi(0,login,produks,300);
        test.modelTransaksi.addTransaksi(transaksi);
    }
}
