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

import py.edu.cursojava.dao.CategoriaDao;
import py.edu.cursojava.entidades.Categoria;
import py.edu.cursojava.interfaces.InterfaceCategoria;
import py.edu.cursojava.tablas.ModeloTablaCategoria;

public class BuscadorCategoria extends JDialog {
	private JTextField tfBuscador;
	private JTable table;
	private CategoriaDao categoriaDao;
	private List<Categoria> categorias;
	private InterfaceCategoria interfaceCategoria;
	private ModeloTablaCategoria modeloTablaCategoria;
	
	public void setInterface(InterfaceCategoria interfaceCategoria) {
		this.interfaceCategoria = interfaceCategoria;
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorCategoria() {
		setTitle("Buscador Categoria");
		setBounds(100, 100, 500, 500);
		setModal(true);
		setLocationRelativeTo(this);
		
		tfBuscador = new JTextField();
		tfBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) {
					filtrarCategoria();
				}
			}
		});
		getContentPane().add(tfBuscador, BorderLayout.NORTH);
		tfBuscador.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 105, 358, 394);
		getContentPane().add(scrollPane);
		
		modeloTablaCategoria = new ModeloTablaCategoria();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					seleccionarRegistro(table.getSelectedRow());
				}
			}
		});
		table.setModel(modeloTablaCategoria);
		scrollPane.setViewportView(table);
		categoriaDao = new CategoriaDao();
		filtrarCategoria();
	}
	
	private void filtrarCategoria() {
		categorias = categoriaDao.filtrarCategoria(tfBuscador.getText());
		modeloTablaCategoria.setLista(categorias);
	}
	
	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceCategoria.seleccionarCategoria(categorias.get(index));
		dispose();
	}
	
	

}
