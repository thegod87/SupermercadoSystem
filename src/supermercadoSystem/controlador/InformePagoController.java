package supermercadoSystem.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import power.tech.dao.PagoDao;
import power.tech.entidades.Pago;
import power.tech.entidades.Reserva;
import power.tech.tablas.ModeloTablaPago;
import power.tech.transacciones.InformePagoVentana;
import power.tech.utilidades.ConexionReportes;
import power.tech.utilidades.UtilidadesFecha;

public class InformePagoController {

	private List<Pago> pagos;
	private ModeloTablaPago modeloTablaPago;
	private PagoDao dao;
	@SuppressWarnings("unused")
	private Reserva reservas;
	private InformePagoVentana ventana;
	private String desde, hasta, Nombre;

	public InformePagoController(InformePagoVentana informePagoVentana) {
		super();
		this.ventana = informePagoVentana;
		dao = new PagoDao();
		modeloTablaPago = new ModeloTablaPago();
		this.ventana.getTable().setModel(modeloTablaPago);
		setUpEvents();

	}

	private void setUpEvents() {
		ventana.getBtnFiltrar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});

		ventana.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});

		ventana.getBtnImprimir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imprimir();
			}
		});
	}

	private void filtrar() {
		cargarFiltros();
		pagos = dao.filtroInformePago(desde, hasta, ventana.getTfNombre().getText());
		modeloTablaPago.setLista(pagos);

		if (pagos.size() <= 0) {
			this.ventana.getBtnImprimir().setEnabled(false);
		} else {
			this.ventana.getBtnImprimir().setEnabled(true);
		}
	}

	private void salir() {
		ventana.dispose();

	}

	private void imprimir() {
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desdeFecha", desde);
		parametros.put("hastaFecha", hasta);
		parametros.put("Nombre", Nombre);
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));

		ConexionReportes<Pago> conexionReportes = new ConexionReportes<Pago>();
		try {
			conexionReportes.generarReporte(pagos, parametros, "InformePago");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	private void cargarFiltros() {
		Nombre =  "Todos";
		desde = "11/10/2000";
		hasta = UtilidadesFecha.fechaAString(new Date());
		if (UtilidadesFecha.stringAFecha(ventana.getTfDesdeFecha().getText()) != null)
			desde = ventana.getTfDesdeFecha().getText();
		if (UtilidadesFecha.stringAFecha(ventana.getTfHastaFecha().getText()) != null)
			hasta = ventana.getTfHastaFecha().getText();

	}

}
