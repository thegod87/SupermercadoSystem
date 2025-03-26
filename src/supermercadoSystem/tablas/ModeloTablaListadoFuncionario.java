package supermercadoSystem.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import supermercadoSystem.entidades.Funcionario;

@SuppressWarnings("serial")
public class ModeloTablaListadoFuncionario extends AbstractTableModel {

	private String[] columnas = { "Id", "Nombre", "Apellido", "Documento", "Telofono", "Direccion" };
	private List<Funcionario> lista = new ArrayList<Funcionario>();

	public void setLista(List<Funcionario> funcionarios) {
		this.lista = funcionarios;
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

		case 4:
			return lista.get(r).getTelefono();

		case 5:
			return lista.get(r).getDireccion();
		}
		return null;
	}

}
