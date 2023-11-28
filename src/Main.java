import node.NodeUser.NodeUser;
import model.modelJSON.ModelJSONUser;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ModelJSONUser modelJSONUser = new ModelJSONUser();
        List<NodeUser> listUser = new ArrayList<>();
        listUser.add(new NodeUser(1, "user1", "user1@gmail.com"));
        listUser.add(new NodeUser(2, "user2", "user2@gmail.com"));
        modelJSONUser.appendToFileJSON(listUser);
        System.out.println("------------- BEFORE -------------");
        List<NodeUser> JSONUser = modelJSONUser.readFromFile();
        for (NodeUser user : JSONUser) {
            System.out.println("ID: " + user.getId_user());
            System.out.println("nama: " + user.getNama());
            System.out.println("email: " + user.getEmail());
            System.out.println("--------------------------");
        }
        System.out.println();
        System.out.print("Delete user ID 3: ");
        modelJSONUser.deleteByIdJSONUser(3);

        List<NodeUser> users = new ArrayList<>();
        users.add(new NodeUser(4, "user4", "user4@gmail.com"));
        System.out.print("\nTambah user ID 4: ");
        modelJSONUser.appendToFileJSON(users);
        System.out.print("\nUpdate user ID 1: ");
        modelJSONUser.updateJSONUser(1, "anonim@gmail.com");
        List<NodeUser> updateListUser = modelJSONUser.readFromFile();
        System.out.println("\n------------- AFTER -------------");
        for (NodeUser user : updateListUser) {
            System.out.println("ID: " + user.getId_user());
            System.out.println("nama: " + user.getNama());
            System.out.println("email: " + user.getEmail());
            System.out.println("--------------------------");
        }
    }
}
