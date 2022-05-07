package com.github.alexeses.persistencia;

import com.github.alexeses.db.AccesoDB;
import com.github.alexeses.model.Usuario;

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

                usuario = new Usuario(user, pass); // crear un usuario con los datos obtenidos
                usuarios.add(usuario); // agregamos el usuario a la lista

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
        // SELECT USUARIOS FROM USUARIOS WHERE USUARIOS.USUARIO = '$usuario' AND USUARIOS.PASSWORD = '$password'

        String query = "SELECT " // seleccionar todos los campos de la tabla
                + UsuarioContract.COLUMNUSER + ", "
                + UsuarioContract.COLUMNPASS
                + " FROM " + UsuarioContract.TABLENAME
                + " WHERE " + UsuarioContract.COLUMNUSER + " = ?"
                + " AND " + UsuarioContract.COLUMNPASS + " = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
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
}