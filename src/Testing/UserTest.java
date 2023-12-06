package Testing;

import view.KeranjangView;
import view.ProdukView;
import view.UserView;

public class UserTest {
    public static void main(String[] args) {
        ProdukView produkView = new ProdukView();

        UserView userView = new UserView();
        KeranjangView keranjangView = new KeranjangView(produkView, userView);
        userView.setKeranjangView(keranjangView);
//        userView.addUser();
        userView.updateUser();
    }
}
