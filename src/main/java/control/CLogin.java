package control;


import gui.*;
import persistencia.UsuarioPersistencia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CLogin implements ActionListener {
    UsuarioPersistencia up;
    VLogin vLogin;
    VMenu vMenu;
    VWelcome vWelcome;
    VSeeUsers vSeeUsers;
    VRegistro vRegistro;
    int attempts = 0;

    public CLogin(UsuarioPersistencia up, VLogin vLogin, VMenu vMenu, VWelcome vWelcome, VSeeUsers vSeeUsers, VRegistro vRegistro) {
        this.up = up;
        this.vLogin = vLogin;
        this.vMenu = vMenu;
        this.vWelcome = vWelcome;
        this.vSeeUsers = vSeeUsers;
        this.vRegistro = vRegistro;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            if (e.getActionCommand().contains("Login")) {
                String user = vLogin.getTxtUser().getText();
                String pass = vLogin.getTxtPass().getText();

                if (up.existUser(user, pass) != null) {
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                    attempts = 0;
                } else if (attempts > 3) {
                    JOptionPane.showMessageDialog(null, "Has excedido el numero de intentos");
                    System.out.println(attempts);
                    System.exit(0);
                } else if (up.existUser(user, pass) == null) {
                    JOptionPane.showMessageDialog(null, attempts);
                    attempts++;
                    System.out.println(attempts);
                } else {
                    JOptionPane.showMessageDialog(null, attempts);
                    attempts++;
                    System.out.println(attempts);
                }
            } else if (e.getActionCommand().contains("< Atras")) {
                vMenu.cargarPanel(vWelcome);
            } else if (e.getActionCommand().contains("Registro")) {
                vMenu.cargarPanel(vRegistro);
            } else if (e.getActionCommand().contains("Registrar")) {
                String rUser = vRegistro.getTxtRegNombre().getText();
                String rPass = vRegistro.getTxtRegPass().getText();

                if (up.existUser(rUser, rPass) != null) {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe");
                } else if (up.existUser(rUser, rPass) == null) {
                    up.addUser(rUser, rPass);
                    JOptionPane.showMessageDialog(null, "Usuario creado");
                }

            }
        }

        if (e.getSource() instanceof JMenuItem) {
            if (e.getActionCommand().contains("Acceso")) {
                vMenu.cargarPanel(vLogin);
            } else if (e.getActionCommand().contains("Ver usuarios")) {
                vMenu.cargarPanel(vSeeUsers);
            }
        }

        if (e.getSource() instanceof JButton) {

        }
    }
}
