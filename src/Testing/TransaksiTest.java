package Testing;

import model.ModelTransaksi;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeTransaksi;
import node.NodeClass.NodeUser;
import view.TransaksiView;

import java.util.ArrayList;

public class TransaksiTest {
    private static ModelTransaksi modelTransaksi;

    public TransaksiTest() {
        modelTransaksi = new ModelTransaksi();
    }

    public static void main(String[] args) {
        TransaksiTest test = new TransaksiTest();
        NodeUser login = new NodeUser(99, "jimbe", "jimbeGanteng", "123");
        ArrayList<NodeProduk> produks = new ArrayList<>();
        produks.add(new NodeProduk(21, "kelapa", 300, "buah", 9, login));
        produks.add(new NodeProduk(22, "melon", 300, "buah", 9, login));
        produks.add(new NodeProduk(23, "gedang", 300, "buah", 9, login));
        NodeTransaksi transaksi = new NodeTransaksi(1,login,produks,400);
//        test.modelTransaksi.addTransaksi(transaksi);
//        TransaksiView.addTransaksiView(login, produks);
    }
}
