
package loginfrom.models;

//todo lo relacionado a base de datos (get, set, update

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Model {
    
public boolean comparar(String nombreArchivo, String nombre) {
        FileReader fr;
        BufferedReader br;
        String accinfo;
        boolean exist = false;
        try {
            fr = new FileReader(nombreArchivo);
            br = new BufferedReader(fr);
            while ((accinfo = br.readLine()) != null) {

                if (checar(accinfo, nombre)) {
                    System.out.println("Esta cuenta ya existe");
                    exist = true;
                    break;
                }else{
                    
                
            }

            }

            br.close();
            fr.close();
        } catch (Exception ie) {
            System.out.println("Error!");
        }
        return exist;
    }

    public boolean checar(String accinfo, String nombre) {
        String[] info = accinfo.split("-");
        String uname = info[0];
        if (uname.equals(nombre)) {
            return true;
        } else {
            return false;
        }

    }
    
     public void GuardarArchivo(String nombreArchivo, String texto) {
        try {
            FileWriter fw = new FileWriter(nombreArchivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException ie) {
            System.out.println("Error en el archivo");
        }
    }
     
     public byte[] encriptado(String contraseña) {
        byte[] b = contraseña.getBytes();
        int i;
        for (i = 0; i < b.length; i++) {
            b[i] = (byte) (b[i] + 5);
        }

        return (b);
    }

    public byte[] desencriptado(String contraseña) {

        byte[] b = contraseña.getBytes();
        int i;
        for (i = 0; i < b.length; i++) {
            b[i] = (byte) (b[i] - 5);
        }

        return (b);
    }
    
    
      public boolean checkBlank(String nombre, String contraseña) {
        boolean hasBlank = false;
        if (nombre.length() < 1) {
            hasBlank = true;
        }
        if (contraseña.length() < 1) {
            hasBlank = true;
        }
        return hasBlank;

    }
      
      public void validar(String nombreArchivo, String nombre, String contraseña) {
        FileReader fr;
        BufferedReader br;
        boolean valid = false;
        String accinfo;
        try {

            fr = new FileReader(nombreArchivo);
            br = new BufferedReader(fr);
            while ((accinfo = br.readLine()) != null) {

                if (check(accinfo, nombre, contraseña)) {
                    valid = true;
                    JOptionPane.showMessageDialog(null, "Accediste");
                    System.exit(0);
                    break;
                    
                }

            }

            if (!valid) {
            }
            br.close();
            fr.close();
        } catch (Exception ie) {
            System.out.println("Error!");
        }

    }
      public boolean check(String Ainfo, String nombre, String contraseña) {
        String[] info = Ainfo.split("-");
        String uname = info[0];
        String pass = new String(desencriptado(info[1]));
        if (uname.equals(nombre) && pass.equals(contraseña)) {
            return true;
        } else {
            return false;
        }

    }
}
