/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinalvistaverde;
    
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author PC
 */
public class ProyectoFinalVistaVerde extends JFrame{
    private CardLayout cardLayout;
    private JPanel contenedorPrincipal;
    
    public ProyectoFinalVistaVerde() {
        setTitle("Sistema de Administración de Condominio Vista Verde");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
        cardLayout = new CardLayout();
        contenedorPrincipal = new JPanel(cardLayout);

        Login login = new Login(this);
        contenedorPrincipal.add(login, "Login");
        Inicio inicio = new Inicio(this);
        contenedorPrincipal.add(inicio, "Inicio");
        RegistroP registrop = new RegistroP(this);
        contenedorPrincipal.add(registrop, "Registro Propietarios");
        RegistroPC registropc = new RegistroPC(this);
        contenedorPrincipal.add(registropc, "Registro Pago Cuotas");
      add(contenedorPrincipal);
        setVisible(true);
    }
    
    public void cambiarPantalla(String nombrePantalla) {
        cardLayout.show(contenedorPrincipal, nombrePantalla);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProyectoFinalVistaVerde();
        });    // TODO code application logic here
    }
}
