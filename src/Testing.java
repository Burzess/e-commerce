import view.UserView;

import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {

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
