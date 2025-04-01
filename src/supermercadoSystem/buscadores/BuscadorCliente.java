package supermercadoSystem.buscadores;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import supermercadoSystem.dao.ClienteDao;
import supermercadoSystem.entidades.Cliente;
import supermercadoSystem.interfaces.InterfaceCliente;
import supermercadoSystem.tablas.ModeloTablaCliente;

@SuppressWarnings("serial")
public class BuscadorCliente extends JDialog {
	private JTextField tfBuscador;
	private List<Cliente> clientes;
	private ClienteDao dao;
	private ModeloTablaCliente modeloTablaCliente;
	private InterfaceCliente interfaceCliente;
	private JTable table;
	
	public void setInterface(InterfaceCliente interfaceCliente) {
		this.interfaceCliente = interfaceCliente;
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorCliente() {
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(new BorderLayout());
		
		tfBuscador = new JTextField();
		tfBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) filtrarRegistros();
			}
		});
		getContentPane().add(tfBuscador, BorderLayout.NORTH);
		tfBuscador.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 105, 358, 394);
		getContentPane().add(scrollPane);
		
		modeloTablaCliente = new ModeloTablaCliente();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(table.getSelectedRow());
			}
		});
		table.setModel(modeloTablaCliente);
		scrollPane.setViewportView(table);
		
		dao = new ClienteDao();
		filtrarRegistros();
	}
	
	private void filtrarRegistros() {
		clientes = dao.filtrarClientes(tfBuscador.getText());
		modeloTablaCliente.setLista(clientes);
	}
	
	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceCliente.seleccionarCliente(clientes.get(index));
		dispose();
	}

}
