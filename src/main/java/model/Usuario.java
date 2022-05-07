package model;

public class Usuario {
    private String name;
    private String password;

    public Usuario(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
