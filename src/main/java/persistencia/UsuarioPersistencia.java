package persistencia;

import db.AccesoDB;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;

import static persistencia.Messages.*;

public class UsuarioPersistencia {

    private final AccesoDB db;

    public UsuarioPersistencia() {
        db = new AccesoDB();
    }

    public String getAllUserName(int i) {

        String query = "SELECT " // seleccionar todos los campos de la tabla
        + Messages.COLUMNUSER +
        " FROM " + Messages.TABLENAME +
        " WHERE ROWID = " + i;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String user = "";

        try {
            con = db.getConexion();
            stmt = con.createStatement(); // crear un statement para realizar la consulta
            rs = stmt.executeQuery(query); // ejecutar la query

            while (rs.next()) {
                user = rs.getString(Messages.COLUMNUSER); // obtener el valor del campo
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // debemos cerrar la conexión de forma inversa a la que se abrió
            finConnect(con, stmt, rs);
        }

        return user;
    }

    public String getAllUserPassword(int i) {

        String query = "SELECT " // seleccionar todos los campos de la tabla
                + Messages.COLUMNPASS +
                " FROM " + Messages.TABLENAME +
                " WHERE ROWID = " + i;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String pass = "";

        try {
            con = db.getConexion();
            stmt = con.createStatement(); // crear un statement para realizar la consulta
            rs = stmt.executeQuery(query); // ejecutar la query

            while (rs.next()) {
                pass = rs.getString(Messages.COLUMNPASS); // obtener el valor del campo
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, rs);
        }

        return pass;
    }

    public ArrayList<Usuario> allUsers() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String query = "SELECT " // seleccionar todos los campos de la tabla
                + Messages.COLUMNUSER + ", "
                + Messages.COLUMNPASS +
                " FROM " + Messages.TABLENAME;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Usuario usuario;

        try {
            con = db.getConexion();
            stmt = con.createStatement(); // crear un statement para realizar la consulta
            rs = stmt.executeQuery(query); // ejecutar la query

            String user;
            String pass;

            while (rs.next()) {

                user = rs.getString(Messages.COLUMNUSER); // obtener el valor del campo
                pass = rs.getString(Messages.COLUMNPASS);

                    usuario = new Usuario(user, pass);
                    usuarios.add(usuario);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, rs);
        }

        return usuarios;
    }

    public Usuario existUser(String user, String pass) {
        Usuario usuario = null;

        String query = "SELECT " // seleccionar todos los campos de la tabla
                + Messages.COLUMNUSER + ", "
                + Messages.COLUMNPASS
                + " FROM " + Messages.TABLENAME
                + " WHERE UPPER (" + Messages.COLUMNUSER + ") = UPPER (?)" // Igualamos MAYUS / MAYUS
                + " AND UPPER (" + Messages.COLUMNPASS + ") = UPPER (?)";

        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs = null;

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query); // preparar la query
            // Vamos a preparar la query con los datos que nos pasan
            pstmt.setString(1, user); // el primer ? será el dni || (posición de la ?, parámetro del método)
            pstmt.setString(2, pass); // el segundo ? será el password || (posición de la ?, parámetro del método)
            rs = pstmt.executeQuery(); // ejecutar la query

            if (rs.next()) {
                user = rs.getString(Messages.COLUMNUSER); // obtener el valor del campo
                pass = rs.getString(Messages.COLUMNPASS);

                usuario = new Usuario(user, pass); // crear un usuario con los datos obtenidos
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuario;
    }

    public int getNumberUsers() {
        String query = "SELECT COUNT(*) FROM " + Messages.TABLENAME;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int number = 0;

        try {
            con = db.getConexion();
            stmt = con.createStatement(); // crear un statement para realizar la consulta
            rs = stmt.executeQuery(query); // ejecutar la query

            while (rs.next()) {

                number = rs.getInt(1);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, rs);
        }

        return number;
    }

    public void addUser(String user, String pass) {
        String query = "INSERT INTO " + Messages.TABLENAME + " (" + Messages.COLUMNUSER + ", " + Messages.COLUMNPASS + ") VALUES (? , ?)";

        Connection con = null;
        PreparedStatement pstmt = null; // Por que su inicialización deberá ir entre un try y un catch
        ResultSet rs = null;

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query); // preparar la query

            pstmt.setString(1, user); // (pos1, parámetro del método)
            pstmt.setString(2, pass); // (pos2, parámetro del método)

            pstmt.executeUpdate(); // Al ser DELETE, INSERT o UPDATE usamos executeUpdate

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, rs);
        }
    }

    private void finConnect(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}