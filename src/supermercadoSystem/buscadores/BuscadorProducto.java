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

import py.edu.cursojava.dao.ProductoDao;
import py.edu.cursojava.entidades.Producto;
import py.edu.cursojava.interfaces.InterfaceProducto;
import py.edu.cursojava.tablas.ModeloTablaProducto;

public class BuscadorProducto extends JDialog {
	private JTextField tfBuscador;
	private JTable table;
	private ProductoDao dao;
	private List<Producto> productos;
	private ModeloTablaProducto modeloTablaProducto;;
	private InterfaceProducto interfaceProducto;

	public void setInterface(InterfaceProducto interfaceProducto) {
		this.interfaceProducto = interfaceProducto;
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorProducto() {
		setBounds(100, 100, 650, 500);
		
		tfBuscador = new JTextField();
		tfBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) filtraRegistros();
			}
		});
		getContentPane().add(tfBuscador, BorderLayout.NORTH);
		tfBuscador.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 105, 358, 394);
		getContentPane().add(scrollPane);
		modeloTablaProducto = new ModeloTablaProducto();
		table = new JTable(modeloTablaProducto);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(table.getSelectedRow());
			}
		});
		scrollPane.setViewportView(table);
		
		dao = new ProductoDao();
		filtraRegistros();
	}
	
	private void filtraRegistros() {
		productos = dao.filtrarProducto(tfBuscador.getText());
		modeloTablaProducto.setLista(productos);
	}
	
	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceProducto.seleccionarporducto(productos.get(index));
		dispose();
	}

}
