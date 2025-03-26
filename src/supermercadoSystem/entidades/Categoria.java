package supermercadoSystem.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_categorias")
public class Categoria {
	@Id
	@GenericGenerator(name="cat_generator", strategy="increment")
	@GeneratedValue(generator="cat_generator")
	
	@Column(name="cat_id")
	private int id;
	
	@Column(name="cat_nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name="cat_descripcion", nullable = false, length = 100)
	private String descripcion;
	
	@OneToMany(mappedBy="categoria")
	private List<Producto> productos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
