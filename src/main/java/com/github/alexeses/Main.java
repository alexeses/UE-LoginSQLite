package com.github.alexeses;

import com.github.alexeses.control.CLogin;
import com.github.alexeses.gui.VLogin;
import com.github.alexeses.persistencia.UsuarioPersistencia;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VLogin vL = new VLogin();

            UsuarioPersistencia datos = new UsuarioPersistencia();
            CLogin controlador = new CLogin(datos, vL);
            vL.setControlador(controlador);

            vL.hacerVisible();
        });
    }
}