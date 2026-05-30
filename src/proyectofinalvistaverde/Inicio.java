package proyectofinalvistaverde;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * 
 * 
 */
public class Inicio extends JPanel {

    private ProyectoFinalVistaVerde framePrincipal;

    public Inicio(ProyectoFinalVistaVerde frame) {
        this.framePrincipal = frame;
        setBackground(Tema.FONDO_GENERAL);
        setLayout(new BorderLayout());
        construirUI();
    }

    private void construirUI() {
        LocalDate hoy = LocalDate.now();
        String mes = hoy.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String subtitulo = "Bienvenido, Administrador  ·  " + capitalize(mes) + " " + hoy.getYear();

        JPanel header = Tema.header("Condominio Vista Verde", subtitulo);
        add(header, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(2, 3, 20, 20));
        grid.setBackground(Tema.FONDO_GENERAL);
        grid.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        grid.add(modulo("", "Registro de Propietarios",  Tema.VERDE_PRIMARIO, "Registro Propietarios"));
        grid.add(modulo("", "Registro de Pagos",          new Color(52, 152, 219), "Registro Pagos"));
        grid.add(modulo("", "Configuración de Cuota",      new Color(155, 89, 182), "Configuracion Cuenta"));
        grid.add(modulo("", "Estado de Cuenta",           new Color(230, 126, 34), "Estado Cuenta"));
        grid.add(modulo("", "Reporte General",            new Color(22, 160, 133), "Reporte General"));
        grid.add(modulo("", "Casas Morosas",              Tema.ROJO_ALERTA,        "Casas Morosas"));

        add(grid, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Tema.FONDO_GENERAL);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Tema.BORDE_SUAVE));
        JLabel lbl = new JLabel("Sistema de Administración Vista Verde  · v1.0   ");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(Tema.TEXTO_SECUNDARIO);
        footer.add(lbl);
        add(footer, BorderLayout.SOUTH);
    }

    private JPanel modulo(String icono, String titulo, Color color, String pantalla) {
        JPanel card = new JPanel(new GridBagLayout()) {
            private boolean hovered = false;
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent e) { hovered = true;  repaint(); }
                    public void mouseExited(java.awt.event.MouseEvent e)  { hovered = false; repaint(); }
                    public void mouseClicked(java.awt.event.MouseEvent e) { framePrincipal.cambiarPantalla(pantalla); }
                });
            }
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(hovered ? color : Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                if (!hovered) {
                    g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 30));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                }
                for (int i = 0; i < 3; i++) {
                    g2.setColor(new Color(0, 0, 0, 6));
                    g2.drawRoundRect(i, i, getWidth()-i*2-1, getHeight()-i*2-1, 16, 16);
                }
                g2.dispose();
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;

        JLabel lblIcono = new JLabel(icono, SwingConstants.CENTER);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 38));
        card.add(lblIcono, gbc);

        String[] lineas = titulo.split("\n");
        gbc.gridy = 1; gbc.insets = new Insets(8, 10, 0, 10);
        JLabel lbl1 = new JLabel(lineas[0], SwingConstants.CENTER);
        lbl1.setFont(Tema.FUENTE_SUBTIT);
        lbl1.setForeground(Tema.TEXTO_PRINCIPAL);
        card.add(lbl1, gbc);

        if (lineas.length > 1) {
            gbc.gridy = 2; gbc.insets = new Insets(2, 10, 0, 10);
            JLabel lbl2 = new JLabel(lineas[1], SwingConstants.CENTER);
            lbl2.setFont(Tema.FUENTE_SUBTIT);
            lbl2.setForeground(Tema.TEXTO_PRINCIPAL);
            card.add(lbl2, gbc);
        }

        JPanel barra = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                g.setColor(color);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
            }
        };
        barra.setPreferredSize(new Dimension(0, 5));
        card.setLayout(new BorderLayout());
        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        contenido.add(lblIcono, c);
        c.gridy = 1; c.insets = new Insets(8,10,0,10);
        contenido.add(lbl1, c);
        if (lineas.length > 1) {
            c.gridy = 2; c.insets = new Insets(2,10,6,10);
            JLabel lbl2b = new JLabel(lineas[1], SwingConstants.CENTER);
            lbl2b.setFont(Tema.FUENTE_SUBTIT);
            lbl2b.setForeground(Tema.TEXTO_PRINCIPAL);
            contenido.add(lbl2b, c);
        }
        card.add(contenido, BorderLayout.CENTER);
        card.add(barra, BorderLayout.SOUTH);

        return card;
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,400,Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,300,Short.MAX_VALUE));
    }
}
