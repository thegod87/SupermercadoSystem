package supermercadoSystem.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import supermercadoSystem.entidades.Producto;

public class ModeloTablaListadoProducto extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private String[] columnas = {"Id", "Descripcion", "Marca", "Categoria", "Existencia", "P. Costo", "P. Venta"};
	private List<Producto> lista = new ArrayList<Producto>();
	
	public void setLista(List<Producto> lista) {
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
	
	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return lista.get(r).getId();
		case 1:
			return lista.get(r).getDescripcion();
		case 2:
			return lista.get(r).getCategoria().getDescripcion();
		case 3:
			return lista.get(r).getExistencia();
		case 4:
			return lista.get(r).getPrecioCompra();
		case 5:
			return lista.get(r).getPrecioVenta();
		}
		return null;
	}
}
