import controller.UserController;
import node.NodeClass.NodeUser;
import view.MainView;
import view.MenuView;
import view.ProdukView;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int opsi;
        ProdukView produkView = new ProdukView();

        do{
            NodeUser loggedUser = null;
            MenuView.displayFirstMenu();
            opsi = input.nextInt();
            input.nextLine();
            switch (opsi){
                case 1 -> {
                    loggedUser = MainView.login();
                    if (loggedUser!=null){
                        int ops;
                        do {
                            if (!loggedUser.isStatus()){
                                MenuView.displayMainMenu();
                            } else {
                                MenuView.displayMainMenuSeller();
                            }
                            ops = input.nextInt();
                            input.nextLine();
                            switch (ops){
                                case 1 ->{
                                    produkView.searchPrduk();
                                }
                                case 3 ->{
                                    if (!loggedUser.isStatus()){
                                        MenuView.displayProfile();
                                    } else {
                                        MenuView.displayProfilePremium();
                                    }
                                    MainView.updateUser(loggedUser);
                                }
                                case 4 ->{
                                    MainView.viewKeranjang(loggedUser);
                                }
                                case 5 ->{
                                    MainView.sellBarang(loggedUser);
                                }
                                case 6 ->{
                                    MainView.getBarangDagangan(loggedUser);
                                }
                                case 0 ->{
                                    System.out.println("Logout Berhasil");
                                    break;
                                }
                            }
                        }while (ops != 0);
                    }
                } case 2 ->{

                }
            }

        }while (opsi!=0);
    }
}
