package gui;

import control.CLogin;

import javax.swing.*;
import java.awt.*;

public class VMenu extends JFrame {
    private JPanel menuPanel;
    private JMenuItem opcLogin;
    private JMenuItem opcSee;
    private JScrollPane scrPanel;

    public VMenu() {
        setContentPane(menuPanel);
        initComponents();
    }

    private void initComponents() {
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Menu");
        setSize(400, 300);
    }

    public void cargarPanel(JPanel panel) {
        scrPanel.setViewportView(panel);
    }

    public void setControlador(CLogin controlador) {
        opcLogin.addActionListener(controlador);
        opcSee.addActionListener(controlador);
    }

    public JMenuItem getOpcLogin() {
        return opcLogin;
    }

    public JMenuItem getOpcSee() {
        return opcSee;
    }
}
