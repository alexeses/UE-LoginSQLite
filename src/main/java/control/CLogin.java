package control;


import gui.VLogin;
import gui.VMenu;
import gui.VWelcome;
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

    public CLogin(UsuarioPersistencia up, VLogin vLogin, VMenu vMenu, VWelcome vWelcome) {
        this.up = up;
        this.vLogin = vLogin;
        this.vMenu = vMenu;
        this.vWelcome = vWelcome;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            if (e.getActionCommand().contains("Login")){
                String user = vLogin.getTxtUser().getText();
                String pass = vLogin.getTxtPass().getText();

                if (up.existUser(user, pass) != null){
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        }

        if (e.getSource() instanceof JMenuItem){
            if (e.getActionCommand().contains("Login")){
                vMenu.cargarPanel(vLogin);
            }
        }

    }
}
