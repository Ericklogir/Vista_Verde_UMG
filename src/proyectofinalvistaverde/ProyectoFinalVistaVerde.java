package proyectofinalvistaverde;

import javax.swing.*;
import java.awt.*;


public class ProyectoFinalVistaVerde extends JFrame {

    private CardLayout cardLayout;
    private JPanel contenedorPrincipal;

    public ProyectoFinalVistaVerde() {
        setTitle("Sistema de Administración — Condominio Vista Verde");
        setSize(1000, 700);
        setMinimumSize(new Dimension(800, 560));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        cardLayout = new CardLayout();
        contenedorPrincipal = new JPanel(cardLayout);

        contenedorPrincipal.add(new Login(this),         "Login");
        contenedorPrincipal.add(new Inicio(this),        "Inicio");
        contenedorPrincipal.add(new RegistroP(this),     "Registro Propietarios");
        contenedorPrincipal.add(new RegistroPC(this),    "Registro Pagos");
        contenedorPrincipal.add(new ConfiguracionC(this),"Configuracion Cuenta");
        contenedorPrincipal.add(new EstadoCC(this),      "Estado Cuenta");
        contenedorPrincipal.add(new ReporteG(this),      "Reporte General");
        contenedorPrincipal.add(new CasasM(this),        "Casas Morosas");

        add(contenedorPrincipal);
        setVisible(true);
    }

   public void cambiarPantalla(String nombre) {

    Component[] componentes =
            contenedorPrincipal.getComponents();

    for(Component c : componentes) {

        if(c instanceof RegistroPC
                && nombre.equals("Registro Pagos")) {

            ((RegistroPC) c)
                    .actualizarCuotaVista();
        }
    }

    cardLayout.show(
            contenedorPrincipal,
            nombre
    );
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProyectoFinalVistaVerde::new);
    }
}
