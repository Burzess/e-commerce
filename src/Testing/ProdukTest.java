package Testing;

import node.NodeClass.NodeUser;
import view.ProdukView;

public class ProdukTest {
    private static ProdukView produkView;

    public ProdukTest() {
        this.produkView = new ProdukView();
    }

    public static void main(String[] args) {
        NodeUser login = new NodeUser(99, "jimbe", "jimbeGanteng", "123");
        ProdukTest produkTest = new ProdukTest();
//        produkView.addProdukView(login);
        produkView.viewwProduk();
//        produkView.searchPrduk();
        produkView.updateProduk();
        produkView.viewwProduk();

    }
}
