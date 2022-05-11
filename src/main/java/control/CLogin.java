package control;

import gui.*;
import model.Usuario;
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
    private final static String MAXINTENTOS = "Has excedido el numero de intentos";
    private final static String USEREXISTE = "El usuario introducido ya existe";
    private final static String USERREGISTRAOD = "Usuario registrado correctamente";
    private final static String ERRRORPASSWORD = "Error en la contraseña";
    public CLogin(UsuarioPersistencia up, VLogin vLogin, VMenu vMenu, VWelcome vWelcome, VSeeUsers vSeeUsers,
                  VRegistro vRegistro) {
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
                Usuario userLogin = up.existUser(user, pass);

                if (userLogin != null) {
                    JOptionPane.showMessageDialog(null, "Bienvenido", "¡Oh yeah!",
                            JOptionPane.INFORMATION_MESSAGE);
                    attempts = 0;
                } else if (attempts > 3) {
                    JOptionPane.showMessageDialog(null, MAXINTENTOS,
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                } else if (up.existUser(user, pass) == null) {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos," +
                            " te quedan " + (3 - attempts) + " intentos", "Error", JOptionPane.ERROR_MESSAGE);
                    attempts++;
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos," +
                            " te quedan " + (3 - attempts) + " intentos", "Error", JOptionPane.ERROR_MESSAGE);
                    attempts++;
                }
            } else if (e.getActionCommand().contains("< Atras")) {
                vMenu.cargarPanel(vWelcome);
                vRegistro.borrarDatos();
            } else if (e.getActionCommand().contains("Registro")) {
                vMenu.cargarPanel(vRegistro);
            } else if (e.getActionCommand().contains("Registrar")) {

                String rUser = vRegistro.getTxtRegNombre().getText();
                String rPass = vRegistro.getTxtRegPass().getText();

                if (vRegistro.validarPassword(rPass, rUser) != "") {
                    JOptionPane.showMessageDialog(null, vRegistro.validarPassword(rPass, rUser),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (up.existUser(rUser, rPass) != null) {
                    JOptionPane.showMessageDialog(null, USEREXISTE,
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (vRegistro.validarPassword(rPass, rUser) == "") {
                    up.addUser(rUser, rPass);
                    vSeeUsers.updateTable();
                    JOptionPane.showMessageDialog(null, USERREGISTRAOD,
                            "¡Oh yeah!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, ERRRORPASSWORD);
                }

            } else if (e.getActionCommand().contains("Borrar")) {
                vRegistro.borrarDatos();
            }
        }

        if (e.getSource() instanceof JMenuItem) {
            if (e.getActionCommand().contains("Acceso")) {
                vMenu.cargarPanel(vLogin);
            } else if (e.getActionCommand().contains("Ver usuarios")) {
                vMenu.cargarPanel(vSeeUsers);
            }
        }

    }
}
