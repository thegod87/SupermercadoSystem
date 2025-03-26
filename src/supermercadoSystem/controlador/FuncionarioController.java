package supermercadoSystem.controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.cursojava.dao.FuncionarioDao;
import py.edu.cursojava.entidades.Funcionario;
import py.edu.cursojava.interfaces.InterfaceAcciones;
import py.edu.cursojava.tablas.ModeloTablaFuncionario;
import py.edu.cursojava.utilidades.EventosGenericos;
import py.edu.cursojava.utilidades.UtilidadesFecha;
import py.edu.cursojava.vistas.FuncionarioVentana;

public class FuncionarioController implements InterfaceAcciones {
	
	private FuncionarioVentana ventana;
	private ModeloTablaFuncionario modeloTablaFuncionario;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private FuncionarioDao dao;
	

	public FuncionarioController(FuncionarioVentana funcionarioVentana) {
		super();
		this.ventana= funcionarioVentana;
		this.ventana.setInterfaceAcciones(this);
		modeloTablaFuncionario = new ModeloTablaFuncionario();
		this.ventana.getTable().setModel(modeloTablaFuncionario);
		dao = new FuncionarioDao();
		consultarFuncionarios();
		estadoinicial();
		setUpEvents();
	}
	
	private void setUpEvents() {
		ventana.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(ventana.getTable().getSelectedRow());
			}
		});
		ventana.getTfBuscador().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) consultarFuncionarios();;
			}
		});

	}
	
	private void estadoinicial() {
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnNuevo().setEnabled(true);
		EventosGenericos.estadosJtexField(ventana.getjPanelFormulario(), false);
		EventosGenericos.limpiarJtexField(ventana.getjPanelFormulario());
		ventana.getTfFecha().setValue(null);;
		ventana.getChbEstado().setSelected(true);
		ventana.getCbCargo().setEnabled(false);
		ventana.getTfFecha().setEnabled(false);
		ventana.getChbEstado().setEnabled(false);
		ventana.getBtnGuardar().setText("Guardar");
		ventana.getTable().setEnabled(true);
	}

	@Override
	public void nuevo() {
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		EventosGenericos.estadosJtexField(ventana.getjPanelFormulario(), true);
		ventana.getTfFecha().setEnabled(true);
		ventana.getChbEstado().setEnabled(true);
		ventana.getCbCargo().setEnabled(true);
		ventana.getTable().setEnabled(false);
	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJtexField(ventana.getjPanelFormulario(), true);
		ventana.getTfFecha().setEnabled(true);
		ventana.getChbEstado().setEnabled(true);
		ventana.getCbCargo().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnGuardar().setText("Actualizar");
	}

	@Override
	public void eliminar() {
		if (funcionario==null) {
			JOptionPane.showMessageDialog(null, "Funcionario no encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas deguro que desea eliminar el funcionario "+funcionario.getNombre(),
				"Antenci�n", JOptionPane.YES_NO_OPTION);
		if (respuesta==JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(funcionario);
				dao.commit();
				estadoinicial();
				consultarFuncionarios();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		}
	}

	@Override
	public void salir() {
		ventana.dispose();
	}

	@Override
	public void cancelar() {
		estadoinicial();
	}

	@Override
	public void guardar() {
		if (!validarCampos()) return;
		funcionario = new Funcionario();
		cargarEntidad();
		try {
			dao.guardar(funcionario);
			dao.commit();
			consultarFuncionarios();
			estadoinicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void actualizar() {
		if (!validarCampos()) return;
		cargarEntidad();
		try {
			dao.guardar(funcionario);
			dao.commit();
			consultarFuncionarios();
			estadoinicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}
	
	private void cargarEntidad() {
		funcionario.setNombre(this.ventana.getTfNombre().getText());
		funcionario.setApellido(this.ventana.getTfApellido().getText());
		funcionario.setDocumento(this.ventana.getTfDocumento().getText());
		funcionario.setTelefono(this.ventana.getTfTelefono().getText());
		funcionario.setEmail(this.ventana.getTfEmail().getText());
		funcionario.setDireccion(this.ventana.getTfDireccion().getText());
		
		funcionario.setFechaRegistro(UtilidadesFecha.stringAFecha(this.ventana.getTfFecha().getText()));
		funcionario.setEstado(this.ventana.getChbEstado().isSelected());
		funcionario.setCargo(ventana.getCbCargo().getSelectedItem().toString());
	}
	
	private void seleccionarRegistro(int index) {
		if(index<0)return;
		funcionario = funcionarios.get(index);
		ventana.getTfNombre().setText(funcionario.getNombre());
		ventana.getTfApellido().setText(funcionario.getApellido());
		ventana.getTfDocumento().setText(funcionario.getDocumento());
		ventana.getTfTelefono().setText(funcionario.getTelefono());
		ventana.getTfDireccion().setText(funcionario.getDireccion());
		ventana.getTfEmail().setText(funcionario.getEmail());
		ventana.getTfFecha().setText(UtilidadesFecha.fechaAString(funcionario.getFechaRegistro()));
		ventana.getChbEstado().setSelected(funcionario.isEstado());
		ventana.getCbCargo().setSelectedItem(funcionario.getCargo());
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		
	}
	
	private void consultarFuncionarios() {
		funcionarios = dao.filtrarFuncionarios(this.ventana.getTfBuscador().getText());
		modeloTablaFuncionario.setLista(funcionarios);
	}
	
	private boolean validarCampos() {
		if (ventana.getTfNombre().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio");
			return false;
		}
		if (ventana.getTfApellido().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo Apellido es obligatorio");
			return false;
		}
		if (ventana.getTfDocumento().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo Documento es obligatorio");
			return false;
		}
		if (ventana.getTfTelefono().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo Telefono es obligatorio");
			return false;
		}
		if (ventana.getTfEmail().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo Email es obligatorio");
			return false;
		}
		if (ventana.getTfDireccion().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo Direcci�n es obligatorio");
			return false;
		}
		if (UtilidadesFecha.stringAFecha(ventana.getTfFecha().getText())==null) {
			JOptionPane.showMessageDialog(null, "Ingrese una fecha valida");
			return false;
		}
		if (dao.verificarCedula(ventana.getTfDocumento().getText())!=null) {
			if(funcionario!=null & funcionario.getId() == dao.verificarCedula(ventana.getTfDocumento().getText()).getId()) return true;
			JOptionPane.showMessageDialog(null, "Documento Duplicado");
			return false;
		}
		return true;
	}
	
	

}
