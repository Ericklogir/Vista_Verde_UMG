package proyectofinalvistaverde;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class RegistroP extends JPanel {

    private ProyectoFinalVistaVerde framePrincipal;

    private JTextField txtDpi;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JComboBox<Integer> cbCasas;

    private JTable tabla;
    private DefaultTableModel modelo;

    public RegistroP(ProyectoFinalVistaVerde frame) {

    this.framePrincipal = frame;

    setBackground(Tema.FONDO_GENERAL);

    setLayout(
            new BorderLayout()
    );

    modelo =
            new DefaultTableModel();

    construirUI();

    cargarPropietarios();
}

    
    private void limpiarCampos() {

        txtDpi.setText("");

        txtNombre.setText("");

        txtTelefono.setText("");

        txtCorreo.setText("");

        cbCasas.setSelectedIndex(0);

        tabla.clearSelection();
    }
    
    private void construirUI() {
        
        //ANTIERRORES DEL USUARIO
        
        //DPI
        txtDpi = Tema.campo(25);
        txtDpi.addKeyListener(
        new java.awt.event.KeyAdapter() {

            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {

                char blo = e.getKeyChar();

                if(!Character.isDigit(blo)) {

                    e.consume();

                }
            }
        }
        );
        
        //NOMBRE
        txtNombre = Tema.campo(25);

        txtNombre.addKeyListener(
        new java.awt.event.KeyAdapter() {

            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {

                char c =e.getKeyChar();

                if(Character.isLetter(c)&&!Character.isWhitespace(c)) {

                    e.consume();
                }
            }
        }
        );
        

    add(
            Tema.header(
                    " Registro de Propietario",
                    "Llene los datos del propietario"
            ),
            BorderLayout.NORTH
    );

    JPanel centro = new JPanel(new BorderLayout());

    centro.setBackground(Tema.FONDO_GENERAL);

    centro.setBorder(
            BorderFactory.createEmptyBorder(
                    20,20,20,20
            )
    );

    JPanel panelFormulario =
            new JPanel(new GridBagLayout());

    panelFormulario.setOpaque(false);

    JPanel tarjeta = Tema.tarjeta();

    tarjeta.setLayout(new GridBagLayout());

    tarjeta.setPreferredSize(
            new Dimension(420,500)
    );

    GridBagConstraints f =
            new GridBagConstraints();

    f.fill = GridBagConstraints.HORIZONTAL;
    f.anchor = GridBagConstraints.WEST;
    f.weightx = 1;

    // =========================
    // DPI
    // =========================
    f.gridx = 0;
    f.gridy = 0;
    f.insets = new Insets(0,0,2,0);

    tarjeta.add(
            Tema.label("DPI *"),
            f
    );

    txtDpi = Tema.campo(25);

    f.gridy = 1;
    f.insets = new Insets(0,0,14,0);

    tarjeta.add(txtDpi, f);

    // =========================
    // NOMBRE
    // =========================
    f.gridx = 0;
    f.gridy = 2;
    f.insets = new Insets(0,0,2,0);

    tarjeta.add(
            Tema.label("Nombre Completo *"),
            f
    );

    txtNombre = Tema.campo(25);

    f.gridy = 3;
    f.insets = new Insets(0,0,14,0);

    tarjeta.add(txtNombre, f);

    // =========================
    // TELEFONO
    // =========================
    f.gridy = 4;
    f.insets = new Insets(0,0,2,0);

    tarjeta.add(
            Tema.label("Número de Teléfono *"),
            f
    );

    txtTelefono = Tema.campo(25);

    f.gridy = 5;
    f.insets = new Insets(0,0,14,0);

    tarjeta.add(txtTelefono, f);

    // =========================
    // CORREO
    // =========================
    f.gridy = 6;
    f.insets = new Insets(0,0,2,0);

    tarjeta.add(
            Tema.label("Correo Electrónico *"),
            f
    );

    txtCorreo = Tema.campo(25);

    f.gridy = 7;
    f.insets = new Insets(0,0,14,0);

    tarjeta.add(txtCorreo, f);

    // =========================
    // CASA
    // =========================
    f.gridy = 8;
    f.insets = new Insets(0,0,2,0);

    tarjeta.add(
            Tema.label("Número de Casa *"),
            f
    );

    Integer[] casas = new Integer[30];

    for(int i = 0; i < 30; i++) {

        casas[i] = i + 1;
    }

    cbCasas = Tema.combo(casas);

    f.gridy = 9;
    f.insets = new Insets(0,0,22,0);

    tarjeta.add(cbCasas, f);

    // =========================
    // BOTONES
    // =========================
    JPanel panelBtns =
            new JPanel(
                    new GridLayout(
                            2,
                            2,
                            12,
                            12
                    )
            );

    panelBtns.setOpaque(false);

    JButton btnGuardar =
            Tema.btnPrimario(
                    "💾 Guardar"
            );

    JButton btnMostrar =
            Tema.btnSecundario(
                    "👁 Mostrar"
            );

    JButton btnModificar =
            Tema.btnSecundario(
                    "✏ Modificar"
            );

    JButton btnEliminar =
            Tema.btnSecundario(
                    "🗑 Eliminar"
            );

    JButton btnVolver =
            Tema.btnSecundario(
                    "← Volver"
            );

    btnMostrar.setBackground(
            new Color(
                    33,
                    150,
                    243
            )
    );

    btnMostrar.setForeground(
            Color.WHITE
    );

    btnMostrar.setFocusPainted(false);

    btnMostrar.setBorderPainted(false);

    btnMostrar.setOpaque(true);

    btnMostrar.setContentAreaFilled(true);

    btnModificar.setBackground(
            new Color(
                    255,
                    152,
                    0
            )
    );

    btnModificar.setForeground(
            Color.WHITE
    );

    btnModificar.setFocusPainted(false);

    btnModificar.setBorderPainted(false);

    btnModificar.setOpaque(true);

    btnModificar.setContentAreaFilled(true);

    btnEliminar.setBackground(
            new Color(
                    244,
                    67,
                    54
            )
    );

    btnEliminar.setForeground(
            Color.WHITE
    );

    btnEliminar.setFocusPainted(false);

    btnEliminar.setBorderPainted(false);

    btnEliminar.setOpaque(true);

    btnEliminar.setContentAreaFilled(true);

    panelBtns.add(btnGuardar);
    panelBtns.add(btnMostrar);
    panelBtns.add(btnModificar);
    panelBtns.add(btnEliminar);

    f.gridy = 10;

    tarjeta.add(
            panelBtns,
            f
    );

    f.gridy = 11;

    f.insets =
            new Insets(
                    15,
                    0,
                    0,
                    0
            );

    tarjeta.add(
            btnVolver,
            f
    );

    panelFormulario.add(
            tarjeta
    );

    centro.add(
            panelFormulario,
            BorderLayout.WEST
    );

    add(
            centro,
            BorderLayout.CENTER
    );
    
    // =========================
// TABLA
// =========================
JPanel panelTabla = Tema.tarjeta();

panelTabla.setLayout(
        new BorderLayout()
);

JLabel lblTabla =
        Tema.label(
                "Propietarios Registrados"
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

// =========================
// MODELO
// =========================
modelo =
        new DefaultTableModel();

modelo.addColumn("DPI");
modelo.addColumn("Nombre");
modelo.addColumn("Teléfono");
modelo.addColumn("Correo");
modelo.addColumn("Casa");

// =========================
// TABLA
// =========================
tabla =
        new JTable(
                modelo
        );

tabla.setRowHeight(32);

tabla.setFont(
        new Font(
                "Segoe UI",
                Font.PLAIN,
                13
        )
);

tabla.setSelectionBackground(
        new Color(
                200,
                230,
                201
        )
);

tabla.setSelectionForeground(
        Color.BLACK
);

tabla.setGridColor(
        new Color(
                230,
                230,
                230
        )
);

tabla.setShowVerticalLines(
        false
);

tabla.setIntercellSpacing(
        new Dimension(
                0,
                1
        )
);

tabla.getTableHeader()
        .setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

tabla.getTableHeader()
        .setBackground(
                Tema.VERDE_PRIMARIO
        );

tabla.getTableHeader()
        .setForeground(
                Color.WHITE
        );

tabla.getTableHeader()
        .setOpaque(false);

tabla.getTableHeader()
        .setReorderingAllowed(
                false
        );

tabla.setBorder(
        BorderFactory
                .createEmptyBorder()
);

tabla.setFillsViewportHeight(
        true
);

// =========================
// SCROLL
// =========================
JScrollPane scroll =
        new JScrollPane(
                tabla
        );

scroll.setBorder(
        BorderFactory
                .createEmptyBorder()
);

scroll.getViewport()
        .setBackground(
                Color.WHITE
        );

panelTabla.add(
        scroll,
        BorderLayout.CENTER);

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

// =========================
// CARGAR TABLA → FORM
// =========================
tabla.getSelectionModel()
        .addListSelectionListener(
                e -> {

                    int fila =
                            tabla.getSelectedRow();

                    if(fila != -1) {

                        txtDpi.setText(
                                modelo.getValueAt(
                                        fila,
                                        0
                                ).toString()
                        );

                        txtNombre.setText(
                                modelo.getValueAt(
                                        fila,
                                        1
                                ).toString()
                        );

                        txtTelefono.setText(
                                modelo.getValueAt(
                                        fila,
                                        2
                                ).toString()
                        );

                        txtCorreo.setText(
                                modelo.getValueAt(
                                        fila,
                                        3
                                ).toString()
                        );

                        cbCasas.setSelectedItem(
                                Integer.parseInt(
                                        modelo.getValueAt(
                                                fila,
                                                4
                                        ).toString()
                                )
                        );
                    }
                }
        );

    btnVolver.addActionListener(
            e ->
                    framePrincipal
                            .cambiarPantalla(
                                    "Inicio"
                            )
    );

    btnGuardar.addActionListener(
            e ->
                    guardarPropietario()
    );

    btnMostrar.addActionListener(
            e ->
                    cargarPropietarios()
    );

    btnModificar.addActionListener(
            e ->
                    modificarPropietario()
    );

    btnEliminar.addActionListener(
            e ->
                    eliminarPropietario()
    );
}

    // =========================
    // GUARDAR
    // =========================
    private void guardarPropietario() {

    //ANTIERRORES//
    //DPI
        /*String dpi = txtDpi.getText().trim();
        
        if (!dpi.matches("\\d{13}")){
            JOptionPane.showMessageDialog(this, "El DPI solo puede tener valores numericos y solo acepta 13 digitos");
            txtDpi.requestFocus();
            return;
        }
        //NOMBRE
        String nombre = txtNombre.getText().trim();

        if(!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

            JOptionPane.showMessageDialog(this,"El nombre solo puede contener letras"
            );

            txtNombre.requestFocus();

            return;
        }
        //NUMERO DE TELEFONO
        String tel = txtTelefono.getText().trim();
        
        if (!tel.matches("\\d{8}")){
            JOptionPane.showMessageDialog(this, "El número de telefono solo puede tener valores numericos");
            txtDpi.requestFocus();
            return;
        }
        //CORREO
        String correo = txtCorreo.getText().trim();

        if(!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            JOptionPane.showMessageDialog(this,"Ingrese un correo electrónico válido" );

            txtCorreo.requestFocus();

            return;
        }*/
    String errores = "";

        // CAMPOS VACÍOS

        if(txtDpi.getText().trim().isEmpty()) {
            errores += "• Ingrese el DPI\n";
        }
        if(txtNombre.getText().trim().isEmpty()) {
            errores += "• Ingrese el nombre\n";
        }
        if(txtTelefono.getText().trim().isEmpty()) {
            errores += "• Ingrese el teléfono\n";
        }
        if(txtCorreo.getText().trim().isEmpty()) {
            errores += "• Ingrese el correo\n";
        }
        
        //DPI Y NUMERO DE TELEFONO

        String dpi = txtDpi.getText().trim();

        if(!dpi.isEmpty()&&!dpi.matches("\\d{13}")) {
            errores += "• El DPI debe tener exactamente 13 números y no acepta caracteres\n";
        }
        
        String tel = txtTelefono.getText().trim();
        
        if (!tel.matches("\\d{8}")){
            errores += "• El numero de telefono debe tener 8 numeros exactos y solo valores numericos\n";
        }

        // NOMBRE

        String nombre = txtNombre.getText().trim();

        if(!nombre.isEmpty()&&!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

            errores +="• El nombre solo acepta letras y espacios\n";
        }

        // CORREO

        String correo = txtCorreo.getText().trim();

        if(!correo.isEmpty()&&!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            errores += "• Correo electrónico inválido\n";
        }

        if(!errores.isEmpty()) {

            JOptionPane.showMessageDialog(this,errores,"Corrija los siguientes errores",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //AANTIERRORES FINALES
        
        
        if(txtDpi.getText().trim().isEmpty()
                ||txtNombre.getText().trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()
                || txtCorreo.getText().trim().isEmpty())
        {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos"
            );

            return;
        }

        try(Connection con =
                    Conexion.getConexion()) {
            
            //VALIDAR CASAS OCUPADAS
            String verificarCasa ="SELECT Dpi " + "FROM casa " + "WHERE numero = ?";

            PreparedStatement psVerificar = con.prepareStatement(verificarCasa );

            psVerificar.setInt(1,(Integer)cbCasas.getSelectedItem());

            ResultSet rsCasa = psVerificar.executeQuery();

            if(rsCasa.next()&&rsCasa.getObject("Dpi")!= null) {

                JOptionPane.showMessageDialog(this,"La casa seleccionada ya está ocupada");

                return;
            }
            
            String sql = "INSERT INTO propietario " + "(Dpi, nombre, telefono, correo, casa) " + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1,Long.parseLong(
                txtDpi.getText().trim()
            )
            );

            ps.setString(2,txtNombre.getText().trim()
            );

            ps.setString(3,txtTelefono.getText().trim()
            );

            ps.setString(4,txtCorreo.getText().trim()
            );
            
            ps.setInt(5,(Integer)cbCasas.getSelectedItem());

            ps.executeUpdate();

            String sqlCasa =
                    "UPDATE casa SET Dpi = ? " +
                    "WHERE numero = ?";

            PreparedStatement psCasa =
                    con.prepareStatement(sqlCasa);

            psCasa.setLong(1,Long.parseLong(
                txtDpi.getText().trim()
            )
            );

            psCasa.setString(
                    2,
                    cbCasas.getSelectedItem()
                            .toString()
            );

            psCasa.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Propietario registrado"
            );

            limpiarCampos();

            cargarPropietarios();

        } catch(SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error:\n" + ex.getMessage()
            );
        }
    }

    // =========================
    // MOSTRAR
    // =========================
    private void cargarPropietarios() {

         modelo.setRowCount(0);

    try(Connection con = Conexion.getConexion()) {

        String sql =
                "SELECT Dpi, nombre, telefono, correo, casa " +
                "FROM propietario";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()) {

            modelo.addRow(new Object[] {

                    rs.getLong("Dpi"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getInt("casa")
            });
        }

    } catch(SQLException ex) {

        JOptionPane.showMessageDialog(
                this,
                "Error cargando:\n" + ex.getMessage()
        );
    }
    }

    // =========================
    // MODIFICAR
    // =========================
    private void modificarPropietario() {

        int fila = tabla.getSelectedRow();

        if(fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un propietario"
            );

            return;
        }

        long dpi = Long.parseLong(modelo.getValueAt(fila,0).toString());

        try(Connection con =
                    Conexion.getConexion()) {

            String sql =
                    "UPDATE propietario " +
                    "SET nombre=?, telefono=?, correo=?, casa=? " +"WHERE Dpi=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    txtNombre.getText().trim()
            );

            ps.setString(
                    2,
                    txtTelefono.getText().trim()
            );

            ps.setString(
                    3,
                    txtCorreo.getText().trim()
            );

            ps.setInt(4,(Integer)cbCasas.getSelectedItem());

            ps.setLong(5,dpi);

            ps.executeUpdate();

            String limpiar =
                    "UPDATE casa SET Dpi = NULL " +
                    "WHERE Dpi = ?";

            PreparedStatement psLimpiar =
                    con.prepareStatement(limpiar);

            psLimpiar.setLong(1, dpi);

            psLimpiar.executeUpdate();

            String nuevaCasa =
                    "UPDATE casa SET Dpi = ? " +
                    "WHERE numero = ?";

            PreparedStatement psCasa =
                    con.prepareStatement(nuevaCasa);

            psCasa.setLong(1, dpi);

            psCasa.setString(
                    2,
                    cbCasas.getSelectedItem()
                            .toString()
            );

            psCasa.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Propietario modificado"
            );

            limpiarCampos();

            cargarPropietarios();

        } catch(SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error modificando:\n"
                            + ex.getMessage()
            );
        }
    }

    // =========================
    // ELIMINAR
    // =========================
    private void eliminarPropietario() {

        int fila = tabla.getSelectedRow();

        if(fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un propietario"
            );

            return;
        }

        long dpi = Long.parseLong(modelo.getValueAt(fila,0).toString());

        int confirmar =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Eliminar propietario?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );

        if(confirmar != JOptionPane.YES_OPTION) {

            return;
        }

        try(Connection con =
                    Conexion.getConexion()) {

            String limpiarCasa =
                    "UPDATE casa SET Dpi = NULL " +
                    "WHERE Dpi = ?";

            PreparedStatement psCasa =
                    con.prepareStatement(limpiarCasa);

            psCasa.setLong(1, dpi);

            psCasa.executeUpdate();

            String sql =
                    "DELETE FROM propietario " +
                    "WHERE Dpi = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, dpi);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Propietario eliminado"
            );

            limpiarCampos();

            cargarPropietarios();

        } catch(SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error eliminando:\n"
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