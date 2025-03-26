package supermercadoSystem.dao;

import java.util.List;
import org.hibernate.query.Query;
import supermercadoSystem.entidades.Funcionario;

public class FuncionarioDao extends GenericDao<Funcionario> {

	public FuncionarioDao() {
		super(Funcionario.class);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> filtrarFuncionarios(String filtro) {
		iniciarTransaccion();
		String sql = "from tb_funcionario where UPPER(nombre) like :filtro or UPPER(apellido) like :filtro or UPPER(documento) like :filtro order by id";
		Query<Funcionario> query = getSession().createQuery(sql);
		query.setParameter("filtro", "%" + filtro.toUpperCase() + "%");
		List<Funcionario> lista = query.getResultList();
		commit();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Funcionario verificarCedula(String ci) {
		iniciarTransaccion();
		String sql = "from tb_funcionario where UPPER(documento) like :filtro";
		Query<Funcionario> query = getSession().createQuery(sql);
		query.setParameter("filtro", ci.toUpperCase());
		Funcionario funcionario;
		try {
			funcionario = query.getSingleResult();
		} catch (Exception e) {
			funcionario = null;
		}
		commit();
		return funcionario;

	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> filtroListadoFuncionarios(String dNombre, String hNombre, String dApellido,
			String hApellido, String order) {
		iniciarTransaccion();

		String sql = "from tb_funcionario where UPPER(nombre)BETWEEN :dNombre and :hNombre "
				+ "and UPPER(apellido) BETWEEN :dApellido and :hApellido " + "order by " + order.toLowerCase();

		Query<Funcionario> query = getSession().createQuery(sql);

		query.setParameter("dNombre", dNombre.toUpperCase());
		query.setParameter("hNombre", hNombre.toUpperCase() + "zzzzz");
		query.setParameter("dApellido", dApellido.toUpperCase());
		query.setParameter("hApellido", hApellido.toUpperCase() + "zzzzz");

		List<Funcionario> lista = query.getResultList();
		commit();
		return lista;

	}

}
