package proyectofinalvistaverde;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class EstadoCC extends JPanel {

    private ProyectoFinalVistaVerde framePrincipal;

    private JComboBox<Integer> cbCasas;

    private JLabel lblPropietario;

    private JLabel lblTotalPagado;

    private JLabel lblEstado;

    private JLabel lblCasa;

    private JTable tablaPagos;

    private DefaultTableModel modelo;

    private JTextArea txtResumen;

    public EstadoCC(ProyectoFinalVistaVerde frame) {

        this.framePrincipal = frame;

        setBackground(Tema.FONDO_GENERAL);

        setLayout(new BorderLayout());

        construirUI();
    }

    private void construirUI() {

        add(
                Tema.header(
                        "Estado de Cuenta",
                        "Consulte el historial de pagos"
                ),
                BorderLayout.NORTH
        );

        // =========================
        // CONTENEDOR PRINCIPAL
        // =========================
        JPanel contenedor =
                new JPanel(new BorderLayout());

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
        // BARRA SUPERIOR
        // =========================
        JPanel barra =
                Tema.tarjeta();

        barra.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        15,
                        10
                )
        );

        JLabel lblBuscar =
                Tema.label("Seleccionar Casa:");

        Integer[] casas = new Integer[30];

        for(int i = 0; i < 30; i++) {

            casas[i] = i + 1;
        }

        cbCasas = Tema.combo(casas);

        cbCasas.setPreferredSize(
                new Dimension(120,36)
        );

        JButton btnConsultar =
                Tema.btnPrimario("Consultar");

        btnConsultar.setPreferredSize(
                new Dimension(160,36)
        );

        barra.add(lblBuscar);

        barra.add(cbCasas);

        barra.add(btnConsultar);

        contenedor.add(barra, BorderLayout.NORTH);

        // =========================
        // CENTRO
        // =========================
        JPanel centro =
                new JPanel(
                        new BorderLayout(20,20)
                );

        centro.setOpaque(false);

        centro.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        0,
                        0,
                        0
                )
        );

        // =========================
        // PANEL IZQUIERDO
        // =========================
        JPanel panelInfo =
                Tema.tarjeta();

        panelInfo.setPreferredSize(
                new Dimension(280,0)
        );

        panelInfo.setLayout(
                new GridLayout(6,1,0,14)
        );

        JLabel tituloInfo =
                new JLabel("Información General");

        tituloInfo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        tituloInfo.setForeground(
                Tema.TEXTO_PRINCIPAL
        );

        lblCasa =
                new JLabel("Casa: —");

        lblCasa.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        lblPropietario =
                new JLabel("Propietario: —");

        lblPropietario.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        lblTotalPagado =
                new JLabel("Total Pagado: —");

        lblTotalPagado.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        lblTotalPagado.setForeground(
                Tema.VERDE_PRIMARIO
        );

        lblEstado =
                new JLabel("Estado: —");

        lblEstado.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        JButton btnVolver =
                Tema.btnSecundario("← Volver");

        panelInfo.add(tituloInfo);

        panelInfo.add(lblCasa);

        panelInfo.add(lblPropietario);

        panelInfo.add(lblTotalPagado);

        panelInfo.add(lblEstado);

        panelInfo.add(btnVolver);

        // =========================
        // PANEL DERECHO
        // =========================
        JPanel panelDerecho =
                new JPanel(
                        new BorderLayout(0,20)
                );
   
        // =========================
        // TABLA
        // =========================
        JPanel panelTabla =
                Tema.tarjeta();

        panelTabla.setLayout(new BorderLayout());

        JLabel lblTabla =
                Tema.label("Historial de Pagos");

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

        modelo = new DefaultTableModel();

        modelo.addColumn("Mes");
        modelo.addColumn("Año");
        modelo.addColumn("Monto");
        modelo.addColumn("Estado");

        tablaPagos =
                new JTable(modelo);
                tablaPagos.setDefaultEditor(Object.class, null);
                 //ANTIERROR

        // =========================
        // ESTILO TABLA
        // =========================
        tablaPagos.setRowHeight(32);

        tablaPagos.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tablaPagos.setSelectionBackground(
                new Color(200,230,201)
        );

        tablaPagos.setSelectionForeground(
                Color.BLACK
        );

        tablaPagos.setGridColor(
                new Color(230,230,230)
        );

        tablaPagos.setShowVerticalLines(false);

        tablaPagos.setIntercellSpacing(
                new Dimension(0,1)
        );

        tablaPagos.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        tablaPagos.getTableHeader().setBackground(
                Tema.VERDE_PRIMARIO
        );

        tablaPagos.getTableHeader().setForeground(
                Color.WHITE
        );

        tablaPagos.getTableHeader().setOpaque(false);

        JScrollPane scroll =
                new JScrollPane(tablaPagos);

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
        // RESUMEN
        // =========================
        JPanel panelResumen =
                Tema.tarjeta();
        
        //ANTI ERROR
        panelResumen.setPreferredSize(new Dimension(0, 150));

        panelResumen.setLayout(
                new BorderLayout()
        );

        JLabel lblResumen =
                Tema.label("Resumen General");

        lblResumen.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        10,
                        0
                )
        );

        txtResumen =
                new JTextArea();

        txtResumen.setEditable(false);

        txtResumen.setBackground(Color.WHITE);

        txtResumen.setForeground(
                Tema.TEXTO_PRINCIPAL
        );

        txtResumen.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        txtResumen.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        txtResumen.setText(
                "Seleccione una casa para consultar."
        );

        panelResumen.add(
                lblResumen,
                BorderLayout.NORTH
        );
        
        //ANTI ERROR
        JScrollPane scrollResumen = new JScrollPane(txtResumen);
        scrollResumen.setBorder(BorderFactory.createEmptyBorder());
        scrollResumen.getViewport().setBackground(Color.WHITE);
        panelResumen.add(scrollResumen, BorderLayout.CENTER);

        panelDerecho.add(
                panelTabla,
                BorderLayout.CENTER
        );

        panelDerecho.add(
                panelResumen,
                BorderLayout.SOUTH
        );

        // =========================
        // AGREGAR
        // =========================
        centro.add(
                panelInfo,
                BorderLayout.WEST
        );

        centro.add(
                panelDerecho,
                BorderLayout.CENTER
        );
        contenedor.add(
                centro,
                BorderLayout.CENTER
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

        btnConsultar.addActionListener(e ->
                mostrarEstado()
        );
    }

    // =========================
    // MOSTRAR ESTADO
    // =========================
    private void mostrarEstado() {

        int casa =
                (int) cbCasas.getSelectedItem();

        modelo.setRowCount(0);

        try(Connection con =
                    Conexion.getConexion()) {

            lblCasa.setText(
                    "Casa: #" + casa
            );

            // =========================
            // PROPIETARIO
            // =========================
            String sqlProp =
                    "SELECT p.nombre " +
                    "FROM propietario p " +
                    "INNER JOIN casa c " +
                    "ON p.Dpi = c.Dpi " +
                    "WHERE c.numero = ?";

            PreparedStatement psProp =
                    con.prepareStatement(sqlProp);

            psProp.setString(
                    1,
                    String.valueOf(casa)
            );

            ResultSet rsProp =
                    psProp.executeQuery();

            String propietario =
                    "Sin propietario";

            if(rsProp.next()) {

                propietario =
                        rsProp.getString("nombre");
            }

            lblPropietario.setText(
                    "Propietario: "
                            + propietario
            );

            // =========================
            // PAGOS
            // =========================
            String sqlPagos =
                    "SELECT p.mes, p.anio, " +
                    "p.monto, p.estado " +
                    "FROM pago p " +
                    "INNER JOIN casa c " +
                    "ON p.id_casa = c.id_casa " +
                    "WHERE c.numero = ? " +
                    "ORDER BY p.anio, p.id_pago";

            PreparedStatement psPagos =
                    con.prepareStatement(sqlPagos);

            psPagos.setString(
                    1,
                    String.valueOf(casa)
            );

            ResultSet rsPagos =
                    psPagos.executeQuery();

            ArrayList<String> mesesPagados =
                    new ArrayList<>();

            double total = 0;

            while(rsPagos.next()) {

                String mes =
                        rsPagos.getString("mes");

                int anio =
                        rsPagos.getInt("anio");

                double monto =
                        rsPagos.getDouble("monto");

                String estado =
                        rsPagos.getString("estado");

                mesesPagados.add(mes);

                total += monto;

                modelo.addRow(new Object[] {

                        mes,
                        anio,
                        "Q "
                                + String.format(
                                "%,.2f",
                                monto
                        ),
                        estado
                });
            }

            lblTotalPagado.setText(
                    "Total Pagado: Q "
                            + String.format(
                            "%,.2f",
                            total
                    )
            );

            // =========================
            // VALIDAR ESTADO
            // =========================
            LocalDate hoy =
                    LocalDate.now();

            Month mesActual =
                    hoy.getMonth();

            String nombreMesActual =
                    traducirMes(
                            mesActual.toString()
                    );

            boolean alDia =
                    mesesPagados.contains(
                            nombreMesActual
                    );

            if(alDia) {

                lblEstado.setText(
                        "Estado: AL DÍA"
                );

                lblEstado.setForeground(
                        Tema.VERDE_PRIMARIO
                );

            } else {

                lblEstado.setText(
                        "Estado: MOROSO"
                );

                lblEstado.setForeground(
                        new Color(220,53,69)
                );
            }

            // =========================
            // PENDIENTES
            // =========================
            String[] todosMeses = {

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

            StringBuilder pendientes =
                    new StringBuilder();

            int indiceActual =
                    mesActual.getValue();

            for(int i = 0; i < indiceActual; i++) {

                if(!mesesPagados.contains(
                        todosMeses[i]
                )) {

                    pendientes.append(
                            "• "
                                    + todosMeses[i]
                                    + " "
                                    + hoy.getYear()
                                    + "\n"
                    );
                }
            }

            // =========================
            // RESUMEN
            // =========================
            String resumen;

            if(alDia) {

                resumen =
                        " La casa está al día.\n\n"
                                + "Todos los pagos correspondientes "
                                + "al mes actual han sido realizados.";

            } else {

                resumen =
                        " La casa presenta morosidad.\n\n"
                                + "Meses pendientes:\n\n"
                                + pendientes;
            }

            txtResumen.setText(resumen);

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