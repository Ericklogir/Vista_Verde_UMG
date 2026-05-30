package proyectofinalvistaverde;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import java.io.File;
import java.awt.Desktop;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class ReporteG extends JPanel {

    private ProyectoFinalVistaVerde framePrincipal;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblTotalRecaudado;
    private JComboBox<String> cbMeses;

    public ReporteG(ProyectoFinalVistaVerde frame) {

        this.framePrincipal = frame;
        setBackground(Tema.FONDO_GENERAL);
        setLayout(new BorderLayout());
        construir();
        cargarReporte("Todos");
    }

    private void construir() {

        add(
                Tema.header(
                        "Reporte General",
                        "Visualización general de pagos"
                ),
                BorderLayout.NORTH
        );

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
        // PANEL SUPERIOR
        // =========================
        JPanel panelSuperior =
        Tema.tarjeta();

panelSuperior.setLayout(
        new BorderLayout(
                20,
                0
        )
);

panelSuperior.setBorder(
        BorderFactory.createEmptyBorder(
                20,
                25,
                20,
                25
        )
);

// =========================
// TITULOS
// =========================
JLabel titulo =
        new JLabel("Resumen Financiero");

titulo.setFont(
        new Font(
                "Segoe UI",
                Font.BOLD,
                22
        )
);

titulo.setForeground(
        Tema.TEXTO_PRINCIPAL
);

lblTotalRecaudado =
        new JLabel(
                "Total Recaudado: Q 0.00"
        );

lblTotalRecaudado.setFont(
        new Font(
                "Segoe UI",
                Font.BOLD,
                16
        )
);

lblTotalRecaudado.setForeground(
        Tema.VERDE_PRIMARIO
);

// =========================
// PANEL IZQUIERDO
// =========================
JPanel izquierda =
        new JPanel();

izquierda.setOpaque(false);

izquierda.setLayout(
        new BoxLayout(
                izquierda,
                BoxLayout.Y_AXIS
        )
);

izquierda.add(titulo);

izquierda.add(
        Box.createVerticalStrut(8)
);

izquierda.add(lblTotalRecaudado);

// =========================
// COMBOBOX MODERNO
// =========================
String[] meses = {

        "Todos",
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

cbMeses =
        Tema.combo(meses);

cbMeses.setPreferredSize(
        new Dimension(180,38)
);

cbMeses.setFont(
        new Font(
                "Segoe UI",
                Font.PLAIN,
                14
        )
);

cbMeses.addActionListener(e -> {

    String mes =
            cbMeses.getSelectedItem()
                    .toString();

    cargarReporte(mes);
});

// =========================
// LABEL FILTRO
// =========================
JLabel lblFiltro =
        new JLabel("Filtrar por mes:");

lblFiltro.setFont(
        new Font(
                "Segoe UI",
                Font.BOLD,
                14
        )
);

lblFiltro.setForeground(
        Tema.TEXTO_PRINCIPAL
);

// =========================
// PANEL FILTRO
// =========================
JPanel panelFiltro =
        new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        10,
                        0
                )
        );

panelFiltro.setOpaque(false);

panelFiltro.add(lblFiltro);

panelFiltro.add(cbMeses);

// =========================
// BOTON VOLVER
// =========================
JButton btnVolver =
        Tema.btnSecundario("← Volver");

btnVolver.setPreferredSize(
        new Dimension(160,40)
);

btnVolver.addActionListener(e ->
        framePrincipal.cambiarPantalla(
                "Inicio"
        )
);
JButton btnPDF =
        Tema.btnPrimario("📄 Generar PDF");

btnPDF.setPreferredSize(
        new Dimension(160,40)
);

btnPDF.addActionListener(e ->
        generarPDF()
);

// =========================
// PANEL DERECHO
// =========================
JPanel derecha =
        new JPanel();

derecha.setOpaque(false);

derecha.setLayout(
        new BoxLayout(
                derecha,
                BoxLayout.Y_AXIS
        )
);

panelFiltro.setAlignmentX(
        Component.RIGHT_ALIGNMENT
);

btnVolver.setAlignmentX(
        Component.RIGHT_ALIGNMENT
);
btnPDF.setAlignmentX(
        Component.RIGHT_ALIGNMENT
);

derecha.add(panelFiltro);

derecha.add(
        Box.createVerticalStrut(15)
);

derecha.add(btnPDF);

derecha.add(
        Box.createVerticalStrut(10)
);

derecha.add(btnVolver);

// =========================
// AGREGAR
// =========================
panelSuperior.add(
        izquierda,
        BorderLayout.WEST
);

panelSuperior.add(
        derecha,
        BorderLayout.EAST
);

        contenedor.add(
                panelSuperior,
                BorderLayout.NORTH
        );

        // =========================
        // TABLA
        // =========================
        JPanel panelTabla = Tema.tarjeta();

        panelTabla.setLayout(
                new BorderLayout()
        );

        modelo =new DefaultTableModel();
        modelo.addColumn("Casa");
        modelo.addColumn("Propietario");
    modelo.addColumn("Mes");
    modelo.addColumn("Año");
    modelo.addColumn("Estado");
    modelo.addColumn("Monto");

        tabla = new JTable(modelo);
        tabla.setDefaultEditor(Object.class, null);
        //ANTIERROR
            
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

        // =========================
        // COLORES ESTADO
        // =========================
        tabla.getColumnModel()
                .getColumn(2)
                .setCellRenderer(
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

                String estado =
                        value.toString();

                if(estado.equalsIgnoreCase("Pagado")) {

                    c.setForeground(
                            new Color(25,135,84)
                    );

                } else {

                    c.setForeground(
                            new Color(220,53,69)
                    );
                }

                return c;
            }
        });

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

        contenedor.add(
                panelTabla,
                BorderLayout.CENTER
        );

        add(contenedor, BorderLayout.CENTER);
    }

    //Tabla de Reporte
    private void cargarReporte(String filtroMes) {

        modelo.setRowCount(0);

        double totalGeneral = 0;

        try(Connection con =
                    Conexion.getConexion()) {

          /*String sql = "SELECT c.numero, pr.nombre, pa.mes, pa.anio, pa.monto, "
                  + "pa.estado FROM pago pa INNER JOIN casa c ON pa.id_casa = c.id_casa "
                  + "INNER JOIN propietario pr ON c.Dpi = pr.Dpi ORDER BY c.numero, pa.anio";*/
          
          String sql;
        if(filtroMes.equals("Todos")) {
        sql = "SELECT c.numero, COALESCE(pr.nombre, 'Sin propietario') AS nombre, pa.mes, pa.anio, pa.monto, "
                + "pa.estado FROM pago pa INNER JOIN casa c ON pa.id_casa = c.id_casa LEFT JOIN propietario pr ON "
                + "c.Dpi = pr.Dpi ORDER BY c.numero, pa.anio";

            } else {

        sql = "SELECT c.numero, COALESCE(pr.nombre, 'Sin propietario') AS nombre, pa.mes, pa.anio, pa.monto, pa.estado"
                + " FROM pago pa INNER JOIN casa c ON pa.id_casa = c.id_casa LEFT JOIN propietario pr ON c.Dpi = pr.Dpi"
                + " WHERE pa.mes = ? ORDER BY c.numero, pa.anio";
        }

            PreparedStatement ps =
                    con.prepareStatement(sql);
            if(!filtroMes.equals("Todos")) {

            ps.setString(1, filtroMes);
        }

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                int casa =
                        rs.getInt("numero");

                String propietario =
                        rs.getString("nombre");

                String estado =
                        rs.getString("estado");

                double monto =
                        rs.getDouble("monto");

                totalGeneral += monto;

                String mes =rs.getString("mes");

                int anio = rs.getInt("anio");

            modelo.addRow(new Object[] {
                    casa,
                    propietario,
                    mes,
                    anio,
                    estado,
                "Q " + String.format(
                "%,.2f",
                monto
        )
});
            }

            lblTotalRecaudado.setText(
                    "Total Recaudado: Q "
                            + String.format(
                            "%,.2f",
                            totalGeneral
                    )
            );

        } catch(SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar reporte:\n"
                            + ex.getMessage()
            );
        }
    }
    private void generarPDF() {

    try {

        Document documento =
                new Document();

        String nombreArchivo =
                "Reporte_VistaVerde_"
                + LocalDate.now()
                + ".pdf";

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(
                        nombreArchivo
                )
        );

        documento.open();

       com.itextpdf.text.Font titulo =
        FontFactory.getFont(
                FontFactory.HELVETICA_BOLD,
                18
        );

        Paragraph encabezado =
                new Paragraph(
                        "CONDOMINIO VISTA VERDE\n\n",
                        titulo
                );

        encabezado.setAlignment(
                Element.ALIGN_CENTER
        );

        documento.add(encabezado);

        documento.add(
                new Paragraph(
                        "Fecha de generación: "
                                + LocalDate.now()
                )
        );

        documento.add(
                new Paragraph(
                        " "
                )
        );

        documento.add(
                new Paragraph(
                        lblTotalRecaudado.getText()
                )
        );

        documento.add(
                new Paragraph(
                        " "
                )
        );

        PdfPTable tablaPDF =
                new PdfPTable(
                        tabla.getColumnCount()
                );

        tablaPDF.setWidthPercentage(
                100
        );

        for(int i = 0;
            i < tabla.getColumnCount();
            i++) {

            PdfPCell celda =
                    new PdfPCell(
                            new Phrase(
                                    tabla.getColumnName(i)
                            )
                    );

            celda.setHorizontalAlignment(
                    Element.ALIGN_CENTER
            );

            tablaPDF.addCell(celda);
        }

        for(int fila = 0;
            fila < tabla.getRowCount();
            fila++) {

            for(int col = 0;
                col < tabla.getColumnCount();
                col++) {

                Object valor =
                        tabla.getValueAt(
                                fila,
                                col
                        );

                tablaPDF.addCell(
                        valor == null
                                ? ""
                                : valor.toString()
                );
            }
        }

        documento.add(tablaPDF);

        documento.close();

        JOptionPane.showMessageDialog(
                this,
                "PDF generado correctamente:\n"
                        + nombreArchivo
        );

        Desktop.getDesktop().open(
                new File(nombreArchivo)
        );

    } catch(Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                "Error al generar PDF:\n"
                        + ex.getMessage()
        );
    }
}
}