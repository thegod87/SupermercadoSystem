package supermercadoSystem.controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import supermercadoSystem.dao.CategoriaDao;
import supermercadoSystem.entidades.Categoria;
import supermercadoSystem.interfaces.InterfaceAcciones;
import supermercadoSystem.tablas.ModeloTablaCategoria;
import supermercadoSystem.utilidades.EventosGenericos;
import supermercadoSystem.vista.CategoriaVentana;

public class CategoriaController implements InterfaceAcciones{
	private Categoria categoria;
	private List<Categoria> categorias;
	private CategoriaDao dao;
	private ModeloTablaCategoria modeloTablaCategoria;
	private CategoriaVentana ventana;

	public CategoriaController(CategoriaVentana categoriaVentana) {
		super();
		this.ventana = categoriaVentana;
		this.ventana.setInterfaceAcciones(this);
		dao = new CategoriaDao();
		modeloTablaCategoria = new ModeloTablaCategoria();
		this.ventana.getTable().setModel(modeloTablaCategoria);
		recuperarCategorias();
		estadoInicial();
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
				if (e.getKeyChar()==KeyEvent.VK_ENTER) recuperarCategorias();;
			}
		});
	}
	
	private void estadoInicial() {
		EventosGenericos.estadosJtexField(ventana.getjPanelFormulario(), false);
		ventana.getBtnNuevo().setEnabled(true);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnGuardar().setText("Guardar");
		ventana.getBtnSalir().setEnabled(true);
		ventana.getTable().setEnabled(true);
	}

	@Override
	public void nuevo() {
		EventosGenericos.estadosJtexField(ventana.getjPanelFormulario(), true);
		this.ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnGuardar().setText("Guardar");
		ventana.getBtnSalir().setEnabled(false);
		ventana.getTable().setEnabled(false);
		ventana.getCbEstado().setEnabled(true);
		ventana.getTfId().setText(dao.crearNuevoid()+"");
	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJtexField(ventana.getjPanelFormulario(), true);
		this.ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnGuardar().setText("Actualizar");
		ventana.getBtnSalir().setEnabled(false);
		ventana.getTable().setEnabled(false);
		ventana.getCbEstado().setEnabled(true);
	}

	@Override
	public void eliminar() {
		if (categoria==null) {
			JOptionPane.showMessageDialog(null, "Seleccione una categoria");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar la categoria "+categoria.getDescripcion(), 
				"Atenci�n", JOptionPane.YES_NO_OPTION);
		if (respuesta== JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(categoria);
				dao.commit();
				cancelar();
				recuperarCategorias();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		}
	}

	@Override
	public void salir() {
		this.ventana.dispose();
	}

	@Override
	public void cancelar() {
		estadoInicial();
		EventosGenericos.limpiarJtexField(ventana.getjPanelFormulario());
	}

	@Override
	public void guardar() {
		if(!validarCampos()) return;
		categoria = new Categoria();
		cargarEntidad();
		try {
			dao.guardar(categoria);
			dao.commit();
			cancelar();
			recuperarCategorias();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void actualizar() {
		if(!validarCampos()) return;
		cargarEntidad();
		try {
			dao.guardar(categoria);
			dao.commit();
			cancelar();
			recuperarCategorias();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}
	
	private void cargarEntidad() {
		categoria.setDescripcion(ventana.getTfDescripcion().getText());
		categoria.setEstado(ventana.getCbEstado().isSelected());
	}
	
	private void recuperarCategorias() {
		categorias = dao.filtrarCategoria(this.ventana.getTfBuscador().getText());
		modeloTablaCategoria.setLista(categorias);
	}
	
	private void seleccionarRegistro(int index) {
		categoria = categorias.get(index);
		ventana.getTfId().setText(categoria.getId()+"");
		ventana.getTfDescripcion().setText(categoria.getDescripcion());
		ventana.getCbEstado().setSelected(categoria.isEstado());
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
	}
	
	private boolean validarCampos() {
		if (ventana.getTfDescripcion().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo descripci�n es obligatorio");
			ventana.getTfDescripcion().requestFocus();
			return false;
		}
		return true;
	}
}
