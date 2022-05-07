package gui;

import control.CLogin;

import javax.swing.*;

public class VSeeUsers {
    private JPanel seePanel;
    private JScrollPane scpPanel;
    private JTable table1;
    private JLabel lblTitle;
    private JButton btnVolver;

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setControlador(CLogin controlador) {
        btnVolver.addActionListener(controlador);
    }
}
