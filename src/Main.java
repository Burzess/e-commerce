import node.NodeClass.NodeUser;
import view.MainView;
import view.MenuView;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int opsi;

        do{
            NodeUser loggedUser;
            MenuView.displayFirstMenu();
            opsi = input.nextInt();
            input.nextLine();
            switch (opsi){
                case 1 -> {
                    loggedUser = MainView.login();
                    if (loggedUser!=null){
                        int ops;
                        do {
                            System.out.println("\nHalo "+loggedUser.getNama());
                            System.out.println("Saldo anda: "+loggedUser.getSaldo());
                            if (!loggedUser.isStatus()){
                                MenuView.displayMainMenu();
                            } else {
                                MenuView.displayMainMenuSeller();
                            }
                            ops = input.nextInt();
                            input.nextLine();
                            switch (ops){
                                case 1 -> MainView.searchProduk(loggedUser);
                                case 2 -> MainView.viewAllBarang(loggedUser);
                                case 3 ->{
                                    if (!loggedUser.isStatus()){
                                        MenuView.displayProfile();
                                    } else {
                                        MenuView.displayProfilePremium();
                                    }
                                    MainView.updateUser(loggedUser);
                                }
                                case 4 -> MainView.viewKeranjang(loggedUser);
                                case 5 -> MainView.viewAllUserTransaksi(loggedUser);
                                case 6 -> MainView.sellBarang(loggedUser);
                                case 7 -> MainView.getBarangDagangan(loggedUser);
                                case 0 -> System.out.println("Logout Berhasil");
                                default -> throw new IllegalStateException("Unexpected value: " + ops);
                            }
                        }while (ops != 0);
                    }
                } case 2 -> MainView.addUser();
            }

        }while (opsi!=0);
    }
}
