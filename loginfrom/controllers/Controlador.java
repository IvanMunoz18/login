package loginfrom.controllers;
//logica del programa(metodos, accion de botones, listeners)

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import loginfrom.models.Model;
import loginfrom.views.Vista;


public class Controlador extends KeyAdapter implements ActionListener {
    
    Model model = new Model();

    public void login(String texto) {
        System.out.println(texto);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

       
    }

    public void inicioDeSesion(ActionEvent e, String Uname, String contraseña) {
        String uname = Uname;
            String passw = contraseña;
            if (!model.checkBlank(uname, passw)) {
                model.validar("accounts.txt", uname, passw);
            }else {
                    Vista.showAlert1();
                }
    }
    
     public void CrearCuenta(ActionEvent e, String Uname, String contraseña) {
       String uname = Uname;
            String passw = contraseña;
            if (!model.checkBlank(uname, passw)) {
                if (!model.comparar("accounts.txt", uname)) {
                    passw = new String(model.encriptado(passw));
                    String accinfo = uname + "-" + passw;
                    model.GuardarArchivo("accounts.txt", accinfo);
                }else {
                    Vista.showAlert2();
                }
            }else{
                Vista.showAlert2();
            }
    }
}
