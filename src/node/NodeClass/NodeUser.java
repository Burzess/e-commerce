package node.NodeClass;

public class NodeUser {
    private int id_user;

    private String nama;

    private String username;
    private String password;

    private int saldo;
    private boolean status;
    public NodeUser(int id_user, String nama, String username, String password) {
        this.id_user = id_user;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.saldo = 0;
        status = false;
    }

    public NodeUser(int id_user, String nama, String username, String password, int saldo, boolean status) {
        this.id_user = id_user;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.saldo = saldo;
        this.status = status;
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


    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo += saldo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
