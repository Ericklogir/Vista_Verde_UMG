package proyectofinalvistaverde;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class CasasM extends JPanel {

    private ProyectoFinalVistaVerde framePrincipal;

    private JTable tablaMorosos;

    private DefaultTableModel modelo;

    private JLabel lblTotalMorosos;

    private JLabel lblTotalDeuda;

    private JLabel lblCasasDia;

    private JLabel lblMesActual;

    private JTextArea txtAlerta;

    public CasasM(ProyectoFinalVistaVerde frame) {

        this.framePrincipal = frame;

        setBackground(Tema.FONDO_GENERAL);

        setLayout(new BorderLayout());

        construirUI();

        cargarMorosos();
    }

    private void construirUI() {

        // =========================
        // HEADER
        // =========================
        JPanel headerPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setPaint(
                        new GradientPaint(
                                0,
                                0,
                                new Color(192,57,43),
                                getWidth(),
                                0,
                                new Color(231,76,60)
                        )
                );

                g2.fillRect(
                        0,
                        0,
                        getWidth(),
                        getHeight()
                );

                g2.dispose();
            }
        };

        headerPanel.setPreferredSize(
                new Dimension(0,100)
        );

        headerPanel.setLayout(
                new GridLayout(2,1)
        );

        JLabel lTit =
                new JLabel(
                        "Casas Morosas",
                        SwingConstants.CENTER
                );

        lTit.setFont(
                Tema.FUENTE_TITULO
        );

        lTit.setForeground(Color.WHITE);

        JLabel lSub =
                new JLabel(
                        "Panel de monitoreo de morosidad",
                        SwingConstants.CENTER
                );

        lSub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        lSub.setForeground(
                new Color(255,255,255,210)
        );

        headerPanel.add(lTit);

        headerPanel.add(lSub);

        add(headerPanel, BorderLayout.NORTH);

        // =========================
        // CONTENEDOR
        // =========================
        JPanel contenedor =
                new JPanel(
                        new BorderLayout(20,20)
                );

        contenedor.setBackground(
                Tema.FONDO_GENERAL
        );

        contenedor.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // =========================
        // CARDS
        // =========================
        JPanel panelCards =
                new JPanel(
                        new GridLayout(1,4,15,0)
                );

        panelCards.setOpaque(false);

        lblTotalMorosos =
                crearCard(
                        "Casas Morosas",
                        "0",
                        new Color(231,76,60)
                );

        lblTotalDeuda =
                crearCard(
                        "Total Adeudado",
                        "Q 0.00",
                        new Color(243,156,18)
                );

        lblCasasDia =
                crearCard(
                        "Casas al Día",
                        "0",
                        new Color(39,174,96)
                );

        lblMesActual =
                crearCard(
                        "Mes Evaluado",
                        traducirMes(
                                LocalDate.now()
                                        .getMonth()
                                        .toString()
                        ),
                        new Color(52,152,219)
                );

        panelCards.add(
                (JPanel) lblTotalMorosos.getParent()
        );

        panelCards.add(
                (JPanel) lblTotalDeuda.getParent()
        );

        panelCards.add(
                (JPanel) lblCasasDia.getParent()
        );

        panelCards.add(
                (JPanel) lblMesActual.getParent()
        );

        // =========================
        // CENTRO
        // =========================
        JPanel centro =
                new JPanel(
                        new BorderLayout(20,0)
                );

        centro.setOpaque(false);

        // =========================
        // TABLA
        // =========================
        JPanel panelTabla =
                Tema.tarjeta();

        panelTabla.setLayout(
                new BorderLayout()
        );

        JLabel lblTabla =
                Tema.label(
                        "Listado de Morosos"
                );

        lblTabla.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        panelTabla.add(
                lblTabla,
                BorderLayout.NORTH
        );

        String[] columnas = {

                "Casa",
                "Propietario",
                "Teléfono",
                "Meses Atrasados",
                "Deuda",
                "Estado"
        };

        modelo =
                new DefaultTableModel(
                        columnas,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int r,
                            int c
                    ) {

                        return false;
                    }
                };

        tablaMorosos =
                new JTable(modelo);

        // =========================
        // ESTILO TABLA
        // =========================
        tablaMorosos.setRowHeight(34);

        tablaMorosos.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tablaMorosos.setSelectionBackground(
                new Color(253,237,236)
        );

        tablaMorosos.setSelectionForeground(
                Color.BLACK
        );

        tablaMorosos.setGridColor(
                new Color(235,235,235)
        );

        tablaMorosos.setShowVerticalLines(false);

        tablaMorosos.setIntercellSpacing(
                new Dimension(0,1)
        );

        tablaMorosos.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        tablaMorosos.getTableHeader().setBackground(
                new Color(192,57,43)
        );

        tablaMorosos.getTableHeader().setForeground(
                Color.WHITE
        );

        tablaMorosos.getTableHeader().setOpaque(false);

        // FILAS
        tablaMorosos.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                    ) {

                        Component c =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );

                        if(!isSelected) {

                            c.setBackground(
                                    row % 2 == 0
                                            ? Color.WHITE
                                            : new Color(
                                            252,
                                            248,
                                            248
                                    )
                            );
                        }

                        setBorder(
                                BorderFactory.createEmptyBorder(
                                        0,
                                        10,
                                        0,
                                        10
                                )
                        );

                        return c;
                    }
                }
        );

        JScrollPane scroll =
                new JScrollPane(tablaMorosos);

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
        // ALERTA
        // =========================
        JPanel panelAlerta =
                Tema.tarjeta();

        panelAlerta.setPreferredSize(
                new Dimension(280,0)
        );

        panelAlerta.setLayout(
                new BorderLayout()
        );

        JLabel lblAlerta =
                Tema.label(
                        "⚠ Alertas"
                );

        lblAlerta.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        10,
                        0
                )
        );

        txtAlerta =
                new JTextArea();

        txtAlerta.setEditable(false);

        txtAlerta.setLineWrap(true);

        txtAlerta.setWrapStyleWord(true);

        txtAlerta.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        txtAlerta.setBackground(Color.WHITE);

        txtAlerta.setForeground(
                Tema.TEXTO_PRINCIPAL
        );

        txtAlerta.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        panelAlerta.add(
                lblAlerta,
                BorderLayout.NORTH
        );

        panelAlerta.add(
                txtAlerta,
                BorderLayout.CENTER
        );

        centro.add(
                panelTabla,
                BorderLayout.CENTER
        );

        centro.add(
                panelAlerta,
                BorderLayout.EAST
        );

        // =========================
        // FOOTER
        // =========================
        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setBackground(Color.WHITE);

        footer.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                1,
                                0,
                                0,
                                0,
                                Tema.BORDE_SUAVE
                        ),
                        BorderFactory.createEmptyBorder(
                                12,
                                24,
                                12,
                                24
                        )
                )
        );

        JButton btnActualizar =
                Tema.btnPrimario(
                        "Actualizar Lista"
                );

        JButton btnVolver =
                Tema.btnSecundario(
                        "← Volver al Inicio"
                );

        JPanel panelBtns =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        panelBtns.setOpaque(false);

        panelBtns.add(btnActualizar);

        panelBtns.add(btnVolver);

        footer.add(
                panelBtns,
                BorderLayout.EAST
        );

        // =========================
        // AGREGAR
        // =========================
        contenedor.add(
                panelCards,
                BorderLayout.NORTH
        );

        contenedor.add(
                centro,
                BorderLayout.CENTER
        );

        contenedor.add(
                footer,
                BorderLayout.SOUTH
        );

        add(contenedor, BorderLayout.CENTER);

        // =========================
        // EVENTOS
        // =========================
        btnVolver.addActionListener(e ->
                framePrincipal.cambiarPantalla(
                        "Inicio"
                )
        );

        btnActualizar.addActionListener(e ->
                cargarMorosos()
        );
    }

    // =========================
    // CREAR CARD
    // =========================
    private JLabel crearCard(
            String titulo,
            String valor,
            Color color
    ) {

        JPanel card = Tema.tarjeta();

        card.setLayout(
                new GridLayout(2,1)
        );

        JLabel lblTitulo =
                new JLabel(
                        titulo
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        lblTitulo.setForeground(
                Tema.TEXTO_SECUNDARIO
        );

        JLabel lblValor =
                new JLabel(valor);

        lblValor.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblValor.setForeground(color);

        card.add(lblTitulo);

        card.add(lblValor);

        return lblValor;
    }

    // =========================
    // CARGAR MOROSOS
    // =========================
    private void cargarMorosos() {

        modelo.setRowCount(0);

        try(Connection con =
                    Conexion.getConexion()) {

            String mesActual =
                    traducirMes(
                            LocalDate.now()
                                    .getMonth()
                                    .toString()
                    );

            int anioActual =
                    LocalDate.now().getYear();

            ArrayList<Integer> casasPagadas =
                    new ArrayList<>();

            // =========================
            // CASAS QUE PAGARON
            // =========================
            String sqlPagados =
                    "SELECT c.numero " +
                    "FROM pago p " +
                    "INNER JOIN casa c " +
                    "ON p.id_casa = c.id_casa " +
                    "WHERE p.mes = ? " +
                    "AND p.anio = ?";

            PreparedStatement psPagados =
                    con.prepareStatement(sqlPagados);

            psPagados.setString(1, mesActual);

            psPagados.setInt(2, anioActual);

            ResultSet rsPagados =
                    psPagados.executeQuery();

            while(rsPagados.next()) {

                casasPagadas.add(
                        Integer.parseInt(
                                rsPagados.getString(
                                        "numero"
                                )
                        )
                );
            }

            // =========================
            // TODAS LAS CASAS
            // =========================
            String sqlCasas =
                    "SELECT c.numero, " +
                    "p.nombre, " +
                    "p.telefono " +
                    "FROM casa c " +
                    "LEFT JOIN propietario p " +
                    "ON c.Dpi = p.Dpi";

            PreparedStatement psCasas =
                    con.prepareStatement(sqlCasas);

            ResultSet rsCasas =
                    psCasas.executeQuery();

            int totalMorosos = 0;

            double totalDeuda = 0;

            while(rsCasas.next()) {

                int casa =
                        Integer.parseInt(
                                rsCasas.getString(
                                        "numero"
                                )
                        );

                if(!casasPagadas.contains(casa)) {

                    totalMorosos++;

                    // =========================
                    // CUOTA
                    // =========================
                    double cuota = 0;

                    String sqlCuota =
                            "SELECT cuota_mensual " +
                            "FROM configuracion " +
                            "LIMIT 1";

                    PreparedStatement psCuota =
                            con.prepareStatement(sqlCuota);

                    ResultSet rsCuota =
                            psCuota.executeQuery();

                    if(rsCuota.next()) {

                        cuota =
                                rsCuota.getDouble(
                                        "cuota_mensual"
                                );
                    }

                    totalDeuda += cuota;

                    modelo.addRow(new Object[] {

                            casa,

                            rsCasas.getString(
                                    "nombre"
                            ),

                            rsCasas.getString(
                                    "telefono"
                            ),

                            1,

                            "Q "
                                    + String.format(
                                    "%,.2f",
                                    cuota
                            ),

                            "MOROSO"
                    });
                }
            }

            // =========================
            // RESUMEN
            // =========================
            lblTotalMorosos.setText(
                    String.valueOf(
                            totalMorosos
                    )
            );

            lblTotalDeuda.setText(
                    "Q "
                            + String.format(
                            "%,.2f",
                            totalDeuda
                    )
            );

            lblCasasDia.setText(
                    String.valueOf(
                            30 - totalMorosos
                    )
            );

            // =========================
            // ALERTA
            // =========================
            String alerta;

            if(totalMorosos == 0) {

                alerta =
                        "✔ Excelente.\n\n"
                                + "Todas las casas "
                                + "están al día.";

            } else {

                alerta =
                        "⚠ Atención.\n\n"
                                + totalMorosos
                                + " casas presentan "
                                + "morosidad en el mes actual.\n\n"
                                + "Deuda acumulada:\nQ "
                                + String.format(
                                "%,.2f",
                                totalDeuda
                        );
            }

            txtAlerta.setText(alerta);

        } catch(SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error:\n"
                            + ex.getMessage()
            );
        }
    }

    // =========================
    // TRADUCIR MES
    // =========================
    private String traducirMes(String mes) {

        switch(mes) {

            case "JANUARY":
                return "Enero";

            case "FEBRUARY":
                return "Febrero";

            case "MARCH":
                return "Marzo";

            case "APRIL":
                return "Abril";

            case "MAY":
                return "Mayo";

            case "JUNE":
                return "Junio";

            case "JULY":
                return "Julio";

            case "AUGUST":
                return "Agosto";

            case "SEPTEMBER":
                return "Septiembre";

            case "OCTOBER":
                return "Octubre";

            case "NOVEMBER":
                return "Noviembre";

            default:
                return "Diciembre";
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