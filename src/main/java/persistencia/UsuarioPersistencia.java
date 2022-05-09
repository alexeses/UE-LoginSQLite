package persistencia;

import db.AccesoDB;
import model.Usuario;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioPersistencia {

    private final AccesoDB db;
    private static final String EROROR_CONEXION = "❌ Error de conexión, contacte al administrador";
    private static final String ERRROR_SQL = "❌ Error de SQL, contacte al administrador";
    private static final String OK_CONEXCERRADA = "✅ Conexión cerrada";

    public UsuarioPersistencia() {
        db = new AccesoDB();
    }

    public String getAllUserName(int i) {

        String query = "SELECT " // seleccionar todos los campos de la tabla
        + UsuarioContract.COLUMNUSER +
        " FROM " + UsuarioContract.TABLENAME +
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
                user = rs.getString(UsuarioContract.COLUMNUSER); // obtener el valor del campo
            }


        } catch (ClassNotFoundException e) {
            System.out.println(EROROR_CONEXION);
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(ERRROR_SQL);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
                System.out.println(OK_CONEXCERRADA);
            } catch (SQLException e) {
                System.out.println(ERRROR_SQL);
                e.printStackTrace();
            }
        }

        return user;
    }

    public String getAllUserPassword(int i) {

        String query = "SELECT " // seleccionar todos los campos de la tabla
                + UsuarioContract.COLUMNPASS +
                " FROM " + UsuarioContract.TABLENAME +
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
                pass = rs.getString(UsuarioContract.COLUMNPASS); // obtener el valor del campo
            }


        } catch (ClassNotFoundException e) {
            System.out.println(EROROR_CONEXION);
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(ERRROR_SQL);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
                System.out.println(OK_CONEXCERRADA);
            } catch (SQLException e) {
                System.out.println(ERRROR_SQL);
                e.printStackTrace();
            }
        }

        return pass;
    }

    public ArrayList<Usuario> allUsers() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String query = "SELECT " // seleccionar todos los campos de la tabla
                + UsuarioContract.COLUMNUSER + ", "
                + UsuarioContract.COLUMNPASS +
                " FROM " + UsuarioContract.TABLENAME;

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

                user = rs.getString(UsuarioContract.COLUMNUSER); // obtener el valor del campo
                pass = rs.getString(UsuarioContract.COLUMNPASS);

                    usuario = new Usuario(user, pass);
                    usuarios.add(usuario);
            }

        } catch (ClassNotFoundException e) {
            System.out.println(EROROR_CONEXION);
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(ERRROR_SQL);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
                System.out.println(OK_CONEXCERRADA);
            } catch (SQLException e) {
                System.out.println(ERRROR_SQL);
                e.printStackTrace();
            }
        }

        return usuarios;
    }

    public Usuario existUser(String user, String pass) {
        Usuario usuario = null;

        String query = "SELECT " // seleccionar todos los campos de la tabla
                + UsuarioContract.COLUMNUSER + ", "
                + UsuarioContract.COLUMNPASS
                + " FROM " + UsuarioContract.TABLENAME
                + " WHERE UPPER (" + UsuarioContract.COLUMNUSER + ") = UPPER (?)" // Igualamos MAYUS / MAYUS
                + " AND UPPER (" + UsuarioContract.COLUMNPASS + ") = UPPER (?)";

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
                user = rs.getString(UsuarioContract.COLUMNUSER); // obtener el valor del campo
                pass = rs.getString(UsuarioContract.COLUMNPASS);

                usuario = new Usuario(user, pass); // crear un usuario con los datos obtenidos
            }
        } catch (ClassNotFoundException e) {
            System.out.println(EROROR_CONEXION);
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(ERRROR_SQL);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
                System.out.println(OK_CONEXCERRADA);
            } catch (SQLException e) {
                System.out.println(ERRROR_SQL);
                e.printStackTrace();
            }
        }

        return usuario;
    }

    public int getNumberUsers() {
        String query = "SELECT COUNT(*) FROM " + UsuarioContract.TABLENAME;

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
            System.out.println(EROROR_CONEXION);
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(ERRROR_SQL);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
                System.out.println(OK_CONEXCERRADA);
            } catch (SQLException e) {
                System.out.println(ERRROR_SQL);
                e.printStackTrace();
            }
        }

        return number;
    }

    public void addUser(String user, String pass) {
        String query = "INSERT INTO " + UsuarioContract.TABLENAME + " (" + UsuarioContract.COLUMNUSER + ", " + UsuarioContract.COLUMNPASS + ") VALUES (? , ?)";

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
            System.out.println(EROROR_CONEXION);
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(ERRROR_SQL);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
                System.out.println(OK_CONEXCERRADA);
            } catch (SQLException e) {
                System.out.println(ERRROR_SQL);
                e.printStackTrace();
            }
        }
    }
}