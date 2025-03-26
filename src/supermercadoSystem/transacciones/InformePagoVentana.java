package supermercadoSystem.transacciones;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import supermercadoSystem.componentes.JtextFielPersonalizado;
import supermercadoSystem.controlador.InformePagoController;
import supermercadoSystem.utilidades.UtilidadesFecha;
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
public class InformePagoVentana extends JDialog {
	private JTable table;
	private JtextFielPersonalizado tfNombre;
	private JButton btnFiltrar;
	private JButton btnImprimir;
	private JScrollPane scrollPane;
	private JButton btnSalir;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JFormattedTextField tfDesdeFecha;
	private JFormattedTextField tfHastaFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformePagoVentana dialog = new InformePagoVentana();
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
		new InformePagoController(this);

	}

	/**
	 * Create the dialog.
	 */
	public InformePagoVentana() {
		setTitle("Informe de Pago");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);

		JLabel lblDesdeNombre = new JLabel("Fecha:");
		lblDesdeNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesdeNombre.setBounds(178, 43, 44, 14);
		getContentPane().add(lblDesdeNombre);

		JLabel lblDesdeApellido = new JLabel("Nombre:");
		lblDesdeApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesdeApellido.setBounds(169, 73, 53, 14);
		getContentPane().add(lblDesdeApellido);

		JLabel lblHastaNombre = new JLabel("Desde");
		lblHastaNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHastaNombre.setBounds(253, 26, 44, 14);
		getContentPane().add(lblHastaNombre);

		JLabel lblHastaApellido = new JLabel("Hasta");
		lblHastaApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHastaApellido.setBounds(365, 26, 38, 14);
		getContentPane().add(lblHastaApellido);

		tfNombre = new JtextFielPersonalizado();
		tfNombre.setBounds(232, 72, 187, 17);
		getContentPane().add(tfNombre);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFiltrar.setHorizontalTextPosition(SwingConstants.LEFT);
		btnFiltrar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnFiltrar.setIcon(new ImageIcon(InformePagoVentana.class.getResource("/power/tech/img/filtrarlistado.png")));
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFiltrar.setBounds(513, 48, 89, 28);
		getContentPane().add(btnFiltrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 110, 764, 380);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setHorizontalAlignment(SwingConstants.TRAILING);
		btnImprimir.setIconTextGap(10);
		btnImprimir.setHorizontalTextPosition(SwingConstants.LEFT);
		btnImprimir.setIcon(new ImageIcon(InformePagoVentana.class.getResource("/power/tech/img/imprimirlistado.png")));
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImprimir.setEnabled(false);
		btnImprimir.setBounds(459, 501, 142, 49);
		getContentPane().add(btnImprimir);

		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(InformePagoVentana.class.getResource("/power/tech/img/salirlistado.png")));
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setIconTextGap(10);
		btnSalir.setHorizontalTextPosition(SwingConstants.LEFT);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalir.setBounds(630, 501, 142, 49);
		getContentPane().add(btnSalir);

		label = new JLabel("-");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(316, 43, 19, 14);
		getContentPane().add(label);

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

		tfDesdeFecha = new JFormattedTextField(UtilidadesFecha.getFormato());
		tfDesdeFecha.setBounds(232, 41, 74, 17);
		getContentPane().add(tfDesdeFecha);

		tfHastaFecha = new JFormattedTextField(UtilidadesFecha.getFormato());
		tfHastaFecha.setBounds(345, 41, 74, 17);
		getContentPane().add(tfHastaFecha);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JtextFielPersonalizado getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JtextFielPersonalizado tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
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

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JLabel getLabel_2() {
		return label_2;
	}

	public void setLabel_2(JLabel label_2) {
		this.label_2 = label_2;
	}

	public JLabel getLabel_3() {
		return label_3;
	}

	public void setLabel_3(JLabel label_3) {
		this.label_3 = label_3;
	}

	public JFormattedTextField getTfDesdeFecha() {
		return tfDesdeFecha;
	}

	public void setTfDesdeFecha(JFormattedTextField tfDesdeFecha) {
		this.tfDesdeFecha = tfDesdeFecha;
	}

	public JFormattedTextField getTfHastaFecha() {
		return tfHastaFecha;
	}

	public void setTfHastaFecha(JFormattedTextField tfHastaFecha) {
		this.tfHastaFecha = tfHastaFecha;
	}

}
