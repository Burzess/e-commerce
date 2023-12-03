package node.NodeClass;

public class NodeUser {
    private int id_user;

    private String nama;

    private String username;
    private String password;
    public NodeUser(int id_user, String nama, String username, String password) {
        this.id_user = id_user;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
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
