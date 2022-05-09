package gui;

import control.CLogin;
import persistencia.UsuarioPersistencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VSeeUsers extends JPanel {
    private JPanel seePanel;
    private JScrollPane scpPanel;
    private JTable tblUsers;
    private JLabel lblTitle;
    private JButton btnVolver;
    private boolean isCreated;
    UsuarioPersistencia up;

    public VSeeUsers(UsuarioPersistencia up) {
        this.up = up;
        add(seePanel);
        crearTabla();
        //updateTable();

    }

    private void crearTabla() {
            DefaultTableModel model = (DefaultTableModel) tblUsers.getModel();

            model.addColumn("Usuario");
            model.addColumn("Contraseña");

            tblUsers.setModel(model);
    }

    private void updateTable() {
        DefaultTableModel model = (DefaultTableModel)tblUsers.getModel();

        for (int i = 1; i <= up.getNumberUsers(); i++) {
            model.addRow(
                    new Object[]{up.getAllUserName(i), up.getAllUserPassword(i)}
            );
        }

        tblUsers.setModel(model);
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setControlador(CLogin controlador) {
        btnVolver.addActionListener(controlador);
    }
}
