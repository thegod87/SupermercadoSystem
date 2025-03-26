package supermercadoSystem.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="tb_productos")
public class Producto {
	@Id
	@GenericGenerator(name="prod_generator", strategy="increment")
	@GeneratedValue(generator="prod_generator")
	
	@Column(name="prod_id")
	private int id;
	@Column(name="prod_codigo_barra", nullable = false, length = 100)
	
	private String codigoBarra;
	@Column(name="prod_descripcion", nullable = false, length = 100)
	
	private String descripcion;
	@Column(name="prod_existencia", nullable = false)
	
	private double existencia;
	@Column(name="prod_precio_compra", nullable = false)
	
	private double precioCompra;
	@Column(name="prod_precio_venta", nullable = false)
	
	private double precioVenta;
	@Column(name="prod_estado", nullable = false)
	
	private boolean estado;
	@OneToMany(mappedBy="producto")
	
	private List<VentaDetalle> ventaDetalles;
	
	@ManyToOne
	private Categoria categoria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getExistencia() {
		return existencia;
	}

	public void setExistencia(double existencia) {
		this.existencia = existencia;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<VentaDetalle> getVentaDetalles() {
		return ventaDetalles;
	}

	public void setVentaDetalles(List<VentaDetalle> ventaDetalles) {
		this.ventaDetalles = ventaDetalles;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
