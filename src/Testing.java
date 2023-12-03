import modelJSON.ModelJSONKeranjang;
import node.NodeClass.NodeKeranjang;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;
import view.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {

        ModelJSONKeranjang modelJSONKeranjang = new ModelJSONKeranjang();
        System.out.println(ModelJSONKeranjang.cekFile());
        NodeUser nodeUser = new NodeUser(1, "halim", "limmm", "123");
        ArrayList<NodeProduk> nodeProduk = new ArrayList<>();
        nodeProduk.add(new NodeProduk(1, "Ban Mobil", 500000, "Otomotif", 5));
        nodeProduk.add(new NodeProduk(2, "Knalpot", 50000, "Otomotif", 10));
        nodeProduk.add(new NodeProduk(3, "Kaca Film", 5100000, "Otomotif", 3));
        List<NodeKeranjang> nodeKeranjang = new ArrayList<>();
        nodeKeranjang.add(new NodeKeranjang(1, nodeUser,nodeProduk, 5650000));

        modelJSONKeranjang.writeFileJSON(nodeKeranjang);

        List<NodeKeranjang> ker = modelJSONKeranjang.readFromFile();

        System.out.println("jancok "+    ker.get(0).getListBarang());

        for (NodeKeranjang keranjang : ker) {
            System.out.println("Id keranjang: " + keranjang.getId());
            System.out.println("user: ");
            System.out.println(keranjang.getUser().getNama());
            System.out.println("list Produk:");
            if (keranjang.getListBarang() != null){
                for (NodeProduk barang : keranjang.getListBarang()) {
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
