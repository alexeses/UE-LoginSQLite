package com.github.alexeses.gui;

import com.github.alexeses.control.CLogin;

import javax.swing.*;

public class VLogin extends JFrame {
    private JLabel lblTitulo;
    private JLabel lblUser;
    private JPasswordField txtPass;
    private JLabel lblPassword;
    private JTextField txtUser;
    private JButton btnAcceder;
    private JPanel mainPanel;

    public VLogin() {
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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

    public void setControlador(CLogin controlador) {
        btnAcceder.addActionListener(controlador);
    }

    public void hacerVisible() {
        setVisible(true);
    }
}
