package supermercadoSystem.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.cursojava.buscadores.BuscadorCliente;
import py.edu.cursojava.buscadores.BuscadorFuncionario;
import py.edu.cursojava.buscadores.BuscadorProducto;
import py.edu.cursojava.dao.ClienteDao;
import py.edu.cursojava.dao.FuncionarioDao;
import py.edu.cursojava.dao.ProductoDao;
import py.edu.cursojava.dao.VentaDao;
import py.edu.cursojava.entidades.Cliente;
import py.edu.cursojava.entidades.Funcionario;
import py.edu.cursojava.entidades.Producto;
import py.edu.cursojava.entidades.Venta;
import py.edu.cursojava.entidades.VentaDetalle;
import py.edu.cursojava.interfaces.InterfaceCliente;
import py.edu.cursojava.interfaces.InterfaceFuncionario;
import py.edu.cursojava.interfaces.InterfaceProducto;
import py.edu.cursojava.tablas.ModeloTablaVentaDetalle;
import py.edu.cursojava.utilidades.EventosGenericos;
import py.edu.cursojava.utilidades.UtilidadesFecha;
import py.edu.cursojava.utilidades.UtilidadesNumeros;
import py.edu.cursojava.vistas.VentaVentana;

public class VentaController implements InterfaceFuncionario, InterfaceCliente, InterfaceProducto {
	
	private Venta venta;
	private Funcionario funcionario;
	private Cliente cliente;
	private Producto producto;
	private VentaDetalle ventaDetalle;
	private List<VentaDetalle> detalles;
	private VentaDao dao;
	private ProductoDao productoDao;
	private VentaVentana ventana;
	private ModeloTablaVentaDetalle modeloTablaVentaDetalle;
	private ClienteDao clienteDao;
	private FuncionarioDao funcionarioDao;
	
	//metodo constructor de la clase, que recibe nuestra ventana
	public VentaController(VentaVentana ventana) {
		super();
		this.ventana = ventana;
		dao = new VentaDao();
		productoDao = new ProductoDao();
		clienteDao = new ClienteDao();
		funcionarioDao = new FuncionarioDao();
		modeloTablaVentaDetalle = new ModeloTablaVentaDetalle();
		this.ventana.getTable().setModel(modeloTablaVentaDetalle);
		estadoinicial();
		setUpEvents();
	}
	
	//el metodo que pasa las acciones a nuestros componentes de la ventana
	private void setUpEvents() {
		ventana.getBtnNuevo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		ventana.getBtnAnular().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anular();
			}
		});
		ventana.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		ventana.getBtnFuncionario().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorFuncionario();
			}
		});
		ventana.getBtnCliente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorCliente();
			}
		});
		ventana.getBtnProducto().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorProducto();
			}
		});
		ventana.getBtnAnadir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anhadirItem();
			}
		});
		ventana.getBtnQuitar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitarItem();
			}
		});
		ventana.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		ventana.getBtnFinalizar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finalizar();
			}
		});
		ventana.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarItem(ventana.getTable().getSelectedRow());
			}
		});
		ventana.getTfNumero().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) recuperarVenta();
			}
		});
		ventana.getTfCodigoFuncionario().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) recuperarFuncionario();
			}
		});
		ventana.getTfCedulaCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) recuperarCliente();
			}
		});
		ventana.getTfCodigoproducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) recuperarProducto();
			}
		});
	}
	
	//pasa los estados a nuestros componentes de la ventana al abrirla, y al terminar una acci�n
	private void estadoinicial() {
		ventana.getBtnNuevo().setEnabled(true);
		ventana.getBtnAnular().setEnabled(false);
		ventana.getBtnSalir().setEnabled(true);
		ventana.getTfNumero().setEnabled(true);
		ventana.getTfFecha().setEnabled(false);
		ventana.getTfCedulaCliente().setEnabled(false);
		ventana.getTfCodigoFuncionario().setEnabled(false);
		ventana.getTfCodigoproducto().setEnabled(false);
		ventana.getTfCantidad().setEnabled(false);
		ventana.getBtnFuncionario().setEnabled(false);
		ventana.getBtnCliente().setEnabled(false);
		ventana.getBtnProducto().setEnabled(false);
		ventana.getBtnAnadir().setEnabled(false);
		ventana.getBtnQuitar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getBtnFinalizar().setEnabled(false);
		ventana.getLblTotal().setText("0,0");
	}
	
	//limpiar los campos de nuestra ventana
	private void limpiar() {
		EventosGenericos.limpiarJtexField(ventana.getContentPane());
		ventana.getTfFecha().setValue(null);
		detalles = new ArrayList<VentaDetalle>();
		modeloTablaVentaDetalle.setLista(detalles);
	}
	
	//habilita la ventana para que el usuario pueda realizar un nuevo registro
	private void nuevo() {
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnAnular().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getTfNumero().setEnabled(false);
		ventana.getTfFecha().setEnabled(true);
		ventana.getTfCedulaCliente().setEnabled(true);
		ventana.getTfCodigoFuncionario().setEnabled(true);
		ventana.getTfCodigoproducto().setEnabled(true);
		ventana.getTfCantidad().setEnabled(true);
		ventana.getBtnFuncionario().setEnabled(true);
		ventana.getBtnCliente().setEnabled(true);
		ventana.getBtnProducto().setEnabled(true);
		ventana.getBtnAnadir().setEnabled(false);
		ventana.getBtnQuitar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnFinalizar().setEnabled(true);
		ventana.getLblTotal().setText("0,0");
		ventana.getTfNumero().setText(dao.crearNuevoid()+"");
		ventana.getTfFecha().setText(UtilidadesFecha.fechaAString(new Date()));
		venta = new Venta();
		detalles = new ArrayList<VentaDetalle>();
		ventana.getTfCodigoFuncionario().requestFocus();
	}
	
	// permite la anulaci�n de un registro
	private void anular() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Deseas eliminar la venta numero "+venta.getId()+" ?", 
				"Atenci�n", JOptionPane.YES_NO_OPTION);
		if (respuesta== JOptionPane.YES_OPTION) {
			venta.setObs(JOptionPane.showInputDialog(null, "Motivo de Anulaci�n"));
			venta.setAnulado(true);
			try {
				dao.guardar(venta);
				dao.commit();
				sumarStock();
				estadoinicial();
				limpiar();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		}
	}
	
	//cierra la ventana
	private void salir() {
		ventana.dispose();
	}
	
	//cancela la operaci�n y vuelve al estado inicial
	private void cancelar() {
		estadoinicial();
		limpiar();
	}
	
	// registra los datos en la base de datos
	private void finalizar() {
		if(!validarVenta())return;
		cragarVenta();
		try {
			dao.guardar(venta);
			dao.commit();
			restarStock();
			estadoinicial();
			limpiar();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}
	
	//cargar en los atributos de venta lo que el usuario ingreso en el formulario/ventana para posteriormente registrarlos 
	private void cragarVenta() {
		venta.setAnulado(false);
		venta.setCliente(cliente);
		venta.setFecha(UtilidadesFecha.stringAFecha(ventana.getTfFecha().getText()));
		venta.setFuncionario(funcionario);
		venta.setObs("");
		venta.setTotal(UtilidadesNumeros.stringADouble(ventana.getLblTotal().getText()));
		venta.setVentaDetalles(detalles);
		for (int i = 0; i < detalles.size(); i++) {
			detalles.get(i).setVenta(venta);
		}
	}
	
	// restar la existencia de un producto despues de realizarce la venta
	private void restarStock() {
		for (int i = 0; i < detalles.size(); i++) {
			producto = detalles.get(i).getProducto();
			producto.setExistencia(producto.getExistencia()-detalles.get(i).getCantidad());
			try {
				productoDao.guardar(producto);
				productoDao.commit();
			} catch (Exception e) {
				productoDao.rollback();
				e.printStackTrace();
			}
		}
	}
	
	// vuelve a depositar los productos en la existencia despues de anular un registro de venta
	private void sumarStock() {
		for (int i = 0; i < detalles.size(); i++) {
			producto = detalles.get(i).getProducto();
			producto.setExistencia(producto.getExistencia()+detalles.get(i).getCantidad());
			try {
				productoDao.guardar(producto);
				productoDao.commit();
			} catch (Exception e) {
				productoDao.rollback();
				e.printStackTrace();
			}
		}
	}
	
	// a�ade en nuestro detalle el producto seleccionado
	private void anhadirItem() {
		ventaDetalle = new VentaDetalle();
		ventaDetalle.setProducto(this.producto);
		ventaDetalle.setCantidad((int)ventana.getTfCantidad().getValue());
		ventaDetalle.setPrecioVenta(producto.getPrecioVenta());
		detalles.add(ventaDetalle);
		modeloTablaVentaDetalle.setLista(detalles);
		producto = null;
		ventana.getTfCodigoproducto().setText("");
		ventana.getTfProducto().setText("");
		ventana.getTfCantidad().setValue(1);
		ventana.getBtnAnadir().setEnabled(true);
		double total = 0;
		for (int i = 0; i < detalles.size(); i++) {
			total = total + (detalles.get(i).getPrecioVenta()*detalles.get(i).getCantidad());
		}
		ventana.getLblTotal().setText(UtilidadesNumeros.doubleString(total));
	}
	
	//selecciona en nuestro detalle un producto para su modificaci�n
	private void seleccionarItem(int index) {
		ventaDetalle = detalles.get(index);
		ventana.getTfCodigoproducto().setText(ventaDetalle.getProducto().getCodigoBarra());
		ventana.getTfProducto().setText(ventaDetalle.getProducto().getDescripcion()+" "+ventaDetalle.getProducto().getMarca().getDescripcion());
		ventana.getTfCantidad().setValue(ventaDetalle.getCantidad());
		ventana.getBtnQuitar().setEnabled(true);
	}
	
	// quita el intem selccionado de la lista de detalles
	private void quitarItem() {
		detalles.remove(ventaDetalle);
		modeloTablaVentaDetalle.setLista(detalles);
		producto = null;
		ventana.getTfCodigoproducto().setText("");
		ventana.getTfProducto().setText("");
		ventana.getTfCantidad().setValue(1);
		ventana.getBtnQuitar().setEnabled(false);
		double total = 0;
		for (int i = 0; i < detalles.size(); i++) {
			total = total + (detalles.get(i).getPrecioVenta()*detalles.get(i).getCantidad());
		}
		ventana.getLblTotal().setText(UtilidadesNumeros.doubleString(total));
	}
	
	// recuperar un registro de venta a travez de su ID
	private void recuperarVenta() {
		venta = dao.recuperarPorId(UtilidadesNumeros.stringAInteger(ventana.getTfNumero().getText()));
		if (venta!=null) {
			ventana.getBtnNuevo().setEnabled(false);
			ventana.getBtnAnular().setEnabled(true);
			ventana.getBtnSalir().setEnabled(false);
			ventana.getBtnCancelar().setEnabled(true);
			cargarCamposVentana();
		}else {
			JOptionPane.showMessageDialog(null, "Venta no encontrada");
		}
	}
	
	// carga en el formulario los datos del registro seleccionado en el metodo anterior
	private void cargarCamposVentana() {
		ventana.getTfNumero().setText(venta.getId()+"");
		ventana.getTfFecha().setText(UtilidadesFecha.fechaAString(venta.getFecha()));
		ventana.getTfCodigoFuncionario().setText(venta.getFuncionario().getDocumento());
		ventana.getTfFuncionario().setText(venta.getFuncionario().getNombre()+" "+venta.getFuncionario().getApellido());
		ventana.getTfCedulaCliente().setText(venta.getCliente().getDocumento());
		ventana.getTfCliente().setText(venta.getCliente().getNombre()+" "+venta.getCliente().getApellido());
		detalles=venta.getVentaDetalles();
		modeloTablaVentaDetalle.setLista(detalles);
		double total = 0;
		for (int i = 0; i < detalles.size(); i++) {
			total = total + (detalles.get(i).getPrecioVenta()*detalles.get(i).getCantidad());
		}
		ventana.getLblTotal().setText(UtilidadesNumeros.doubleString(total));
	}
	
	//recupera un registro de funcionario por medio de su c�digo
	private void recuperarFuncionario() {
		funcionario = funcionarioDao.recuperarPorId(UtilidadesNumeros.stringAInteger(ventana.getTfCodigoFuncionario().getText()));
		if (funcionario==null) {
			JOptionPane.showMessageDialog(null, "Funcionario no encontrado");
			ventana.getTfCodigoFuncionario().requestFocus();
		}else {
			ventana.getTfFuncionario().setText(funcionario.getNombre()+" "+funcionario.getApellido());
			ventana.getTfCedulaCliente().requestFocus();
		}

	}
	
	// recupera un registro de cliente a travez de su CI
	private void recuperarCliente() {
		cliente = clienteDao.verificarCedula(ventana.getTfCedulaCliente().getText());
		if (cliente==null) {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado");
			ventana.getTfCedulaCliente().requestFocus();
		}else {
			ventana.getTfCliente().setText(cliente.getNombre()+" "+cliente.getApellido());
			ventana.getTfCodigoproducto().requestFocus();
		}
	}
	
	//recupera el registro de producto a travez de su c�digo de barra
	private void recuperarProducto() {
		producto = productoDao.filtrarProductoPorCodigo(ventana.getTfCodigoproducto().getText());
		if (producto==null) {
			JOptionPane.showMessageDialog(null, "Producto no encontrado");
			ventana.getTfCodigoproducto().requestFocus();
		}else {
			ventana.getTfProducto().setText(producto.getDescripcion()+" "+producto.getMarca().getDescripcion());
			ventana.getTfCantidad().setValue(1);
			anhadirItem();
		}
	}

	//abre la ventana que permite la busqueda de un registro
	private void abrirBuscadorProducto() {
		BuscadorProducto buscadorProducto = new BuscadorProducto();
		buscadorProducto.setInterface(this);
		buscadorProducto.setVisible(true);
	}
	
	//metodo de la interfaz que pasa el registro seleccionado en el buscador
	@Override
	public void seleccionarporducto(Producto producto) {
		this.producto = producto;
		ventana.getTfCodigoproducto().setText(producto.getCodigoBarra());
		ventana.getTfProducto().setText(producto.getDescripcion()+" "+producto.getMarca().getDescripcion());
		ventana.getTfCantidad().setValue(1);
		ventana.getBtnAnadir().setEnabled(true);
	}
	
	//abre la ventana que permite la busqueda de un registro
	private void abrirBuscadorCliente() {
		BuscadorCliente buscadorCliente = new BuscadorCliente();
		buscadorCliente.setInterface(this);
		buscadorCliente.setVisible(true);
	}

	//metodo de la interfaz que pasa el registro seleccionado en el buscador
	@Override
	public void seleccionarCliente(Cliente cliente) {
		this.cliente = cliente;
		ventana.getTfCedulaCliente().setText(cliente.getDocumento());
		ventana.getTfCliente().setText(cliente.getNombre()+" "+cliente.getApellido());
	}
	
	//abre la ventana que permite la busqueda de un registro
	private void abrirBuscadorFuncionario() {
		BuscadorFuncionario buscadorFuncionario = new BuscadorFuncionario();
		buscadorFuncionario.setInterface(this);
		buscadorFuncionario.setVisible(true);
	}

	//metodo de la interfaz que pasa el registro seleccionado en el buscador
	@Override
	public void seleccionarFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
		ventana.getTfCodigoFuncionario().setText(funcionario.getId()+"");
		ventana.getTfFuncionario().setText(funcionario.getNombre()+" "+funcionario.getApellido());
	}
	
	//valida que se hayan ingresado todos los datos obligatorios 
	private boolean validarVenta() {
		if (UtilidadesFecha.stringAFecha(ventana.getTfFecha().getText())==null) {
			JOptionPane.showMessageDialog(null, "Ingrese una fecha valida");
			ventana.getTfFecha().requestFocus();
			return false;
		}
		if (funcionario==null) {
			JOptionPane.showMessageDialog(null, "Seleccione un funcionario");
			ventana.getTfCodigoFuncionario().requestFocus();
			return false;
		}
		if (cliente==null) {
			JOptionPane.showMessageDialog(null, "Seleccione un cliente");
			ventana.getTfCedulaCliente().requestFocus();
			return false;
		}
		if (detalles.size()==0) {
			JOptionPane.showMessageDialog(null, "Seleccione al menos 1 producto");
			return false;
		}
		return true;
	}

}
