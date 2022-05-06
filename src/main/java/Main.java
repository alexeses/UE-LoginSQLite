import control.CLogin;
import gui.VLogin;
import persistencia.UsuarioPersistencia;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VLogin vL = new VLogin();

                UsuarioPersistencia datos = new UsuarioPersistencia();
                CLogin controlador = new CLogin(datos, vL);
                vL.setControlador(controlador);

                vL.hacerVisible();
            }
        });
    }
}