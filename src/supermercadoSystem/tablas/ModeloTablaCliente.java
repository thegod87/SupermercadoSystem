package supermercadoSystem.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import supermercadoSystem.entidades.Cliente;

@SuppressWarnings("serial")
public class ModeloTablaCliente extends AbstractTableModel {

	private String[] columnas = { "ID", "Nombre", "Apellido", "Documento" };
	private List<Cliente> lista = new ArrayList<Cliente>();

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return lista.get(r).getId();

		case 1:
			return lista.get(r).getNombre();

		case 2:
			return lista.get(r).getApellido();

		case 3:
			return lista.get(r).getDocumento();

		}
		return null;
	}

}
