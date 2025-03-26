package supermercadoSystem.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import py.edu.cursojava.dao.CategoriaDao;
import py.edu.cursojava.dao.MarcaDao;
import py.edu.cursojava.dao.ProductoDao;
import py.edu.cursojava.entidades.Categoria;
import py.edu.cursojava.entidades.Marca;
import py.edu.cursojava.entidades.Producto;
import py.edu.cursojava.tablas.ModeloTablaListadoProducto;
import py.edu.cursojava.utilidades.ConexionReportes;
import py.edu.cursojava.utilidades.UtilidadesFecha;
import py.edu.cursojava.vistas.ListadoProductoVentana;

public class ListadoProductoController {
	
	private List<Producto> productos;
	private ModeloTablaListadoProducto modeloTabla;
	private ProductoDao dao;
	private ListadoProductoVentana ventana;
	private String desde, hasta;
	
	
	public ListadoProductoController(ListadoProductoVentana listadoProductoVentana) {
		super();
		this.ventana = listadoProductoVentana;
		dao = new ProductoDao();
		modeloTabla = new ModeloTablaListadoProducto();
		this.ventana.getTable().setModel(modeloTabla);
		cargarComboBox();
		filtrar();
		setUpEvents();
	}
	
	private void setUpEvents() {
		ventana.getBtnFiltrar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrar();
			}
		});
		ventana.getBtnImprimir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				imprimir();
			}
		});
	}
	
	private void cargarComboBox() {
		CategoriaDao categoriaDao = new CategoriaDao();
		List<Categoria> categorias = categoriaDao.recuperarTodos();
		ventana.getCbCategoria().addItem("Todo");
		for (int i = 0; i < categorias.size(); i++) {
			ventana.getCbCategoria().addItem(categorias.get(i).getDescripcion());
		}
		
		MarcaDao marcaDao = new MarcaDao();
		List<Marca> marcas = marcaDao.recuperarTodos();
		ventana.getCbMarca().addItem("Todo");
		for (int i = 0; i < marcas.size(); i++) {
			ventana.getCbMarca().addItem(marcas.get(i).getDescripcion());
		}
	}
	
	private void filtrar() {
		cargarFiltros();
		productos = dao.filtroListadoProducto(ventana.getTfDesde().getText(), ventana.getTfHasta().getText(), ventana.getCbCategoria().getSelectedItem().toString(), 
				ventana.getCbMarca().getSelectedItem().toString(), ventana.getCbOrder().getSelectedItem().toString());
		modeloTabla.setLista(productos);
		if (productos.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
		}else {
			this.ventana.getBtnImprimir().setEnabled(true);
		}
	}
	
	private void cargarFiltros() {
		desde = "A";
		hasta = "Z";
		if(!ventana.getTfDesde().getText().isEmpty()) desde = ventana.getTfDesde().getText();
		if(!ventana.getTfHasta().getText().isEmpty()) hasta = ventana.getTfHasta().getText();
	}

	private void imprimir() {
		//Pasando parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desde", desde);
		parametros.put("hasta", hasta);
		parametros.put("categoria", ventana.getCbCategoria().getSelectedItem().toString());
		parametros.put("marca", ventana.getCbMarca().getSelectedItem().toString());
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));
		parametros.put("order", this.ventana.getCbOrder().getSelectedItem().toString());
		
		//Creando reporte
		ConexionReportes<Producto> conexionReportes = new ConexionReportes<Producto>();
		try {
			conexionReportes.generarReporte(productos, parametros, "ListadoProductos");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
}
