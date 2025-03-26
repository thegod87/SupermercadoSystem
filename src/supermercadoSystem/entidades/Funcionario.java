package supermercadoSystem.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_funcionario")
public class Funcionario {

	@Id
	@GenericGenerator(name = "funcionario_generator", strategy = "increment")
	@GeneratedValue(generator = "funcionario_generator")
	
	@Column(name = "fun_id")
	private int id;

	@Column(name = "fun_nombre", nullable = false)
	private String nombre;

	@Column(name = "fun_apellido", nullable = false)
	private String apellido;

	@Column(name = "fun_documento", nullable = false, unique = true)
	private String documento;

	@Column(name = "fun_telefono", nullable = false)
	private String telefono;

	@Column(name = "fun_correo")
	private String correo;

	@Column(name = "fun_direccion")
	private String direccion;
	
	@Column(name = "fun_ciudad")
	private String ciudad;

	@Column(name = "fun_fecha_nascimiento", nullable = false)
	private Date fecha_nascimiento;

	@Column(name = "fun_estado")
	private Boolean estado;

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

	public Date getFecha_nascimiento() {
		return fecha_nascimiento;
	}

	public void setFecha_nascimiento(Date fecha_nascimiento) {
		this.fecha_nascimiento = fecha_nascimiento;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
