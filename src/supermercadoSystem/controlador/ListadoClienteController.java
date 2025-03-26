package supermercadoSystem.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import py.edu.cursojava.dao.ClienteDao;
import py.edu.cursojava.entidades.Cliente;
import py.edu.cursojava.tablas.ModeloTablaListadoCliente;
import py.edu.cursojava.utilidades.ConexionReportes;
import py.edu.cursojava.utilidades.UtilidadesFecha;
import py.edu.cursojava.vistas.ListadoClientesVentana;

public class ListadoClienteController {
	
	private List<Cliente> clientes;
	private ModeloTablaListadoCliente modeloTablaListadoCliente;
	private ClienteDao dao;
	private ListadoClientesVentana ventana;
	private String dNombre, hNombre, dApellido, hApellido;

	public ListadoClienteController(ListadoClientesVentana listadoClientesVentana) {
		super();
		this.ventana = listadoClientesVentana;
		dao = new ClienteDao();
		modeloTablaListadoCliente = new ModeloTablaListadoCliente();
		this.ventana.getTable().setModel(modeloTablaListadoCliente);
		
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
	
	private void filtrar() {
		cargarFiltros();
		clientes = dao.filtroListadoClientes(dNombre, hNombre, dApellido, hApellido, this.ventana.getCbOrder().getSelectedItem().toString());
		modeloTablaListadoCliente.setLista(clientes);
		if (clientes.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
		}else {
			this.ventana.getBtnImprimir().setEnabled(true);
		}
	}
	
	private void cargarFiltros() {
		dNombre = "A";
		hNombre = "Z";
		dApellido = "A";
		hApellido = "Z";
		if(!this.ventana.getTfDesdeNombre().getText().isEmpty()) dNombre = this.ventana.getTfDesdeNombre().getText();
		if(!this.ventana.getTfHastaNombre().getText().isEmpty()) hNombre = this.ventana.getTfHastaNombre().getText();
		if(!this.ventana.getTfDesdeApellido().getText().isEmpty()) dApellido = this.ventana.getTfDesdeApellido().getText();
		if(!this.ventana.getTfHastaApellido().getText().isEmpty()) hApellido = this.ventana.getTfHastaApellido().getText();
	}

	private void imprimir() {
		//Pasando parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desdeNombre", dNombre);
		parametros.put("hastaNombre", hNombre);
		parametros.put("desdeApellido", dApellido);
		parametros.put("hastaApellido", hApellido);
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));
		parametros.put("order", this.ventana.getCbOrder().getSelectedItem().toString());
		
		//Creando reporte
		ConexionReportes<Cliente> conexionReportes = new ConexionReportes<Cliente>();
		try {
			conexionReportes.generarReporte(clientes, parametros, "ListadoClientes");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
}
