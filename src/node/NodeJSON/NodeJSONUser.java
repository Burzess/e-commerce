package node.NodeJSON;

public class NodeJSONUser {
    private String id_user;
    private String nama;
    private String userName;
    private String password;

    public NodeJSONUser(){
        id_user = "id_user";
        nama = "nama";
        userName = "userName";
        password = "password";
    }

    public String getUserName() {
        return userName;
    }

    public String getId_user() {
        return id_user;
    }

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }
}
