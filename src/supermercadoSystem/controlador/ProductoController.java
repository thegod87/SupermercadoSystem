package supermercadoSystem.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.cursojava.buscadores.BuscadorCategoria;
import py.edu.cursojava.buscadores.BuscadorMarca;
import py.edu.cursojava.dao.ProductoDao;
import py.edu.cursojava.entidades.Categoria;
import py.edu.cursojava.entidades.Marca;
import py.edu.cursojava.entidades.Producto;
import py.edu.cursojava.interfaces.InterfaceAcciones;
import py.edu.cursojava.interfaces.InterfaceCategoria;
import py.edu.cursojava.interfaces.InterfaceMarca;
import py.edu.cursojava.tablas.ModeloTablaProducto;
import py.edu.cursojava.utilidades.EventosGenericos;
import py.edu.cursojava.utilidades.UtilidadesNumeros;
import py.edu.cursojava.vistas.ProductoVentana;

public class ProductoController implements InterfaceAcciones, InterfaceCategoria, InterfaceMarca {
	private Producto producto;
	private ProductoDao dao;
	private List<Producto> productos;
	private ModeloTablaProducto modeloTablaProducto;
	private Marca marca;
	private Categoria categoria;
	private ProductoVentana ventana;

	public ProductoController(ProductoVentana productoVentana) {
		super();
		this.ventana = productoVentana;
		this.ventana.setInterfaceAcciones(this);
		modeloTablaProducto = new ModeloTablaProducto();
		ventana.getTable().setModel(modeloTablaProducto);
		dao = new ProductoDao();
		estadoinicial();
		consultarProductos();
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
				if (e.getKeyChar()==KeyEvent.VK_ENTER) consultarProductos();
			}
		});
		ventana.getBtnCategoria().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorCategoria();
			}
		});
		ventana.getBtnMarca().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorMarca();
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
		ventana.getRdbtnActivo().setSelected(true);
		ventana.getRdbtnActivo().setEnabled(false);
		ventana.getBtnGuardar().setText("Guardar");
		ventana.getTable().setEnabled(true);
		
		ventana.getBtnCategoria().setEnabled(false);
		ventana.getBtnMarca().setEnabled(false);
	}

	@Override
	public void nuevo() {
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		EventosGenericos.estadosJtexField(ventana.getjPanelFormulario(), true);
		ventana.getRdbtnActivo().setEnabled(true);
		ventana.getTable().setEnabled(false);
		
		ventana.getBtnCategoria().setEnabled(true);
		ventana.getBtnMarca().setEnabled(true);
	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJtexField(ventana.getjPanelFormulario(), true);
		ventana.getRdbtnActivo().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnGuardar().setText("Actualizar");
		
		ventana.getBtnCategoria().setEnabled(true);
		ventana.getBtnMarca().setEnabled(true);
	}

	@Override
	public void eliminar() {
		if (producto==null) {
			JOptionPane.showMessageDialog(null, "Producto no encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas deguro que desea eliminar el producto "+producto.getDescripcion(),
				"Antenci�n", JOptionPane.YES_NO_OPTION);
		if (respuesta==JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(producto);
				dao.commit();
				estadoinicial();
				consultarProductos();
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
		producto = new Producto();
		cargarEntidad();
		try {
			dao.guardar(producto);
			dao.commit();
			consultarProductos();
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
			dao.guardar(producto);
			dao.commit();
			consultarProductos();
			estadoinicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}
	
	private void cargarEntidad() {
		producto.setCodigoBarra(ventana.getTfCodigo().getText());
		producto.setDescripcion(ventana.getTfDescripcion().getText());
		producto.setEstado(ventana.getRdbtnActivo().isSelected());
		producto.setExistencia(UtilidadesNumeros.stringAInteger(ventana.getTfExistencia().getText()));
		producto.setPrecioCompra(UtilidadesNumeros.stringADouble(ventana.getTfCompra().getText()));
		producto.setPrecioVenta(UtilidadesNumeros.stringADouble(ventana.getTfVenta().getText()));
		
		producto.setCategoria(categoria);
		producto.setMarca(marca);
	}
	
	private void consultarProductos() {
		productos = dao.filtrarProducto(this.ventana.getTfBuscador().getText());
		modeloTablaProducto.setLista(productos);
	}
	
	private void seleccionarRegistro(int index) {
		if(index<0)return;
		producto = productos.get(index);
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		
		ventana.getTfCodigo().setText(producto.getCodigoBarra());
		ventana.getTfDescripcion().setText(producto.getDescripcion());
		ventana.getRdbtnActivo().setSelected(producto.isEstado());
		ventana.getTfExistencia().setText(UtilidadesNumeros.doubleString(producto.getExistencia()));
		ventana.getTfCompra().setText(UtilidadesNumeros.doubleString(producto.getPrecioCompra()));
		ventana.getTfVenta().setText(UtilidadesNumeros.doubleString(producto.getPrecioVenta()));
		
		marca=producto.getMarca();
		categoria= producto.getCategoria();
		ventana.getTfCategoria().setText(categoria.getDescripcion());
		ventana.getTfMarca().setText(marca.getDescripcion());
	}
	
	private void abrirBuscadorCategoria() {
		BuscadorCategoria buscadorCategoria = new BuscadorCategoria();
		buscadorCategoria.setInterface(this);
		buscadorCategoria.setVisible(true);
	}
	
	@Override
	public void seleccionarCategoria(Categoria categoria) {
		this.categoria = categoria;
		ventana.getTfCategoria().setText(this.categoria.getDescripcion());
	}
	
	private void abrirBuscadorMarca() {
		BuscadorMarca buscadorMarca = new BuscadorMarca();
		buscadorMarca.setInterface(this);
		buscadorMarca.setVisible(true);
	}
	
	@Override
	public void seleccionarMarca(Marca marca) {
		this.marca = marca;
		ventana.getTfMarca().setText(marca.getDescripcion());
	}
	
	private boolean validarCampos() {
		if (ventana.getTfDescripcion().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo Descripci�n es obligatorio");
			return false;
		}
		if (ventana.getTfCodigo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo c�digo es obligatorio");
			return false;
		}
		if (ventana.getTfExistencia().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo existencia es obligatorio");
			return false;
		}
		if (ventana.getTfCompra().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo compra es obligatorio");
			return false;
		}
		if (ventana.getTfVenta().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo venta es obligatorio");
			return false;
		}
		if (categoria == null) {
			JOptionPane.showMessageDialog(null, "Seleccione una categoria");
			return false;
		}
		if (marca == null) {
			JOptionPane.showMessageDialog(null, "Seleccione una marca");
			return false;
		}
		if (UtilidadesNumeros.stringADouble(ventana.getTfVenta().getText())<UtilidadesNumeros.stringADouble(ventana.getTfCompra().getText())) {
			JOptionPane.showMessageDialog(null, "El precio de compra no puede ser mayor al precio de venta");
			return false;
		}
		return true;
	}

	
	
	

}
