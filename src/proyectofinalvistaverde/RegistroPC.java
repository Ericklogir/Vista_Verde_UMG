package proyectofinalvistaverde;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class RegistroPC extends JPanel {

    private ProyectoFinalVistaVerde framePrincipal;

    private JComboBox<Integer> cbCasas;

    private JComboBox<String> cbMeses;

    private JTextField txtAnio;

    private JTextField txtMonto;

    private JTable tabla;

    private DefaultTableModel modelo;

    public RegistroPC(ProyectoFinalVistaVerde frame) {

        this.framePrincipal = frame;

        setBackground(Tema.FONDO_GENERAL);

        setLayout(new BorderLayout());

        construirUI();

        cargarCuota();

        cargarPagos();
    }

    private void construirUI() {

        add(
                Tema.header(
                        "Registro de Pago de Cuota",
                        "Seleccione la casa"
                ),
                BorderLayout.NORTH
        );

        JPanel centro =
                new JPanel(new BorderLayout(20,0));

        centro.setBackground(Tema.FONDO_GENERAL);

        centro.setBorder(
                BorderFactory.createEmptyBorder(
                        20,20,20,20
                )
        );

        // =========================
        // FORMULARIO
        // =========================
        JPanel panelFormulario =
                new JPanel(new GridBagLayout());

        panelFormulario.setOpaque(false);

        JPanel tarjeta = Tema.tarjeta();

        tarjeta.setLayout(new GridBagLayout());

        tarjeta.setPreferredSize(
                new Dimension(420,420)
        );

        GridBagConstraints f =
                new GridBagConstraints();

        f.fill = GridBagConstraints.HORIZONTAL;

        f.anchor = GridBagConstraints.WEST;

        f.weightx = 1;

        // CASA
        f.gridx = 0;
        f.gridy = 0;
        f.insets = new Insets(0,0,2,0);

        tarjeta.add(
                Tema.label("Número de Casa"),
                f
        );

        Integer[] casas = new Integer[30];

        for(int i = 0; i < 30; i++) {

            casas[i] = i + 1;
        }

        cbCasas = Tema.combo(casas);

        f.gridy = 1;

        f.insets = new Insets(0,0,14,0);

        tarjeta.add(cbCasas, f);

        // MES
        f.gridy = 2;

        f.insets = new Insets(0,0,2,0);

        tarjeta.add(
                Tema.label("Mes a Pagar"),
                f
        );

        String[] meses = {

                "Enero",
                "Febrero",
                "Marzo",
                "Abril",
                "Mayo",
                "Junio",
                "Julio",
                "Agosto",
                "Septiembre",
                "Octubre",
                "Noviembre",
                "Diciembre"
        };

        cbMeses = Tema.combo(meses);

        f.gridy = 3;

        f.insets = new Insets(0,0,14,0);

        tarjeta.add(cbMeses, f);

        // AÑO
        f.gridy = 4;

        f.insets = new Insets(0,0,2,0);

        tarjeta.add(
                Tema.label("Año"),
                f
        );

        txtAnio = Tema.campo(10);

        txtAnio.setText(
                String.valueOf(
                        LocalDate.now().getYear()
                )
        );

        f.gridy = 5;

        f.insets = new Insets(0,0,14,0);

        tarjeta.add(txtAnio, f);

        // MONTO
        f.gridy = 6;

        f.insets = new Insets(0,0,2,0);

        tarjeta.add(
                Tema.label("Monto de Cuota (Q)"),
                f
        );

        txtMonto = Tema.campo(10);

        txtMonto.setEditable(false);

        txtMonto.setBackground(
                new Color(245,248,250)
        );

        txtMonto.setForeground(
                Tema.VERDE_OSCURO
        );

        txtMonto.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        f.gridy = 7;

        f.insets = new Insets(0,0,22,0);

        tarjeta.add(txtMonto, f);

        // =========================
        // BOTONES
        // =========================
        JPanel panelBtns =
                new JPanel(
                        new GridLayout(1,2,12,0)
                );

        panelBtns.setOpaque(false);

        JButton btnPagar =
                Tema.btnPrimario(
                        " Registrar Pago"
                );

        JButton btnVolver =
                Tema.btnSecundario(
                        " Volver al Inicio"
                );

        panelBtns.add(btnPagar);

        panelBtns.add(btnVolver);

        f.gridy = 8;

        tarjeta.add(panelBtns, f);

        panelFormulario.add(tarjeta);

        // =========================
        // TABLA
        // =========================
        JPanel panelTabla = Tema.tarjeta();

        panelTabla.setLayout(new BorderLayout());

        JLabel lblTabla =
                Tema.label("Historial de Pagos");

        lblTabla.setBorder(
                BorderFactory.createEmptyBorder(
                        10,10,10,10
                )
        );

        panelTabla.add(
                lblTabla,
                BorderLayout.NORTH
        );

        modelo = new DefaultTableModel();

        modelo.addColumn("Casa");
        modelo.addColumn("Mes");
        modelo.addColumn("Año");
        modelo.addColumn("Monto");
        modelo.addColumn("Estado");

        tabla = new JTable(modelo);

        // ESTILO TABLA
        tabla.setRowHeight(32);

        tabla.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tabla.setSelectionBackground(
                new Color(200,230,201)
        );

        tabla.setSelectionForeground(
                Color.BLACK
        );

        tabla.setGridColor(
                new Color(230,230,230)
        );

        tabla.setShowVerticalLines(false);

        tabla.setIntercellSpacing(
                new Dimension(0,1)
        );

        tabla.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        tabla.getTableHeader().setBackground(
                Tema.VERDE_PRIMARIO
        );

        tabla.getTableHeader().setForeground(
                Color.WHITE
        );

        tabla.getTableHeader().setOpaque(false);

        tabla.getTableHeader()
                .setReorderingAllowed(false);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scroll.getViewport()
                .setBackground(Color.WHITE);

        panelTabla.add(
                scroll,
                BorderLayout.CENTER
        );

        // =========================
        // AGREGAR
        // =========================
        centro.add(
                panelFormulario,
                BorderLayout.WEST
        );

        centro.add(
                panelTabla,
                BorderLayout.CENTER
        );

        add(centro, BorderLayout.CENTER);

        // =========================
        // EVENTOS
        // =========================
        btnVolver.addActionListener(e ->
                framePrincipal.cambiarPantalla(
                        "Inicio"
                )
        );

        btnPagar.addActionListener(e ->
                procesarPago()
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

                txtMonto.setText(
                        String.format(
                                "Q %,.2f",
                                cuota
                        )
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
// ACTUALIZAR CUOTA VISTA
// =========================
public void actualizarCuotaVista() {

    cargarCuota();
}

    // =========================
    // REGISTRAR PAGO
    // =========================
    private void procesarPago() {

        try(Connection con =
                    Conexion.getConexion()) {

            int casa =
                    (int) cbCasas.getSelectedItem();

            String mes =
                    cbMeses.getSelectedItem()
                            .toString();

            int anio =
                    Integer.parseInt(
                            txtAnio.getText()
                    );

            // OBTENER ID CASA
            String sqlCasa =
                    "SELECT id_casa " +
                    "FROM casa " +
                    "WHERE numero = ?";

            PreparedStatement psCasa =
                    con.prepareStatement(sqlCasa);

            psCasa.setString(
                    1,
                    String.valueOf(casa)
            );

            ResultSet rsCasa =
                    psCasa.executeQuery();

            int idCasa = 0;

            if(rsCasa.next()) {

                idCasa =
                        rsCasa.getInt("id_casa");
            }

            // VALIDAR DUPLICADO
            String validar =
                    "SELECT * FROM pago " +
                    "WHERE id_casa = ? " +
                    "AND mes = ? " +
                    "AND anio = ?";

            PreparedStatement psValidar =
                    con.prepareStatement(validar);

            psValidar.setInt(1, idCasa);

            psValidar.setString(2, mes);

            psValidar.setInt(3, anio);

            ResultSet rsValidar =
                    psValidar.executeQuery();

            if(rsValidar.next()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Esta casa ya pagó "
                                + mes + " "
                                + anio,
                        "Pago duplicado",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // MONTO
            double monto =
                    Double.parseDouble(
                            txtMonto.getText()
                                    .replace("Q","")
                                    .replace(",","")
                                    .trim()
                    );

            // INSERTAR
            String sql =
                    "INSERT INTO pago " +
                    "(mes, anio, monto, estado, id_casa) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, mes);

            ps.setInt(2, anio);

            ps.setDouble(3, monto);

            ps.setString(4, "PAGADO");

            ps.setInt(5, idCasa);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Pago registrado exitosamente"
            );

            cargarPagos();

        } catch(NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un año válido"
            );

        } catch(SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error:\n"
                            + ex.getMessage()
            );
        }
    }

    // =========================
    // CARGAR PAGOS
    // =========================
    private void cargarPagos() {

        modelo.setRowCount(0);

        try(Connection con =
                    Conexion.getConexion()) {

            String sql =
                    "SELECT c.numero, p.mes, " +
                    "p.anio, p.monto, p.estado " +
                    "FROM pago p " +
                    "INNER JOIN casa c " +
                    "ON p.id_casa = c.id_casa " +
                    "ORDER BY p.anio DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                modelo.addRow(new Object[] {

                        rs.getString("numero"),
                        rs.getString("mes"),
                        rs.getInt("anio"),
                        "Q " + String.format(
                                "%,.2f",
                                rs.getDouble("monto")
                        ),
                        rs.getString("estado")
                });
            }

        } catch(SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando pagos:\n"
                            + ex.getMessage()
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