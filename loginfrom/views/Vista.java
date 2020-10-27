package loginfrom.views;

//(botones, cajas de texto etc)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import loginfrom.controllers.Controlador;

public class Vista extends JFrame {

    Controlador controller = new Controlador();

    JPanel panel;
    JLabel nombre;
    JLabel contraseña;
    static JLabel mensaje;
    JTextField txtname;
    JPasswordField txtpassword;
    JButton inicioSesion;
    JPanel panelreg;
    JLabel nombreR;
    JLabel contraseñaR;
    static JLabel mensajeR;
    JTextField txtnamereg;
    JPasswordField txtpasswordreg;
    JButton crear;

    public Vista() {

        setDefaultCloseOperation(0);
        setUndecorated(true);

        setTitle("Muñoz Torres Ivan Antonio/ITL Login");
        setSize(660, 260);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container content = getContentPane();
        JDesktopPane des = new JDesktopPane();

        //Login form
        JInternalFrame flog = new JInternalFrame();
        flog.setSize(300, 200);
        flog.setLocation(10, 2);

        flog.setTitle("Iniciar sesion ");
        nombre = new JLabel("Nombre de usuario:");
         contraseña = new JLabel("Contraseña:");
        mensaje = new JLabel("");
        inicioSesion = new JButton("Iniciar sesion");

        inicioSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.inicioDeSesion(e, txtnamereg.getText(), txtpasswordreg.getText());
            }

        });
        txtname = new JTextField(20);
        txtpassword = new JPasswordField(20);
        panel = new JPanel();
        panel.add(nombre);
        panel.add(txtname);
        panel.add(contraseña);
        panel.add(txtpassword);
        panel.add(inicioSesion);
        panel.add(mensaje);
        flog.add(panel);
        flog.setVisible(true);

      
        JInternalFrame freg = new JInternalFrame();
        freg.setSize(300, 200);
        freg.setLocation(315, 2);
        freg.setTitle("Registro");
        nombreR = new JLabel("Nombre de usuario:");
        contraseñaR = new JLabel("Contraseña:");
        mensajeR= new JLabel("");
        crear = new JButton("Guardar");

       crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.CrearCuenta(e, txtnamereg.getText(), txtpasswordreg.getText());
            }

        });

        txtnamereg = new JTextField(20);
        txtpasswordreg = new JPasswordField(20);
        txtpasswordreg.addKeyListener(new Vista.KeyList());
        panelreg = new JPanel();
        panelreg.add(nombreR);
        panelreg.add(txtnamereg);
        panelreg.add(contraseñaR);
        panelreg.add(txtpasswordreg);
        panelreg.add(crear);
        panelreg.add(mensajeR);
        freg.add(panelreg);
        freg.setVisible(true);
        des.add(flog);
        des.add(freg);
        content.add(des, BorderLayout.CENTER);
        setVisible(true);
        txtname.requestFocus();

    }

    public class KeyList extends KeyAdapter {

        public void keyPressed(KeyEvent ke) {
            String passw = new String(txtpasswordreg.getPassword());
            String mess = checkStrength(passw);
            showMess(mess + " contraseña", contraseñaR);
        }

    }

    public void keyPressed(KeyEvent ke) {
        String passw = new String(txtpasswordreg.getPassword());
        String mess = checkStrength(passw);
        showMess(mess + " contraseña", contraseñaR);
    }

    public String checkStrength(String contra) {
        Pattern pat = Pattern.compile("([0-9][aA-zZ]|[aA-zZ][0-9])");
        Matcher mat = pat.matcher(contra);
        if (mat.find()) {
            if (contra.length() >= 8) {
                return "Fuerte";
            } else {
                return "Media";
            }
        } else {
            return "debil";
        }

    }

    public void showMess(String mess, JLabel lbl) {
        lbl.setText(mess);
    }

    public static void showAlert1() {

       mensaje.setText("usuario no encontrado");

    }

    public static void showAlert2() {

        mensaje.setText("Usuario repetido");

    }
}
