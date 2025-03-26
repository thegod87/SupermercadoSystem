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

import py.edu.cursojava.dao.FuncionarioDao;
import py.edu.cursojava.entidades.Funcionario;
import py.edu.cursojava.interfaces.InterfaceFuncionario;
import py.edu.cursojava.tablas.ModeloTablaFuncionario;

public class BuscadorFuncionario extends JDialog {
	private JTextField tfBuscador;
	private JTable table;
	private FuncionarioDao dao;
	private List<Funcionario> funcionarios;
	private InterfaceFuncionario interfaceFuncionario;
	private ModeloTablaFuncionario modeloTablaFuncionario;
	
	public void setInterface(InterfaceFuncionario interfaceCategoria) {
		this.interfaceFuncionario = interfaceCategoria;
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorFuncionario() {
		setTitle("Buscador Funcionario");
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
		
		modeloTablaFuncionario = new ModeloTablaFuncionario();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					seleccionarRegistro(table.getSelectedRow());
				}
			}
		});
		table.setModel(modeloTablaFuncionario);
		scrollPane.setViewportView(table);
		dao = new FuncionarioDao();
		filtrarCategoria();
	}
	
	private void filtrarCategoria() {
		funcionarios = dao.filtrarFuncionarios(tfBuscador.getText());
		modeloTablaFuncionario.setLista(funcionarios);
	}
	
	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceFuncionario.seleccionarFuncionario(funcionarios.get(index));
		dispose();
	}
	
	

}
