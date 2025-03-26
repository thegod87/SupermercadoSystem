package supermercadoSystem.dao;

import java.util.List;
import org.hibernate.query.Query;
import supermercadoSystem.entidades.Venta;

public class VentaDao extends GenericDao<Venta> {

	public VentaDao() {
		super(Venta.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Venta> filtroInformeVenta(String desde, String hasta, String cliente, String funcionario){
		iniciarTransaccion();
		
		String sql = "from tb_ventas where fecha >= '"+desde+" 00:00:00' and fecha <= '"+hasta+" 23:59:59' "
				+ " and UPPER(cliente.nombre) like :cliente and UPPER(funcionario.nombre) like :funcionario order by id";
		
		Query<Venta> query = getSession().createQuery(sql);
		query.setParameter("cliente", "%"+cliente.toUpperCase()+"%");
		query.setParameter("funcionario", "%"+funcionario.toUpperCase()+"%");
		List<Venta> lista = query.getResultList();
		commit();
		return lista;
	}
	
	public int crearNuevoid() {
		iniciarTransaccion();
		String sql = "select max(id) from tb_ventas";
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(sql);
		try {
			return ((int) query.getSingleResult())+1;
		} catch (Exception e) {
			return 0;
		}
	}

}
