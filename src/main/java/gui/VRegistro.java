package gui;

import control.CLogin;

import javax.swing.*;

public class VRegistro extends JPanel{
    private JLabel lblTitle;
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JTextField txtRegNombre;
    private JPasswordField txtRegPass;
    private JButton btnRegistrar;
    private JButton btnBorrar;
    private JPanel mainRegistro;

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
}
