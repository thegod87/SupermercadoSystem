package supermercadoSystem.dao;

import java.util.List;
import org.hibernate.query.Query;
import supermercadoSystem.entidades.Categoria;

public class CategoriaDao extends GenericDao<Categoria> {

	public CategoriaDao() {
		super(Categoria.class);
	}
	
	public List<Categoria> filtrarCategoria(String filtro){
		iniciarTransaccion();
		String sql = "from Categoria where UPPER(descripcion) like :filtro order by id";
		@SuppressWarnings("unchecked")
		Query<Categoria> query = getSession().createQuery(sql);
		query.setParameter("filtro", "%"+filtro.toUpperCase()+"%");
		List<Categoria> lista = query.getResultList();
		return lista;
	}
	public int crearNuevoid() {
		iniciarTransaccion();
		String sql = "select max(id) from Categoria";
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(sql);
		try {
			return ((int) query.getSingleResult())+1;
		} catch (Exception e) {
			return 0;
		}
	}
}
