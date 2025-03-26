package supermercadoSystem.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_pago")
public class Pago {

	@Id
	@GenericGenerator(name = "pago_generator", strategy = "increment")
	@GeneratedValue(generator = "pago_generator")
	@Column(name = "pago_id")
	private int id;

	@Column(name = "pago_fecha", nullable = false)
	private Date fecha;

	@Column(name = "pago_total", nullable = false)
	private Double total;

	@Column(name = "pago_estado", nullable = false)
	private Boolean estado;

	@ManyToOne
	public Venta venta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	

}
