package supermercadoSystem.buscadores;

import java.util.List;
import javax.swing.JDialog;
import javax.swing.JTextField;
import supermercadoSystem.dao.PagoDao;
import supermercadoSystem.entidades.Pago;
import supermercadoSystem.interfaces.InterfacePagos;
import supermercadoSystem.tablas.ModeloTablaPago;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class BuscadorPago extends JDialog {
	private JTextField tfBuscador;
	private JScrollPane scrollPane;
	private JTable table;
	private PagoDao pagoDao;
	private List<Pago> pagos;
	private InterfacePagos interfacePagos;
	private ModeloTablaPago modeloTablaPago;

	public void setInterface(InterfacePagos interfacePagos) {
		this.interfacePagos = interfacePagos;
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorPago() {
		setTitle("Buscador Pago");
		setBounds(100, 100, 450, 450);
		setModal(true);
		setLocationRelativeTo(this);

		tfBuscador = new JTextField();
		tfBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					filtrarPago();
				}
			}
		});
		getContentPane().add(tfBuscador, BorderLayout.NORTH);
		tfBuscador.setColumns(10);

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		modeloTablaPago = new ModeloTablaPago();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					seleccionarPago(table.getSelectedRow());
				}
			}
		});
		table.setModel(modeloTablaPago);
		scrollPane.setViewportView(table);
		pagoDao = new PagoDao();
		filtrarPago();
	}

	private void filtrarPago() {
		pagos = pagoDao.filtrarPago(tfBuscador.getText());
		modeloTablaPago.setLista(pagos);

	}

	private void seleccionarPago(int index) {
		if (index < 0)
			return;
		interfacePagos.seleccionarPago(pagos.get(index));
		dispose();
	}

}
