package supermercadoSystem.dao;

import java.util.List;
import org.hibernate.query.Query;
import supermercadoSystem.entidades.Cliente;

public class ClienteDao extends GenericDao<Cliente> {

	public ClienteDao() {
		super(Cliente.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> filtrarClientes(String filtro) {
		iniciarTransaccion();
		String sql = "from tb_cliente where UPPER(nombre) like :filtro or UPPER(apellido) like :filtro or UPPER(documento) like :filtro order by id";
		Query<Cliente> query = getSession().createQuery(sql);
		query.setParameter("filtro", "%" + filtro.toUpperCase() + "%");
		List<Cliente> lista = query.getResultList();
		commit();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Cliente verificarCedula(String ci) {
		iniciarTransaccion();
		String sql = "from tb_cliente where UPPER(documento) like :filtro";
		Query<Cliente> query = getSession().createQuery(sql);
		query.setParameter("filtro", ci.toUpperCase());
		Cliente cliente;
		try {
			cliente = query.getSingleResult();
		} catch (Exception e) {
			cliente = null;
		}
		commit();
		return cliente;

	}

	@SuppressWarnings("unchecked")
	public List<Cliente> filtroListadoClientes(String dNombre, String hNombre, String dApellido, String hApellido,
			String order) {
		iniciarTransaccion();

		String sql = "from tb_cliente where UPPER(nombre)BETWEEN :dNombre and :hNombre "
				+ "and UPPER(apellido) BETWEEN :dApellido and :hApellido " + "order by " + order.toLowerCase();

		Query<Cliente> query = getSession().createQuery(sql);

		query.setParameter("dNombre", dNombre.toUpperCase());
		query.setParameter("hNombre", hNombre.toUpperCase() + "zzzzz");
		query.setParameter("dApellido", dApellido.toUpperCase());
		query.setParameter("hApellido", hApellido.toUpperCase() + "zzzzz");

		List<Cliente> lista = query.getResultList();
		commit();
		return lista;
	}

}
