package supermercadoSystem.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import py.edu.cursojava.dao.VentaDao;
import py.edu.cursojava.entidades.Cliente;
import py.edu.cursojava.entidades.Venta;
import py.edu.cursojava.tablas.ModeloTablaVenta;
import py.edu.cursojava.utilidades.ConexionReportes;
import py.edu.cursojava.utilidades.UtilidadesFecha;
import py.edu.cursojava.vistas.InformeVentaVentana;

public class InformeVentasController {
	private List<Venta> ventas;
	private VentaDao dao;
	private InformeVentaVentana ventana;
	private ModeloTablaVenta modeloTablaVenta;
	private String desde, hasta;

	public InformeVentasController( InformeVentaVentana informeVentaVentana) {
		super();
		ventana = informeVentaVentana;
		dao = new VentaDao();
		modeloTablaVenta = new ModeloTablaVenta();
		ventana.getTable().setModel(modeloTablaVenta);
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
	
	private void filtrar() {
		cargarFiltros();
		ventas = dao.filtroInformeVenta(desde, hasta, ventana.getTfCliente().getText(), ventana.getTfFuncionario().getText());
		modeloTablaVenta.setLista(ventas);
		if (ventas.size()>0) {
			ventana.getBtnImprimir().setEnabled(true);
		}else {
			ventana.getBtnImprimir().setEnabled(false);
		}
	}
	
	private void cargarFiltros() {
		desde = "11/11/1000";
		hasta = UtilidadesFecha.fechaAString(new Date());
		if(UtilidadesFecha.stringAFecha(ventana.getTfDesdefecha().getText())!=null) desde = ventana.getTfDesdefecha().getText();
		if(UtilidadesFecha.stringAFecha(ventana.getTfHastaFecha().getText())!=null) hasta = ventana.getTfHastaFecha().getText();
	}
	
	private void imprimir() {
		//Pasando parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desde", desde);
		parametros.put("hasta", hasta);
		parametros.put("cliente", ventana.getTfCliente().getText());
		parametros.put("funcionario", ventana.getTfFuncionario().getText());
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));
				
		//Creando reporte
		ConexionReportes<Venta> conexionReportes = new ConexionReportes<Venta>();
		try {
			if(ventana.getCbInforme().getSelectedIndex()==0) {
				conexionReportes.generarReporte(ventas, parametros, "InformeVentasSimples");
			}else {
				conexionReportes.generarReporte(ventas, parametros, "InformeVentasDetallado");
			}
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}		
	}
	
	

}
