package com.github.alexeses.control;


import com.github.alexeses.gui.VLogin;
import com.github.alexeses.model.Usuario;
import com.github.alexeses.persistencia.UsuarioPersistencia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CLogin implements ActionListener {
    Usuario usuario;
    UsuarioPersistencia up;
    VLogin vLogin;

    public CLogin(UsuarioPersistencia up, VLogin vLogin) {
        this.up = up;
        this.vLogin = vLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            if (e.getActionCommand().contains("Login")){
                String user = vLogin.getTxtUser().getText();
                String pass = vLogin.getTxtPass().getText();

                System.out.println(user);
                System.out.println(pass);

                if (up.existUser(user, pass) != null){
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }

            }
        }

    }
}
