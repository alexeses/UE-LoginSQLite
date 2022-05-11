package gui;

import control.CLogin;
import persistencia.UsuarioPersistencia;

import javax.swing.*;

public class VRegistro extends JPanel {
    private JLabel lblTitle;
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JTextField txtRegNombre;
    private JPasswordField txtRegPass;
    private JButton btnRegistrar;
    private JButton btnBorrar;
    private JPanel mainRegistro;
    UsuarioPersistencia uP;

    public VRegistro() {
        add(mainRegistro);
    }

    public void setControlador(CLogin controlador) {
        btnRegistrar.addActionListener(controlador);
        btnBorrar.addActionListener(controlador);
    }

    public JTextField getTxtRegNombre() {
        return txtRegNombre;
    }

    public JPasswordField getTxtRegPass() {
        return txtRegPass;
    }

    public void borrarDatos() {
        txtRegNombre.setText("");
        txtRegPass.setText("");
    }

    public String validarPassword(String pwd, String nom) {
        String error = "";

        if (pwd.isEmpty()) {
            error = "Debe introducir la password";
        } else if (pwd.length() < 8) {
            error = "La password debe tener al menos 8 caracteres";
        } else if (pwd.length() > 20) {
            error = "La password debe tener menos de 20 caracteres";
        } else if (pwd.matches(".*\\s+.*")) {
            error = "La password no puede contener espacios";
        } else if (pwd.matches(".*[^a-zA-Z0-9&+_*].*")) {
            error = "La password no puede contener caracteres distintos de letras, números y &,+,_,*";
        } else if (!pwd.matches(".*[A-Z].*")) {
            error = "La password debe contener al menos una letra mayúscula";
        }

        if (nom.contains(pwd)) {
            error = "La password no puede ser igual al nombre de usuario";
        } else if (nom.length() < 8) {
            error = "El nombre de usuario debe tener al menos 8 caracteres";
        } else if (nom.length() > 20) {
            error = "El nombre de usuario debe tener menos de 20 caracteres";
        } else if (nom.matches(".*\\s+.*")) {
            error = "El nombre de usuario no puede contener espacios";
        } else if (nom.isEmpty()) {
            error = "Debe introducir el nombre de usuario";
        }

        return error;
    }
}