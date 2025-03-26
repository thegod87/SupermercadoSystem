package supermercadoSystem.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import supermercadoSystem.utilidades.ConnectionHelper;

public abstract class GenericDao<T> {

	protected Class<T> clase;

	public GenericDao(Class<T> clase) {
		this.clase = clase;
	}

	protected Session getSession() {
		return ConnectionHelper.getSessionFactory().getCurrentSession();
	}

	protected void iniciarTransaccion() {
		if (!getSession().getTransaction().isActive()) {
			getSession().beginTransaction();
		}
	}

	public void commit() {
		getSession().flush();
		getSession().getTransaction().commit();
	}

	public void rollback() {
		getSession().getTransaction().rollback();
	}

	public void guardar(T entity) throws Exception {
		iniciarTransaccion();
		getSession().saveOrUpdate(entity);
	}

	public void eliminar(T entity) throws Exception {
		iniciarTransaccion();
		getSession().delete(entity);
	}

	public T recuperarPorId(Serializable id) {
		iniciarTransaccion();
		T result = getSession().get(clase, id);
		commit();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> recuperarTodos() {
		iniciarTransaccion();
		String sql = "fron " + clase.getName() + " order by id";
		Query<T> query = getSession().createQuery(sql);
		List<T> lista = query.getResultList();
		commit();
		return lista;
	}

	@SuppressWarnings("rawtypes")
	public void inicializarTabla(String tabla) {
		iniciarTransaccion();
		String sql = "TRUNCATE TABLE " + tabla + " CASCADE";
		Query query = getSession().createNativeQuery(sql);
		query.executeUpdate();
	}

}
