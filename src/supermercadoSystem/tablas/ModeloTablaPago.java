package supermercadoSystem.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import supermercadoSystem.entidades.Pago;
import supermercadoSystem.utilidades.UtilidadesFecha;
import supermercadoSystem.utilidades.UtilidadesNumeros;

@SuppressWarnings("serial")
public class ModeloTablaPago extends AbstractTableModel {

	private String[] columnas = { "Cliente", "Numero de Reserva", "Fecha de Pago", "Total a Pagar", "Estado" };
	private List<Pago> lista = new ArrayList<Pago>();

	public void setLista(List<Pago> lista) {
		this.lista = lista;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return lista.get(r).getVenta().getCliente().getNombre() + " "
					+ lista.get(r).getVenta().getCliente().getApellido();

		case 1:
			return lista.get(r).getVenta().getId();

		case 2:
			return UtilidadesFecha.fechaAString(lista.get(r).getFecha());

		case 3:
			return UtilidadesNumeros.doubleAString(lista.get(r).getTotal());

		case 4:
			if (lista.get(r).getEstado()) {
				return "Pago";
			} else {
				return "Pendiente";
			}

		}
		return null;
	}

}
