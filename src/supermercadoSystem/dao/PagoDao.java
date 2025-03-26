package supermercadoSystem.dao;

import java.util.List;
import org.hibernate.query.Query;
import supermercadoSystem.entidades.Pago;

public class PagoDao extends GenericDao<Pago> {

	private javax.persistence.Query query;

	public PagoDao() {
		super(Pago.class);
	}

	@SuppressWarnings("unchecked")
	public List<Pago> filtrarPago(String filtro) {
		iniciarTransaccion();
		String sql = "from tb_pago where UPPER(reserva.cliente.nombre) like :filtro or UPPER(reserva.cliente.apellido) like :filtro order by id";
		Query<Pago> query = getSession().createQuery(sql);
		query.setParameter("filtro", "%" + filtro.toUpperCase() + "%");
		List<Pago> lista = query.getResultList();
		commit();
		return lista;
	}

	@SuppressWarnings("unused")
	public int crearNuevoId() {
		iniciarTransaccion();
		String sql = "select max(id) from tb_pago";
		try {
			return ((int) query.getSingleResult()) + 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pago> filtroInformePago(String desde, String hasta, String cliente) {

		iniciarTransaccion();

		String sql = "from tb_pago where fecha >= '" + desde + " 00:00:00' and fecha <= '" + hasta + " 23:59:59'"
				+ " and UPPER(reserva.cliente.nombre) like :cliente order by id";

		Query<Pago> query = getSession().createQuery(sql);
		query.setParameter("cliente", "%" + cliente + "%");
		List<Pago> lista = query.getResultList();
		commit();
		return lista;
	}

}
