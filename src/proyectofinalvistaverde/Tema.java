package proyectofinalvistaverde;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.*;

/**
 *
 * 
 */
public class Tema {

    public static final Color VERDE_PRIMARIO   = new Color(39, 174, 96);
    public static final Color VERDE_OSCURO     = new Color(27, 120, 66);
    public static final Color VERDE_CLARO      = new Color(212, 239, 223);
    public static final Color AZUL_ACENTO      = new Color(52, 152, 219);
    public static final Color ROJO_ALERTA      = new Color(231, 76, 60);
    public static final Color FONDO_GENERAL    = new Color(245, 248, 250);
    public static final Color FONDO_TARJETA    = Color.WHITE;
    public static final Color TEXTO_PRINCIPAL  = new Color(30, 39, 46);
    public static final Color TEXTO_SECUNDARIO = new Color(99, 110, 114);
    public static final Color BORDE_SUAVE      = new Color(220, 230, 235);
    public static final Color FILA_PAR         = new Color(248, 252, 250);

    public static final Font FUENTE_TITULO   = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font FUENTE_SUBTIT   = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FUENTE_LABEL    = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_CAMPO    = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_BTN      = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FUENTE_TABLA    = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FUENTE_TABLA_H  = new Font("Segoe UI", Font.BOLD, 12);

    public static JButton btnPrimario(String texto) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color base = getModel().isPressed()  ? VERDE_OSCURO :
                             getModel().isRollover() ? new Color(46, 190, 110) : VERDE_PRIMARIO;
                g2.setColor(base);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(FUENTE_BTN);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 40));
        return btn;
    }

    public static JButton btnSecundario(String texto) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color base = getModel().isPressed()  ? BORDE_SUAVE :
                             getModel().isRollover() ? new Color(236, 240, 241) : Color.WHITE;
                g2.setColor(base);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(BORDE_SUAVE);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(FUENTE_BTN);
        btn.setForeground(TEXTO_SECUNDARIO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 38));
        return btn;
    }

    public static JTextField campo(int columnas) {
        JTextField tf = new JTextField(columnas);
        tf.setFont(FUENTE_CAMPO);
        tf.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(8, BORDE_SUAVE),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        tf.setBackground(Color.WHITE);
        return tf;
    }

    public static JPasswordField campoPassword(int columnas) {
        JPasswordField pf = new JPasswordField(columnas);
        pf.setFont(FUENTE_CAMPO);
        pf.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(8, BORDE_SUAVE),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        pf.setBackground(Color.WHITE);
        return pf;
    }

    public static <T> JComboBox<T> combo(T[] items) {
        JComboBox<T> cb = new JComboBox<>(items);
        cb.setFont(FUENTE_CAMPO);
        cb.setBackground(Color.WHITE);
        cb.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(8, BORDE_SUAVE),
            BorderFactory.createEmptyBorder(2, 4, 2, 4)
        ));
        return cb;
    }

    public static JPanel tarjeta() {
        JPanel p = new JPanel();
        p.setBackground(FONDO_TARJETA);
        p.setBorder(BorderFactory.createCompoundBorder(
            new ShadowBorder(),
            BorderFactory.createEmptyBorder(24, 30, 24, 30)
        ));
        return p;
    }

    public static JPanel header(String titulo, String subtitulo) {
        JPanel p = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, VERDE_PRIMARIO, getWidth(), getHeight(), VERDE_OSCURO);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        p.setLayout(new GridLayout(subtitulo != null ? 2 : 1, 1));
        p.setPreferredSize(new Dimension(0, subtitulo != null ? 90 : 65));

        JLabel lTit = new JLabel(titulo, SwingConstants.CENTER);
        lTit.setFont(FUENTE_TITULO);
        lTit.setForeground(Color.WHITE);
        p.add(lTit);

        if (subtitulo != null) {
            JLabel lSub = new JLabel(subtitulo, SwingConstants.CENTER);
            lSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lSub.setForeground(new Color(255, 255, 255, 200));
            p.add(lSub);
        }
        return p;
    }

    public static JLabel label(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(FUENTE_LABEL);
        l.setForeground(TEXTO_SECUNDARIO);
        return l;
    }

    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_TABLA);
        tabla.setRowHeight(32);
        tabla.setGridColor(BORDE_SUAVE);
        tabla.setBackground(Color.WHITE);
        tabla.setSelectionBackground(VERDE_CLARO);
        tabla.setSelectionForeground(TEXTO_PRINCIPAL);
        tabla.setShowVerticalLines(false);
        tabla.getTableHeader().setFont(FUENTE_TABLA_H);
        tabla.getTableHeader().setBackground(VERDE_PRIMARIO);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 36));
        tabla.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        tabla.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(
                    JTable t, Object v, boolean sel, boolean foc, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, v, sel, foc, row, col);
                if (!sel) c.setBackground(row % 2 == 0 ? Color.WHITE : FILA_PAR);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return c;
            }
        });
    }

    public static class RoundedBorder extends AbstractBorder {
        private final int radio;
        private final Color color;
        public RoundedBorder(int radio, Color color) { this.radio = radio; this.color = color; }
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(x, y, w-1, h-1, radio, radio);
            g2.dispose();
        }
        @Override public Insets getBorderInsets(Component c) { return new Insets(radio/2, radio/2, radio/2, radio/2); }
    }

    public static class ShadowBorder extends AbstractBorder {
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (int i = 0; i < 4; i++) {
                g2.setColor(new Color(0, 0, 0, 8 - i*2));
                g2.drawRoundRect(x+i, y+i, w-i*2-1, h-i*2-1, 12, 12);
            }
            g2.setColor(BORDE_SUAVE);
            g2.drawRoundRect(x, y, w-1, h-1, 12, 12);
            g2.dispose();
        }
        @Override public Insets getBorderInsets(Component c) { return new Insets(6, 6, 6, 6); }
    }
}
