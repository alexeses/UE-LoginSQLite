import control.CLogin;
import gui.VLogin;
import gui.VMenu;
import gui.VSeeUsers;
import gui.VWelcome;
import persistencia.UsuarioPersistencia;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VMenu vM = new VMenu();
            VLogin vL = new VLogin();
            VWelcome vW = new VWelcome();
            VSeeUsers vS = new VSeeUsers();

            UsuarioPersistencia datos = new UsuarioPersistencia();
            CLogin controlador = new CLogin(datos, vL, vM,vW);

            vL.setControlador(controlador);
            vS.setControlador(controlador);
            vM.setControlador(controlador);


            vM.setVisible(true);
            vM.setControlador(controlador);
            vM.cargarPanel(vW);
        });
    }
}