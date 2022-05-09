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
    final String campoVacio = "No puedes dejar ningún campo vacío";
    final String campo20Car = "El campo debe tener menos de 20 caracteres";
    final String campoUNumber = "El usuario no puede estar formado por números";
    final String campoIqual = "El nombre de usuario y contraseña no pueden ser iguales";
    final String campo8Car = "El nombre de usuario y contraseña no puede tener menos de 8 caracteres";
    final String compoMayus = "La contraseña debe contener una letra mayúscula";
    final String compoNumber = "La contraseña debe contener un número";
    final String compoLetters = "La contraseña debe contener una letra y un número";


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

                if (up.existUser(user, pass) != null) {
                    JOptionPane.showMessageDialog(null, "Bienvenido", "¡Oh yeah!",
                            JOptionPane.INFORMATION_MESSAGE);
                    attempts = 0;
                } else if (attempts > 3) {
                    JOptionPane.showMessageDialog(null, "Has excedido el numero de intentos",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                } else if (up.existUser(user, pass) == null) {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos," +
                            " te quedan " + (3 - attempts) + " intentos", "Error", JOptionPane.ERROR_MESSAGE);
                    attempts++;
                    System.out.println(attempts);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos," +
                            " te quedan " + (3 - attempts) + " intentos", "Error", JOptionPane.ERROR_MESSAGE);
                    attempts++;
                    System.out.println(attempts);
                }
            } else if (e.getActionCommand().contains("< Atras")) {
                vMenu.cargarPanel(vWelcome);
                vRegistro.borrarDatos();
            } else if (e.getActionCommand().contains("Registro")) {
                vMenu.cargarPanel(vRegistro);
            } else if (e.getActionCommand().contains("Registrar")) {

                if (vRegistro.getTxtRegNombre().getText().isEmpty() || vRegistro.getTxtRegPass().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, campoVacio, "Error", JOptionPane.ERROR_MESSAGE);
                } else if (vRegistro.getTxtRegNombre().getText().length() > 20 || vRegistro.getTxtRegPass().getText().length() > 20) {
                    JOptionPane.showMessageDialog(null, campo20Car);
                } else if (vRegistro.getTxtRegNombre().getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, campoUNumber);
                } else if (vRegistro.getTxtRegNombre().getText().equals(vRegistro.getTxtRegPass().getText())) {
                    JOptionPane.showMessageDialog(null, campoIqual);
                } else if (vRegistro.getTxtRegNombre().getText().length() < 2 || vRegistro.getTxtRegPass().getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, campo8Car);
                } else if (!vRegistro.getTxtRegPass().getText().equals("[A-Za-z0-9]+")) {
                    JOptionPane.showMessageDialog(null, compoLetters);
                } else {
                    String rUser = vRegistro.getTxtRegNombre().getText();
                    String rPass = vRegistro.getTxtRegPass().getText();

                    if (up.existUser(rUser, rPass) != null) {
                        JOptionPane.showMessageDialog(null, "El usuario ya existe");
                        vRegistro.borrarDatos();
                    } else if (up.existUser(rUser, rPass) == null) {
                        up.addUser(rUser, rPass);
                        JOptionPane.showMessageDialog(null, "Usuario creado");
                        vRegistro.borrarDatos();
                    }

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
