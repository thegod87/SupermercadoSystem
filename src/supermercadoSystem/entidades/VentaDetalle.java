package supermercadoSystem.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="tb_venta_detalles")
public class VentaDetalle {
	@Id
	@GenericGenerator(name="vend_generator",  strategy="increment")
	@GeneratedValue(generator="vend_generator")
	
	@Column(name="vend_id")
	private int id;
	@Column(name="vend_precio", nullable=false)
	private double precioVenta;
	@Column(name="vend_cantidad", nullable=false)
	private double cantidad;
	@ManyToOne
	private Venta venta;
	@ManyToOne
	private Producto producto;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
