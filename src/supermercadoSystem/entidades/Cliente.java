package supermercadoSystem.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_cliente")
public class Cliente {

	@Id
	@GenericGenerator(name = "cliente_generator", strategy = "increment")
	@GeneratedValue(generator = "cliente_generator")
	
	@Column(name = "cli_id")
	private int id;

	@Column(name = "cli_nombre", nullable = false)
	private String nombre;

	@Column(name = "cli_apellido", nullable = false)
	private String apellido;

	@Column(name = "cli_documento", nullable = false, unique = true)
	private String documento;

	@Column(name = "cli_telefono", nullable = false)
	private String telefono;

	@Column(name = "cli_correo")
	private String correo;

	@Column(name = "cli_direccion")
	private String direccion;
	
	@Column(name = "cli_ciudad")
	private String ciudad;

	@Column(name = "cli_fecha_nascimiento", nullable = false)
	private Date fecha_nascimiento;
	
	@Column(name = "cli_linea_credito")
	private String linea_credito;

	@OneToMany(mappedBy = "cliente")
	private List<Venta> ventas;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Date getFecha_nascimiento() {
		return fecha_nascimiento;
	}

	public void setFecha_nascimiento(Date fecha_nascimiento) {
		this.fecha_nascimiento = fecha_nascimiento;
	}

	public String getLinea_credito() {
		return linea_credito;
	}

	public void setLinea_credito(String linea_credito) {
		this.linea_credito = linea_credito;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
}
