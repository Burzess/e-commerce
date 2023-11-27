import NodeUser.NodeUser;
import modelJSON.ModelJSONUser;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ModelJSONUser modelJSONUser = new ModelJSONUser();
        List<NodeUser> listUser = modelJSONUser.readFromFile();
        System.out.println("--------------------------");
        for (NodeUser user : listUser) {
            System.out.println(user.getId_user());
            System.out.println(user.getNama());
            System.out.println(user.getEmail());
            System.out.println("--------------------------");
        }
        System.out.println();
        modelJSONUser.deleteById(3);

        List<NodeUser> users = new ArrayList<>();
        users.add(new NodeUser(4, "Abdurrahman", "abdurrahman@gmail.com"));
        modelJSONUser.appendToFileJSON(users);
        List<NodeUser> updateListUser = modelJSONUser.readFromFile();
        for (NodeUser user : updateListUser) {
            System.out.println(user.getId_user());
            System.out.println(user.getNama());
            System.out.println(user.getEmail());
            System.out.println("--------------------------");
        }
    }
}
