package supermercadoSystem.transacciones;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import power.tech.componentes.JtextFielPersonalizado;
import power.tech.controlador.PagoController;
import power.tech.utilidades.UtilidadesFecha;
import py.com.cs.xnumberfield.component.NumberTextField;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JToolBar;
import power.tech.componentes.BotonesJtoolsbarPersonalizados2;
import java.awt.Cursor;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PagoVentana extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private NumberTextField tfCostoTotal;
	private BotonesJtoolsbarPersonalizados2 btnProcesar;
	private BotonesJtoolsbarPersonalizados2 btnCancelar;
	private BotonesJtoolsbarPersonalizados2 btnSalir;
	private JToolBar toolBar2;
	private JButton btnNumeroDeReserva;
	private JtextFielPersonalizado tfNumeroDeReserva;
	private JtextFielPersonalizado tfCedula;
	private JFormattedTextField tfFechaDePago;
	private JtextFielPersonalizado tfNombre;
	private BotonesJtoolsbarPersonalizados2 btnAnular;
	private JTable table;
	private JtextFielPersonalizado tfNumeroDePago;
	private JButton btnNumeroDePago;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PagoVentana dialog = new PagoVentana();
			dialog.setUpController();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUpController() {
		new PagoController(this);
	}

	/**
	 * Create the dialog.
	 */
	public PagoVentana() {
		setTitle("Pagos");
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(14, 120, 853, 87);
		contentPanel.add(panel);
		panel.setLayout(null);

		tfNombre = new JtextFielPersonalizado();
		tfNombre.setBorder(null);
		tfNombre.setEditable(false);
		tfNombre.setBounds(381, 52, 217, 18);
		panel.add(tfNombre);

		JLabel label_4 = new JLabel("Nombre:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(321, 54, 47, 14);
		panel.add(label_4);

		JLabel label_5 = new JLabel("Fecha de pago:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(321, 13, 84, 14);
		panel.add(label_5);

		tfFechaDePago = new JFormattedTextField(UtilidadesFecha.getFormato());
		tfFechaDePago.setBorder(null);
		tfFechaDePago.setEditable(false);
		tfFechaDePago.setBounds(415, 11, 73, 18);
		panel.add(tfFechaDePago);

		btnNumeroDeReserva = new JButton("");
		btnNumeroDeReserva.setIcon(new ImageIcon(PagoVentana.class.getResource("/power/tech/img/pesquisar.png")));
		btnNumeroDeReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNumeroDeReserva.setBounds(188, 11, 63, 20);
		panel.add(btnNumeroDeReserva);

		tfNumeroDeReserva = new JtextFielPersonalizado();
		tfNumeroDeReserva.setHorizontalAlignment(SwingConstants.CENTER);
		tfNumeroDeReserva.setBackground(new Color(230, 230, 250));
		tfNumeroDeReserva.setBounds(122, 13, 56, 18);
		panel.add(tfNumeroDeReserva);

		tfCedula = new JtextFielPersonalizado();
		tfCedula.setBorder(null);
		tfCedula.setEditable(false);
		tfCedula.setBounds(122, 52, 129, 18);
		panel.add(tfCedula);

		JLabel label_7 = new JLabel("Cedula:");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(77, 53, 41, 14);
		panel.add(label_7);

		JLabel label_8 = new JLabel("N\u00B0 de Reserva:");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(35, 15, 83, 14);
		panel.add(label_8);

		JLabel label_6 = new JLabel("N\u00B0 de Pago:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(608, 13, 73, 14);
		panel.add(label_6);

		tfNumeroDePago = new JtextFielPersonalizado();
		tfNumeroDePago.setHorizontalAlignment(SwingConstants.CENTER);
		tfNumeroDePago.setBackground(new Color(230, 230, 250));
		tfNumeroDePago.setBounds(678, 11, 56, 18);
		panel.add(tfNumeroDePago);

		btnNumeroDePago = new JButton("");
		btnNumeroDePago.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNumeroDePago.setIcon(new ImageIcon(PagoVentana.class.getResource("/power/tech/img/pesquisar.png")));
		btnNumeroDePago.setBounds(748, 9, 63, 20);
		panel.add(btnNumeroDePago);

		JLabel lblNewLabel = new JLabel("Pagos de Reservas");
		lblNewLabel.setIconTextGap(10);
		lblNewLabel.setIcon(new ImageIcon(PagoVentana.class.getResource("/power/tech/img/pagostransacciones.png")));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
		lblNewLabel.setBounds(274, 23, 324, 50);
		contentPanel.add(lblNewLabel);

		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos para realizar el pago aqui:");
		lblIngreseLosDatos.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblIngreseLosDatos.setBounds(265, 84, 338, 20);
		contentPanel.add(lblIngreseLosDatos);

		JSeparator separator = new JSeparator();
		separator.setBounds(14, 115, 853, 7);
		contentPanel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(14, 210, 853, 7);
		contentPanel.add(separator_1);

		JLabel label_2 = new JLabel("PowerTech");
		label_2.setForeground(Color.GREEN);
		label_2.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		label_2.setBackground(SystemColor.inactiveCaptionBorder);
		label_2.setBounds(790, 11, 72, 14);
		contentPanel.add(label_2);

		toolBar2 = new JToolBar();
		toolBar2.setFloatable(false);
		toolBar2.setBounds(455, 504, 374, 77);
		contentPanel.add(toolBar2);

		btnCancelar = new BotonesJtoolsbarPersonalizados2();
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setText("Cancelar");
		toolBar2.add(btnCancelar);

		btnSalir = new BotonesJtoolsbarPersonalizados2();
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setText("Salir");
		toolBar2.add(btnSalir);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(61, 504, 374, 77);
		contentPanel.add(toolBar);
		toolBar.setFloatable(false);

		btnProcesar = new BotonesJtoolsbarPersonalizados2();
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar.add(btnProcesar);
		btnProcesar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProcesar.setIcon(new ImageIcon(PagoVentana.class.getResource("/power/tech/img/procesar.png")));
		btnProcesar.setText("Procesar");

		btnAnular = new BotonesJtoolsbarPersonalizados2();
		btnAnular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnular.setText("Anular");
		toolBar.add(btnAnular);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 255, 853, 218);
		contentPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel label = new JLabel("System");
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		label.setBackground(SystemColor.inactiveCaptionBorder);
		label.setBounds(809, 24, 35, 14);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("Costo total:");
		label_1.setBounds(653, 223, 83, 20);
		contentPanel.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		tfCostoTotal = new NumberTextField();
		tfCostoTotal.setBounds(746, 213, 115, 38);
		contentPanel.add(tfCostoTotal);
		tfCostoTotal.setDisabledTextColor(Color.BLACK);
		tfCostoTotal.setCaretColor(Color.BLACK);
		tfCostoTotal.setBorder(null);
		tfCostoTotal.setFont(new Font("Tahoma", Font.PLAIN, 22));
		tfCostoTotal.setEnabled(false);
		tfCostoTotal.setEditable(false);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public NumberTextField getTfCostoTotal() {
		return tfCostoTotal;
	}

	public void setTfCostoTotal(NumberTextField tfCostoTotal) {
		this.tfCostoTotal = tfCostoTotal;
	}

	public BotonesJtoolsbarPersonalizados2 getBtnProcesar() {
		return btnProcesar;
	}

	public void setBtnProcesar(BotonesJtoolsbarPersonalizados2 btnProcesar) {
		this.btnProcesar = btnProcesar;
	}

	public BotonesJtoolsbarPersonalizados2 getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(BotonesJtoolsbarPersonalizados2 btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public BotonesJtoolsbarPersonalizados2 getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(BotonesJtoolsbarPersonalizados2 btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JToolBar getToolBar2() {
		return toolBar2;
	}

	public void setToolBar2(JToolBar toolBar2) {
		this.toolBar2 = toolBar2;
	}

	public JButton getBtnNumeroDeReserva() {
		return btnNumeroDeReserva;
	}

	public void setBtnNumeroDeReserva(JButton btnNumeroDeReserva) {
		this.btnNumeroDeReserva = btnNumeroDeReserva;
	}

	public JtextFielPersonalizado getTfNumeroDeReserva() {
		return tfNumeroDeReserva;
	}

	public void setTfNumeroDeReserva(JtextFielPersonalizado tfNumeroDeReserva) {
		this.tfNumeroDeReserva = tfNumeroDeReserva;
	}

	public JtextFielPersonalizado getTfCedula() {
		return tfCedula;
	}

	public void setTfCedula(JtextFielPersonalizado tfCedula) {
		this.tfCedula = tfCedula;
	}

	public JtextFielPersonalizado getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JtextFielPersonalizado tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JFormattedTextField getTfFechaDePago() {
		return tfFechaDePago;
	}

	public void setTfFechaDePago(JFormattedTextField tfFechaDePago) {
		this.tfFechaDePago = tfFechaDePago;
	}

	public BotonesJtoolsbarPersonalizados2 getBtnAnular() {
		return btnAnular;
	}

	public void setBtnAnular(BotonesJtoolsbarPersonalizados2 btnAnular) {
		this.btnAnular = btnAnular;
	}

	public JtextFielPersonalizado getTfNumeroDePago() {
		return tfNumeroDePago;
	}

	public void setTfNumeroDePago(JtextFielPersonalizado tfNumeroDePago) {
		this.tfNumeroDePago = tfNumeroDePago;
	}

	public JButton getBtnNumeroDePago() {
		return btnNumeroDePago;
	}

	public void setBtnNumeroDePago(JButton btnNumeroDePago) {
		this.btnNumeroDePago = btnNumeroDePago;
	}

}
