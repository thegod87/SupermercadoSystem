package supermercadoSystem.dao;

import java.util.List;

import org.hibernate.query.Query;

import supermercadoSystem.entidades.Producto;

public class ProductoDao extends GenericDao<Producto> {

	public ProductoDao() {
		super(Producto.class);
	}
	
	public List<Producto> filtrarProducto(String filtro){
		iniciarTransaccion();
		String sql = "from tb_productos where UPPER(descripcion) like :filtro order by id";
		@SuppressWarnings("unchecked")
		Query<Producto> query = getSession().createQuery(sql);
		query.setParameter("filtro", "%"+filtro.toUpperCase()+"%");
		List<Producto> lista = query.getResultList();
		commit();
		return lista;
	}
	
	public Producto filtrarProductoPorCodigo(String filtro){
		iniciarTransaccion();
		String sql = "from tb_productos where UPPER(codigoBarra) like :filtro";
		@SuppressWarnings("unchecked")
		Query<Producto> query = getSession().createQuery(sql);
		query.setParameter("filtro", filtro.toUpperCase());
		Producto producto;
		try {
			producto = query.getSingleResult();
		} catch (Exception e) {
			producto=null;
		}
		commit();
		return producto;
	}
	
	public List<Producto> filtroListadoProducto(String desde, String hasta, String categoria, String marca, String order){
		if (categoria.equals("Todo")) {
			categoria = "";
		}else {
			categoria = "and UPPER(categoria.descripcion) like '"+categoria.toUpperCase()+"'";
		}
		if (marca.equals("Todo")) {
			marca = "";
		}else {
			marca = "and UPPER(marca.descripcion) like '"+marca.toUpperCase()+"'";
		}
		iniciarTransaccion();
		String sql = "from tb_productos where UPPER(descripcion) BETWEEN :desde and :hasta "+marca+" "+categoria
				+ " order by "+order.toLowerCase();
		
		@SuppressWarnings("unchecked")
		Query<Producto> query = getSession().createQuery(sql);
		query.setParameter("desde", desde.toUpperCase());
		query.setParameter("hasta", hasta.toUpperCase()+"zzzzzz");
		
		System.out.println(sql);
		
		List<Producto> lista = query.getResultList();
		commit();
		return lista;
	}

}
