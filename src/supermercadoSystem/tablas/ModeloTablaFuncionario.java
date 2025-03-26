package supermercadoSystem.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import supermercadoSystem.entidades.Funcionario;

@SuppressWarnings("serial")
public class ModeloTablaFuncionario extends AbstractTableModel {

	private String[] columnas = { "ID", "Nombre", "Apellido", "Documento", "Estado" };
	private List<Funcionario> lista = new ArrayList<Funcionario>();

	public void setLista(List<Funcionario> lista) {
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
			return lista.get(r).getId();
		case 1:
			return lista.get(r).getNombre();

		case 2:
			return lista.get(r).getApellido();

		case 3:
			return lista.get(r).getDocumento();

		case 4:
			if (lista.get(r).getEstado()) {
				return "Activo";
			} else {
				return "Inactivo";
			}

		}
		return null;
	}

}
