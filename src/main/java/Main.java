import control.CLogin;
import gui.VLogin;
import gui.VSeeUsers;
import persistencia.UsuarioPersistencia;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VLogin vL = new VLogin();
            VSeeUsers vS = new VSeeUsers();

            UsuarioPersistencia datos = new UsuarioPersistencia();
            CLogin controlador = new CLogin(datos, vL);

            vL.setControlador(controlador);
            vS.setControlador(controlador);

            vL.hacerVisible();
        });
    }
}