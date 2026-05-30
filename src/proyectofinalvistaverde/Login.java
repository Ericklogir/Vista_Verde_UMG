package proyectofinalvistaverde;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JPanel {

    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private ProyectoFinalVistaVerde framePrincipal;

    public Login(ProyectoFinalVistaVerde frame) {

        this.framePrincipal = frame;

        setLayout(new BorderLayout());
        setBackground(Tema.FONDO_GENERAL);

        construirUI();
    }

    private void construirUI() {

        JPanel panelIzq = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                GradientPaint gp = new GradientPaint(
                        0,
                        0,
                        Tema.VERDE_PRIMARIO,
                        getWidth(),
                        getHeight(),
                        Tema.VERDE_OSCURO
                );

                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(new Color(255, 255, 255, 20));

                g2.fillOval(-60, -60, 300, 300);

                g2.fillOval(
                        getWidth() - 150,
                        getHeight() - 150,
                        300,
                        300
                );

                g2.dispose();
            }
        };

        panelIzq.setPreferredSize(new Dimension(340, 0));
        panelIzq.setLayout(new GridBagLayout());

        JLabel lblIcono = new JLabel("🏘", SwingConstants.CENTER);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 72));

        JLabel lblNombre = new JLabel(
                "<html><div style='text-align:center'>CONDOMINIO<br>VISTA VERDE</div></html>",
                SwingConstants.CENTER
        );

        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblNombre.setForeground(Color.WHITE);

        JLabel lblSlogan = new JLabel(
                "Sistema de Administración",
                SwingConstants.CENTER
        );

        lblSlogan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSlogan.setForeground(new Color(255, 255, 255, 180));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 20, 8, 20);

        panelIzq.add(lblIcono, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 6, 20);

        panelIzq.add(lblNombre, gbc);

        gbc.gridy = 2;

        panelIzq.add(lblSlogan, gbc);

        JPanel panelDer = new JPanel(new GridBagLayout());

        panelDer.setBackground(Tema.FONDO_GENERAL);

        JPanel tarjeta = Tema.tarjeta();

        tarjeta.setLayout(new GridBagLayout());
        tarjeta.setPreferredSize(new Dimension(360, 380));

        GridBagConstraints f = new GridBagConstraints();

        f.fill = GridBagConstraints.HORIZONTAL;
        f.insets = new Insets(6, 0, 6, 0);

        JLabel lblTit = new JLabel("Iniciar Sesión");

        lblTit.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTit.setForeground(Tema.TEXTO_PRINCIPAL);

        f.gridx = 0;
        f.gridy = 0;
        f.gridwidth = 1;
        f.insets = new Insets(0, 0, 20, 0);

        tarjeta.add(lblTit, f);

        f.gridy = 1;
        f.insets = new Insets(4, 0, 2, 0);

        tarjeta.add(Tema.label("Usuario"), f);

        txtUsuario = Tema.campo(22);

        f.gridy = 2;
        f.insets = new Insets(2, 0, 10, 0);

        tarjeta.add(txtUsuario, f);

        f.gridy = 3;
        f.insets = new Insets(4, 0, 2, 0);

        tarjeta.add(Tema.label("Contraseña"), f);

        txtContraseña = Tema.campoPassword(22);

        f.gridy = 4;
        f.insets = new Insets(2, 0, 20, 0);

        tarjeta.add(txtContraseña, f);

        JButton btnIngresar =
                Tema.btnPrimario("Ingresar al Sistema");

        btnIngresar.setPreferredSize(
                new Dimension(300, 44)
        );

        f.gridy = 5;
        f.insets = new Insets(4, 0, 4, 0);

        tarjeta.add(btnIngresar, f);

        JLabel lblError = new JLabel(" ");

        lblError.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        lblError.setForeground(Tema.ROJO_ALERTA);

        lblError.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        f.gridy = 6;

        tarjeta.add(lblError, f);

        panelDer.add(tarjeta);

        // FUNCION DEL BOTON INGRESAR CON BASE DE DATOS LOGIN
        btnIngresar.addActionListener(e -> {

            String usuario =
                    txtUsuario.getText().trim();

            String contrasena =
                    new String(
                            txtContraseña.getPassword()
                    ).trim();

            if (usuario.isEmpty()
                    || contrasena.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Complete todos los campos"
                );

                return;
            }

            try (Connection con =
                         Conexion.getConexion()) {

                if (con == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo conectar a la base de datos"
                    );

                    return;
                }

                String sql =
                        "SELECT * FROM login " +
                        "WHERE usuario = ? " +
                        "AND contrasena = ?";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(1, usuario);
                ps.setString(2, contrasena);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Bienvenido al sistema"
                    );

                    framePrincipal.cambiarPantalla(
                            "Inicio"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Usuario o contraseña incorrectos",
                            "Error de autenticación",
                            JOptionPane.ERROR_MESSAGE
                    );

                    txtContraseña.setText("");
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error en base de datos:\n"
                                + ex.getMessage()
                );
            }
        });

        // ESTO ERA LO QUE FALTABA
        add(panelIzq, BorderLayout.WEST);
        add(panelDer, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(this);

        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                ).addGap(0, 400, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                ).addGap(0, 300, Short.MAX_VALUE)
        );
    }
}