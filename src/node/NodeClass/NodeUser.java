package node.NodeClass;

public class NodeUser {
    private int id_user;
    private final String nama;
    private String userName;
    private String password;

    public NodeUser(int id_user, String nama, String userName,String password) {
        this.id_user = id_user;
        this.nama = nama;
        this.userName = userName;
        this.password = password;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserName() {
        return userName;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }
}
