package NodeUser;

public class NodeUser {
    private int id_user;
    private String nama;
    private String email;

    public NodeUser(int id_user, String nama, String email) {
        this.id_user = id_user;
        this.nama = nama;
        this.email = email;
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
