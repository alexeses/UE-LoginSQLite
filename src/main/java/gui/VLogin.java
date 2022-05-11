package gui;

import control.CLogin;

import javax.swing.*;

public class VLogin extends JPanel {
    private JLabel lblTitulo;
    private JLabel lblUser;
    private JPasswordField txtPass;
    private JLabel lblPassword;
    private JTextField txtUser;
    private JButton btnAcceder;
    private JPanel mainPanel;
    private JButton btnRegistro;

    public VLogin() {
        add(mainPanel);
    }

    public JButton getBtnAcceder() {
        return btnAcceder;
    }

    public JTextField getTxtUser() {
        return txtUser;
    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }

    public JButton getBtnRegistro() {
        return btnRegistro;
    }

    public void setControlador(CLogin controlador) {
        btnAcceder.addActionListener(controlador);
        btnRegistro.addActionListener(controlador);
    }

}
