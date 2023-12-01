package node.NodeClass;

public class NodeUser {
    private int id_user;
    private String nama;
    private String userName;
    private String email;

    public NodeUser(int id_user, String nama, String userName,String email) {
        this.id_user = id_user;
        this.nama = nama;
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }
}
