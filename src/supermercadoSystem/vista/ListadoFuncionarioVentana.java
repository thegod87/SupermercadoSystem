package supermercadoSystem.vista;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JLabel;
import power.tech.componentes.JtextFielPersonalizado;
import power.tech.controlador.ListadoFuncionarioController;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.Color;

@SuppressWarnings("serial")
public class ListadoFuncionarioVentana extends JDialog {
	private JTable table;
	private JtextFielPersonalizado tfDesdeNombre;
	private JtextFielPersonalizado tfDesdeApellido;
	private JtextFielPersonalizado tfHastaNombre;
	private JtextFielPersonalizado tfHastaApellido;
	private JButton btnFiltrar;
	@SuppressWarnings("rawtypes")
	private JComboBox cbOrder;
	private JButton btnImprimir;
	private JScrollPane scrollPane;
	private JButton btnSalir;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoFuncionarioVentana dialog = new ListadoFuncionarioVentana();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setUpController();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setUpController() {
		new ListadoFuncionarioController(this);

	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListadoFuncionarioVentana() {
		setTitle("Listado de Funcionarios");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);

		JLabel lblDesdeNombre = new JLabel("Nombre:");
		lblDesdeNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesdeNombre.setBounds(69, 42, 48, 14);
		getContentPane().add(lblDesdeNombre);

		JLabel lblDesdeApellido = new JLabel("Apellido:");
		lblDesdeApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesdeApellido.setBounds(69, 72, 48, 14);
		getContentPane().add(lblDesdeApellido);

		JLabel lblHastaNombre = new JLabel("Desde");
		lblHastaNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHastaNombre.setBounds(172, 24, 41, 14);
		getContentPane().add(lblHastaNombre);

		JLabel lblHastaApellido = new JLabel("Hasta");
		lblHastaApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHastaApellido.setBounds(328, 24, 41, 14);
		getContentPane().add(lblHastaApellido);

		tfDesdeNombre = new JtextFielPersonalizado();
		tfDesdeNombre.setBounds(127, 40, 131, 17);
		getContentPane().add(tfDesdeNombre);

		tfDesdeApellido = new JtextFielPersonalizado();
		tfDesdeApellido.setBounds(127, 70, 131, 17);
		getContentPane().add(tfDesdeApellido);

		tfHastaNombre = new JtextFielPersonalizado();
		tfHastaNombre.setBounds(283, 40, 131, 17);
		getContentPane().add(tfHastaNombre);

		tfHastaApellido = new JtextFielPersonalizado();
		tfHastaApellido.setBounds(283, 70, 131, 17);
		getContentPane().add(tfHastaApellido);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrdenarPor.setBounds(483, 42, 74, 14);
		getContentPane().add(lblOrdenarPor);

		cbOrder = new JComboBox();
		cbOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbOrder.setModel(new DefaultComboBoxModel(new String[] { "Id", "Nombre", "Apellido" }));
		cbOrder.setBounds(567, 39, 142, 20);
		getContentPane().add(cbOrder);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFiltrar.setHorizontalTextPosition(SwingConstants.LEFT);
		btnFiltrar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnFiltrar.setIcon(
				new ImageIcon(ListadoFuncionarioVentana.class.getResource("/power/tech/img/filtrarlistado.png")));
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFiltrar.setBounds(467, 65, 89, 28);
		getContentPane().add(btnFiltrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 764, 380);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setHorizontalAlignment(SwingConstants.TRAILING);
		btnImprimir.setIconTextGap(10);
		btnImprimir.setHorizontalTextPosition(SwingConstants.LEFT);
		btnImprimir.setIcon(
				new ImageIcon(ListadoFuncionarioVentana.class.getResource("/power/tech/img/imprimirlistado.png")));
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImprimir.setEnabled(false);
		btnImprimir.setBounds(459, 501, 142, 49);
		getContentPane().add(btnImprimir);

		btnSalir = new JButton("Salir");
		btnSalir.setIcon(
				new ImageIcon(ListadoFuncionarioVentana.class.getResource("/power/tech/img/salirlistado.png")));
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setIconTextGap(10);
		btnSalir.setHorizontalTextPosition(SwingConstants.LEFT);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalir.setBounds(630, 501, 142, 49);
		getContentPane().add(btnSalir);

		label = new JLabel("-");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(261, 42, 20, 14);
		getContentPane().add(label);

		label_1 = new JLabel("-");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(261, 72, 20, 14);
		getContentPane().add(label_1);

		label_2 = new JLabel("PowerTech");
		label_2.setForeground(Color.GREEN);
		label_2.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		label_2.setBackground(SystemColor.inactiveCaptionBorder);
		label_2.setBounds(702, 11, 72, 14);
		getContentPane().add(label_2);

		label_3 = new JLabel("System");
		label_3.setForeground(Color.GRAY);
		label_3.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		label_3.setBackground(SystemColor.inactiveCaptionBorder);
		label_3.setBounds(721, 23, 35, 14);
		getContentPane().add(label_3);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JtextFielPersonalizado getTfDesdeNombre() {
		return tfDesdeNombre;
	}

	public void setTfDesdeNombre(JtextFielPersonalizado tfDesdeNombre) {
		this.tfDesdeNombre = tfDesdeNombre;
	}

	public JtextFielPersonalizado getTfDesdeApellido() {
		return tfDesdeApellido;
	}

	public void setTfDesdeApellido(JtextFielPersonalizado tfDesdeApellido) {
		this.tfDesdeApellido = tfDesdeApellido;
	}

	public JtextFielPersonalizado getTfHastaNombre() {
		return tfHastaNombre;
	}

	public void setTfHastaNombre(JtextFielPersonalizado tfHastaNombre) {
		this.tfHastaNombre = tfHastaNombre;
	}

	public JtextFielPersonalizado getTfHastaApellido() {
		return tfHastaApellido;
	}

	public void setTfHastaApellido(JtextFielPersonalizado tfHastaApellido) {
		this.tfHastaApellido = tfHastaApellido;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbOrder() {
		return cbOrder;
	}

	@SuppressWarnings("rawtypes")
	public void setCbOrder(JComboBox cbOrder) {
		this.cbOrder = cbOrder;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

}
