import control.CLogin;
import gui.*;
import model.Usuario;
import persistencia.UsuarioPersistencia;

import javax.swing.*;
import java.awt.*;

public class Main {

    static UsuarioPersistencia datos;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VMenu vM = new VMenu();
            VLogin vL = new VLogin();
            VWelcome vW = new VWelcome();
            VRegistro vR = new VRegistro();
            datos = new UsuarioPersistencia();
            VSeeUsers vS = new VSeeUsers(datos);

            CLogin controlador = new CLogin(datos, vL, vM,vW, vS, vR);

            vL.setControlador(controlador);
            vS.setControlador(controlador);
            vM.setControlador(controlador);
            vS.setControlador(controlador);
            vR.setControlador(controlador);

            vM.setVisible(true);
            vM.setControlador(controlador);
            vM.cargarPanel(vW);

        });

    }

    public UsuarioPersistencia getUp() {
        return datos;
    }

}