package node.NodeJSON;

public class NodeJSONUser {
    private String id_user;
    private String nama;
    private String userName;
    private String email;

    public NodeJSONUser(){
        id_user = "id_user";
        nama = "nama";
        userName = "userName";
        email = "email";
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

    public String getEmail() {
        return email;
    }
}
