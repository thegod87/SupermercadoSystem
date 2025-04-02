package supermercadoSystem.controlador;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import supermercadoSystem.dao.ClienteDao;
import supermercadoSystem.entidades.Cliente;
import supermercadoSystem.interfaces.InterfaceAcciones;
import supermercadoSystem.tablas.ModeloTablaCliente;
import supermercadoSystem.utilidades.EventosGenericos;
import supermercadoSystem.utilidades.UtilidadesFecha;
import supermercadoSystem.vista.ClienteVentana;

public class ClienteController implements InterfaceAcciones, FocusListener {

	private ModeloTablaCliente modeloTablaCliente;
	private ClienteVentana ventana;
	private Cliente cliente;
	private List<Cliente> clientes;
	private ClienteDao dao;

	public ClienteController(ClienteVentana clienteventana) {
		super();
		this.ventana = clienteventana;
		this.ventana.setInterfaceAcciones(this);
		dao = new ClienteDao();
		modeloTablaCliente = new ModeloTablaCliente();
		setUpEvents();
		estadoinicial();
		iniciarTabla();
		consultarClientes();
	}

	private void setUpEvents() {
		ventana.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					seleccionarRegistro(ventana.getTable().getSelectedRow());

			}
		});

		ventana.getTfBuscador().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					consultarClientes();
			}
		});

		ventana.getTfNombre().addFocusListener(this);
		ventana.getTfApellido().addFocusListener(this);
		ventana.getTfDocumento().addFocusListener(this);
		ventana.getTfTelefono().addFocusListener(this);
		ventana.getTfFechaNascimiento().addFocusListener(this);

	}

	private void estadoinicial() {
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnNuevo().setEnabled(true);
		ventana.getBtnSalir().setEnabled(true);
		EventosGenericos.estadosJtexField(ventana.getPanelFormulario(), false);
		EventosGenericos.limpiarJtexField(ventana.getPanelFormulario());
		ventana.getTfFechaNascimiento().setValue(null);
		ventana.getTfFechaNascimiento().setEnabled(false);
		ventana.getBtnGuardar().setText("Guardar");
		ventana.getTable().setEnabled(true);
		ventana.getScrollPane().setEnabled(true);
	}

	private void iniciarTabla() {
		modeloTablaCliente = new ModeloTablaCliente();
		this.ventana.getTable().setModel(modeloTablaCliente);
		this.ventana.getTable().getColumnModel().getColumn(0).setPreferredWidth(2);
		this.ventana.getTable().getColumnModel().getColumn(3).setPreferredWidth(5);
	}

	@Override
	public void nuevo() {
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		EventosGenericos.estadosJtexField(ventana.getPanelFormulario(), true);
		ventana.getTfFechaNascimiento().setEnabled(true);
		ventana.getTable().setEnabled(false);
		ventana.getScrollPane().setEnabled(false);
	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJtexField(ventana.getPanelFormulario(), true);
		ventana.getTfFechaNascimiento().setEnabled(true);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnGuardar().setText("Actualizar");
	}

	@Override
	public void actualizar() {
		if (!validarCampos())
			return;
		cargarEntidad();
		try {
			dao.guardar(cliente);
			dao.commit();
			estadoinicial();
			consultarClientes();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void eliminar() {
		if (cliente == null) {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar el cliente "
				+ cliente.getNombre() + " " + cliente.getApellido() + "?", "�Atenci�n!", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(cliente);
				dao.commit();
				estadoinicial();
				consultarClientes();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		}
	}

	@Override
	public void cancelar() {
		estadoinicial();
	}

	@Override
	public void guardar() {
		if (!validarCampos())
			return;
		cliente = new Cliente();
		cargarEntidad();
		try {
			dao.guardar(cliente);
			dao.commit();
			consultarClientes();
			estadoinicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void salir() {
		ventana.dispose();
	}

	private void cargarEntidad() {
		cliente.setNombre(this.ventana.getTfNombre().getText());
		cliente.setApellido(this.ventana.getTfApellido().getText());
		cliente.setDireccion(this.ventana.getTfDireccion().getText());
		cliente.setCorreo(this.ventana.getTfEmail().getText());
		cliente.setDocumento(this.ventana.getTfDocumento().getText());
		cliente.setTelefono(this.ventana.getTfTelefono().getText());
		cliente.setFecha_nascimiento(UtilidadesFecha.stringAFecha(this.ventana.getTfFechaNascimiento().getText()));
	}

	private void seleccionarRegistro(int index) {
		if (index < 0)
			return;
		cliente = clientes.get(index);
		ventana.getTfNombre().setText(cliente.getNombre());
		ventana.getTfApellido().setText(cliente.getApellido());
		ventana.getTfDireccion().setText(cliente.getDireccion());
		ventana.getTfEmail().setText(cliente.getCorreo());
		ventana.getTfDocumento().setText(cliente.getDocumento());
		ventana.getTfTelefono().setText(cliente.getTelefono());
		ventana.getTfFechaNascimiento().setText(UtilidadesFecha.fechaAString(cliente.getFecha_nascimiento()));

		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(true);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
	}

	private void consultarClientes() {
		clientes = dao.filtrarClientes(this.ventana.getTfBuscador().getText());
		modeloTablaCliente.setLista(clientes);
	}

	private boolean validarCampos() {
		if (ventana.getTfNombre().getText().isEmpty()) {
			ventana.getTfNombre().setBorder(new LineBorder(Color.red));
			JOptionPane.showMessageDialog(null, "El campo 'Nombre' es obligatorio");
			return false;
		}
		if (ventana.getTfApellido().getText().isEmpty()) {
			ventana.getTfApellido().setBorder(new LineBorder(Color.red));
			JOptionPane.showMessageDialog(null, "El campo 'Apellido' es obligatorio");
			return false;
		}
		if (ventana.getTfDocumento().getText().isEmpty()) {
			ventana.getTfDocumento().setBorder(new LineBorder(Color.red));
			JOptionPane.showMessageDialog(null, "El campo 'Documento' es obligatorio");
			return false;
		}
		if (ventana.getTfTelefono().getText().isEmpty()) {
			ventana.getTfTelefono().setBorder(new LineBorder(Color.red));
			JOptionPane.showMessageDialog(null, "El campo 'Telefono' es obligatorio");
			return false;
		}
		if (UtilidadesFecha.stringAFecha(ventana.getTfFechaNascimiento().getText()) == null) {
			ventana.getTfFechaNascimiento().setBorder(new LineBorder(Color.red));
			JOptionPane.showMessageDialog(null, "El campo 'Fecha de nascimiento' es obligatorio");
			return false;
		}

		Cliente cli = dao.verificarCedula(ventana.getTfDocumento().getText());

		if (cli != null) {
			if (ventana.getBtnGuardar().getText().equals("Actualizar") && cliente.getId() == cli.getId()) {

			} else {
				JOptionPane.showMessageDialog(null, "Documento Duplicado");
				return false;
			}
		}

		return true;
	}

	@Override
	public void focusGained(FocusEvent arg0) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == ventana.getTfNombre()) {
			ventana.getTfNombre().setBorder(new LineBorder(Color.LIGHT_GRAY));
		}
		if (e.getSource() == ventana.getTfApellido()) {
			ventana.getTfApellido().setBorder(new LineBorder(Color.LIGHT_GRAY));
		}
		if (e.getSource() == ventana.getTfDocumento()) {
			ventana.getTfDocumento().setBorder(new LineBorder(Color.LIGHT_GRAY));
		}
		if (e.getSource() == ventana.getTfTelefono()) {
			ventana.getTfTelefono().setBorder(new LineBorder(Color.LIGHT_GRAY));
		}
		if (e.getSource() == ventana.getTfFechaNascimiento()) {
			ventana.getTfFechaNascimiento().setBorder(new LineBorder(Color.LIGHT_GRAY));
		}

	}

}
