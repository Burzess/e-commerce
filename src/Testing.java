import modelJSON.ModelJSONKeranjang;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;
import view.ProdukView;
import view.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Testing {
    public static NodeUser Mainuser = new NodeUser(1, "halim", "limmm", "123");
    public static void main(String[] args) {

        ProdukView produkView = new ProdukView();
        produkView.addProdukView(Mainuser);
        ArrayList<NodeProduk> nodeProduk = new ArrayList<>();
        nodeProduk.add(new NodeProduk(1, "Ban Mobil", 500000, "Otomotif", 5, Mainuser));
        nodeProduk.add(new NodeProduk(2, "Knalpot", 50000, "Otomotif", 10, Mainuser));
        nodeProduk.add(new NodeProduk(3, "Kaca Film", 5100000, "Otomotif", 3, Mainuser));
        ArrayList<NodeKeranjang> nodeKeranjang = new ArrayList<>();
        nodeKeranjang.add(new NodeKeranjang(1, Mainuser,nodeProduk, 5650000));

        ModelJSONKeranjang.writeFileJSON(nodeKeranjang);

        List<NodeKeranjang> ker = ModelJSONKeranjang.readFromFile();

        for (NodeKeranjang keranjang : ker) {
            System.out.println("Id keranjang: " + keranjang.getId());
            System.out.println("user: ");
            System.out.println(keranjang.getUser().getNama());
            System.out.println("list Produk:");
            if (keranjang.listBarang != null){
                for (NodeProduk barang : keranjang.listBarang) {
                    System.out.println(barang.getNamaBarang());
                    System.out.println(barang.getHarga());
                    System.out.println(barang.getKategori());
                    System.out.println("-----------------------------");
                }
            }
        }
        UserView userView = new UserView();
        runApp(userView);
    }

    private static void runApp(UserView userView) {
        boolean exit = false;
        Scanner input = new Scanner(System.in);

        while (!exit) {
            System.out.print("""
                    \nUser Management System
                    1. Add User
                    2. Update User
                    3. Delete User
                    0. exit
                    Masukan Pilihan:\s""");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    userView.addUser();
                    break;
                case 2:
                    userView.updateUser();
                    break;
                case 3:
                    userView.deleteUser();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


}
