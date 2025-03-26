package supermercadoSystem.componentes;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import supermercadoSystem.interfaces.InterfaceAcciones;

@SuppressWarnings("serial")
public class jDialogoGenerico extends JDialog implements ActionListener {
	private BotonesJtoolsbarPersonalizados btnNuevo;
	private BotonesJtoolsbarPersonalizados btnModificar;
	private BotonesJtoolsbarPersonalizados btnEliminar;
	private BotonesJtoolsbarPersonalizados btnCancelar;
	private JPanel PanelFormulario;
	private JtextFielPersonalizado tfBuscador;
	private JLabel lblTitulo;
	private InterfaceAcciones interfaceAcciones;
	private JTable table;
	private BotonesJtoolsbarPersonalizados2 btnGuardar;
	private BotonesJtoolsbarPersonalizados2 btnSalir;
	private JScrollPane scrollPane;
	private JLabel lblBuscar;
	private JToolBar toolBar;
	private JToolBar toolBar_2;
	private BotonesJtoolsbarPersonalizados2 btn2Nuevo;
	private BotonesJtoolsbarPersonalizados2 btn2Cancelar;

	public void setInterfaceAcciones(InterfaceAcciones interfaceAcciones) {
		this.interfaceAcciones = interfaceAcciones;
	}

	/**
	 * Create the dialog.
	 */
	public jDialogoGenerico() {
		setBounds(100, 100, 900, 650);
		setResizable(false);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOpaque(false);
		toolBar.setBounds(20, 519, 405, 77);
		getContentPane().add(toolBar);

		btnNuevo = new BotonesJtoolsbarPersonalizados();
		toolBar.add(btnNuevo);
		btnNuevo.setText("Nuevo");

		btnModificar = new BotonesJtoolsbarPersonalizados();
		toolBar.add(btnModificar);
		btnModificar.setText("Modificar");

		btnEliminar = new BotonesJtoolsbarPersonalizados();
		toolBar.add(btnEliminar);
		btnEliminar.setText("Eliminar");

		btnCancelar = new BotonesJtoolsbarPersonalizados();
		toolBar.add(btnCancelar);
		btnCancelar.setText("Cancelar");

		PanelFormulario = new JPanel();
		PanelFormulario.setBounds(10, 78, 415, 422);
		getContentPane().add(PanelFormulario);
		PanelFormulario.setLayout(null);
		toolBar.setFloatable(false);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(435, 105, 440, 395);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBuscar.setBounds(435, 80, 49, 14);
		getContentPane().add(lblBuscar);

		tfBuscador = new JtextFielPersonalizado();
		tfBuscador.setBounds(494, 78, 381, 20);
		getContentPane().add(tfBuscador);

		lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(417, 26, 57, 22);
		getContentPane().add(lblTitulo);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setBounds(467, 519, 372, 77);
		getContentPane().add(toolBar_1);

		btnGuardar = new BotonesJtoolsbarPersonalizados2();
		toolBar_1.add(btnGuardar);
		btnGuardar.setText("Guardar");

		btnSalir = new BotonesJtoolsbarPersonalizados2();
		toolBar_1.add(btnSalir);
		btnSalir.setText("Salir");

		toolBar_2 = new JToolBar();
		toolBar_2.setFloatable(false);
		toolBar_2.setBounds(50, 519, 375, 77);
		getContentPane().add(toolBar_2);

		btn2Nuevo = new BotonesJtoolsbarPersonalizados2();
		btn2Nuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn2Nuevo.setText("Nuevo");
		toolBar_2.add(btn2Nuevo);

		btn2Cancelar = new BotonesJtoolsbarPersonalizados2();
		btn2Cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn2Cancelar.setText("Cancelar");
		toolBar_2.add(btn2Cancelar);

		setearEventos();
	}

	private void setearEventos() {
		btnNuevo.addActionListener(this);
		btnModificar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);
		btnSalir.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Nuevo":
			interfaceAcciones.nuevo();
			break;
		case "Guardar":
			interfaceAcciones.guardar();
			break;
		case "Actualizar":
			interfaceAcciones.actualizar();
			break;

		case "Modificar":
			interfaceAcciones.modificar();
			break;

		case "Eliminar":
			interfaceAcciones.eliminar();
			break;

		case "Cancelar":
			interfaceAcciones.cancelar();
			break;

		case "Salir":
			interfaceAcciones.salir();
			break;
		}
	}

	public BotonesJtoolsbarPersonalizados getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(BotonesJtoolsbarPersonalizados btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public BotonesJtoolsbarPersonalizados getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(BotonesJtoolsbarPersonalizados btnModificar) {
		this.btnModificar = btnModificar;
	}

	public BotonesJtoolsbarPersonalizados getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(BotonesJtoolsbarPersonalizados btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public BotonesJtoolsbarPersonalizados getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(BotonesJtoolsbarPersonalizados btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JPanel getPanelFormulario() {
		return PanelFormulario;
	}

	public void setPanelFormulario(JPanel panelFormulario) {
		PanelFormulario = panelFormulario;
	}

	public JtextFielPersonalizado getTfBuscador() {
		return tfBuscador;
	}

	public void setTfBuscador(JtextFielPersonalizado tfBuscador) {
		this.tfBuscador = tfBuscador;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public BotonesJtoolsbarPersonalizados2 getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(BotonesJtoolsbarPersonalizados2 btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public BotonesJtoolsbarPersonalizados2 getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(BotonesJtoolsbarPersonalizados2 btnSalir) {
		this.btnSalir = btnSalir;
	}

	public InterfaceAcciones getInterfaceAcciones() {
		return interfaceAcciones;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JLabel getLblBuscar() {
		return lblBuscar;
	}

	public void setLblBuscar(JLabel lblBuscar) {
		this.lblBuscar = lblBuscar;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public JToolBar getToolBar_2() {
		return toolBar_2;
	}

	public void setToolBar_2(JToolBar toolBar_2) {
		this.toolBar_2 = toolBar_2;
	}

	public BotonesJtoolsbarPersonalizados2 getBtn2Nuevo() {
		return btn2Nuevo;
	}

	public void setBtnsjtlsbrprsnlzds2Nuevo(BotonesJtoolsbarPersonalizados2 btnsjtlsbrprsnlzds2Nuevo) {
		this.btn2Nuevo = btnsjtlsbrprsnlzds2Nuevo;
	}

	public BotonesJtoolsbarPersonalizados2 getBtn2Cancelar() {
		return btn2Cancelar;
	}

	public void setBtnsjtlsbrprsnlzds2Cancelar(BotonesJtoolsbarPersonalizados2 btnsjtlsbrprsnlzds2Cancelar) {
		this.btn2Cancelar = btnsjtlsbrprsnlzds2Cancelar;
	}

}
