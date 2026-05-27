package proyectofinalvistaverde;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ConfiguracionC extends JPanel {

    private ProyectoFinalVistaVerde framePrincipal;

    private JTextField txtNuevaCuota;

    private JLabel lblCuotaActual;

    public ConfiguracionC(ProyectoFinalVistaVerde frame) {

        this.framePrincipal = frame;

        setBackground(Tema.FONDO_GENERAL);

        setLayout(new BorderLayout());

        construirUI();

        cargarCuota();
    }

    private void construirUI() {

        add(
                Tema.header(
                        "Configuración de Cuota",
                        "Modifique el monto de mantenimiento mensual"
                ),
                BorderLayout.NORTH
        );

        JPanel centro =
                new JPanel(new GridBagLayout());

        centro.setBackground(Tema.FONDO_GENERAL);

        JPanel tarjeta = Tema.tarjeta();

        tarjeta.setLayout(new GridBagLayout());

        tarjeta.setPreferredSize(
                new Dimension(400, 280)
        );

        GridBagConstraints f =
                new GridBagConstraints();

        f.fill = GridBagConstraints.HORIZONTAL;

        f.weightx = 1;

        // =========================
        // PANEL INFORMACION
        // =========================
        JPanel infoPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        infoPanel.setOpaque(false);

        JLabel lblInfo =
                new JLabel("Cuota actual:  ");

        lblInfo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        lblInfo.setForeground(
                Tema.TEXTO_SECUNDARIO
        );

        lblCuotaActual = new JLabel();

        lblCuotaActual.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        lblCuotaActual.setForeground(
                Tema.VERDE_PRIMARIO
        );

        infoPanel.add(lblInfo);

        infoPanel.add(lblCuotaActual);

        f.gridx = 0;
        f.gridy = 0;
        f.insets = new Insets(0,0,20,0);

        tarjeta.add(infoPanel, f);

        // =========================
        // SEPARADOR
        // =========================
        JSeparator sep = new JSeparator();

        sep.setForeground(Tema.BORDE_SUAVE);

        f.gridy = 1;

        f.insets = new Insets(0,0,20,0);

        tarjeta.add(sep, f);

        // =========================
        // LABEL
        // =========================
        f.gridy = 2;

        f.insets = new Insets(0,0,4,0);

        tarjeta.add(
                Tema.label(
                        "Nuevo monto de cuota (Q)"
                ),
                f
        );

        // =========================
        // CAMPO
        // =========================
        txtNuevaCuota = Tema.campo(20);

        f.gridy = 3;

        f.insets = new Insets(0,0,6,0);

        tarjeta.add(txtNuevaCuota, f);

        // =========================
        // NOTA
        // =========================
        JLabel nota =
                new JLabel(
                        "* El nuevo monto aplica para pagos registrados a partir de este momento."
                );

        nota.setFont(
                new Font(
                        "Segoe UI",
                        Font.ITALIC,
                        11
                )
        );

        nota.setForeground(
                Tema.TEXTO_SECUNDARIO
        );

        f.gridy = 4;

        f.insets = new Insets(0,0,22,0);

        tarjeta.add(nota, f);

        // =========================
        // BOTONES
        // =========================
        JPanel panelBtns =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                12,
                                0
                        )
                );

        panelBtns.setOpaque(false);

        JButton btnGuardar =
                Tema.btnPrimario(
                        "  Actualizar Monto"
                );

        JButton btnVolver =
                Tema.btnSecundario(
                        " Volver al Inicio"
                );

        panelBtns.add(btnGuardar);

        panelBtns.add(btnVolver);

        f.gridy = 5;

        f.insets = new Insets(0,0,0,0);

        tarjeta.add(panelBtns, f);

        centro.add(tarjeta);

        add(centro, BorderLayout.CENTER);

        // =========================
        // EVENTOS
        // =========================
        btnVolver.addActionListener(e ->
                framePrincipal.cambiarPantalla(
                        "Inicio"
                )
        );

        btnGuardar.addActionListener(e ->
                actualizarCuota()
        );
    }

    // =========================
    // CARGAR CUOTA
    // =========================
    private void cargarCuota() {

        try(Connection con =
                    Conexion.getConexion()) {

            String sql =
                    "SELECT cuota_mensual " +
                    "FROM configuracion " +
                    "LIMIT 1";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                double cuota =
                        rs.getDouble(
                                "cuota_mensual"
                        );

                lblCuotaActual.setText(
                        String.format(
                                "Q %,.2f",
                                cuota
                        )
                );

                txtNuevaCuota.setText(
                        String.valueOf(cuota)
                );
            }

        } catch(SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando cuota:\n"
                            + ex.getMessage()
            );
        }
    }

    // =========================
    // ACTUALIZAR CUOTA
    // =========================
    private void actualizarCuota() {

        try {

            double nuevo =
                    Double.parseDouble(
                            txtNuevaCuota
                                    .getText()
                                    .replace(",", ".")
                    );

            try(Connection con =
                        Conexion.getConexion()) {

                String sql =
                        "UPDATE configuracion " +
                        "SET cuota_mensual = ? " +
                        "WHERE id_config = 1";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setDouble(1, nuevo);

                ps.executeUpdate();

                lblCuotaActual.setText(
                        String.format(
                                "Q %,.2f",
                                nuevo
                        )
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Cuota actualizada correctamente",
                        "Actualización exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch(NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un monto válido",
                    "Valor inválido",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch(SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error:\n" + ex.getMessage()
            );
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(this);

        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                ).addGap(0,400,Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                ).addGap(0,300,Short.MAX_VALUE)
        );
    }
}