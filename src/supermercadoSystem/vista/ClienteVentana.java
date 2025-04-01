package supermercadoSystem.vista;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import supermercadoSystem.componentes.jDialogoGenerico;
import supermercadoSystem.controlador.ClienteController;
import supermercadoSystem.utilidades.UtilidadesFecha;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import supermercadoSystem.componentes.JtextFielPersonalizado;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ClienteVentana extends jDialogoGenerico {

	private JFormattedTextField tfFechaNascimiento;
	private JtextFielPersonalizado tfSexo;
	private JtextFielPersonalizado tfTelefono;
	private JtextFielPersonalizado tfDocumento;
	private JtextFielPersonalizado tfEmail;
	private JtextFielPersonalizado tfDireccion;
	private JtextFielPersonalizado tfApellido;
	private JtextFielPersonalizado tfNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteVentana dialog = new ClienteVentana();
					dialog.setUpControlador();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setUpControlador() {
		new ClienteController(this);
	}

	/**
	 * Create the dialog.
	 */
	public ClienteVentana() {
		getToolBar_2().setBounds(20, 0, -2, 77);
		getToolBar_2().setVisible(false);
		getBtnNuevo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getLblTitulo().setFont(new Font("Times New Roman", Font.BOLD, 34));
		getLblTitulo().setIconTextGap(10);
		getLblTitulo().setIcon(new ImageIcon(ClienteVentana.class.getResource("/power/tech/img/registroclientes.png")));
		getBtnSalir().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnGuardar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnCancelar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnEliminar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnModificar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnNuevo().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getScrollPane().setViewportBorder(new LineBorder(Color.GRAY));
		setTitle("Registro de Clientes");
		getBtnEliminar().setLocation(226, 575);
		getBtnCancelar().setLocation(358, 504);
		getPanelFormulario().setBounds(14, 131, 411, 327);
		getLblTitulo().setBounds(282, 21, 361, 37);
		getLblTitulo().setText("Registro de Clientes");
		getPanelFormulario().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(30, 11, 48, 14);
		getPanelFormulario().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(30, 51, 48, 14);
		getPanelFormulario().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Direccion:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(23, 92, 55, 14);
		getPanelFormulario().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Telefono:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(26, 214, 52, 14);
		getPanelFormulario().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Email:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(45, 131, 33, 14);
		getPanelFormulario().add(lblNewLabel_5);

		JLabel lblDireccion = new JLabel("Documento:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccion.setBounds(10, 172, 68, 14);
		getPanelFormulario().add(lblDireccion);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSexo.setBounds(45, 256, 33, 14);
		getPanelFormulario().add(lblSexo);

		JLabel lblFechaDeNascimiento = new JLabel("Fecha de Nascimiento:");
		lblFechaDeNascimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaDeNascimiento.setBounds(10, 296, 125, 14);
		getPanelFormulario().add(lblFechaDeNascimiento);

		tfNombre = new JtextFielPersonalizado();
		tfNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				tfNombre.setBorder(new LineBorder(Color.gray));
			}
		});
		tfNombre.setBounds(88, 8, 287, 20);
		getPanelFormulario().add(tfNombre);

		tfApellido = new JtextFielPersonalizado();
		tfApellido.setBounds(88, 48, 287, 20);
		getPanelFormulario().add(tfApellido);

		tfDireccion = new JtextFielPersonalizado();
		tfDireccion.setBounds(88, 89, 287, 20);
		getPanelFormulario().add(tfDireccion);

		tfEmail = new JtextFielPersonalizado();
		tfEmail.setBounds(88, 128, 287, 20);
		getPanelFormulario().add(tfEmail);

		tfDocumento = new JtextFielPersonalizado();
		tfDocumento.setBounds(88, 169, 145, 20);
		getPanelFormulario().add(tfDocumento);

		tfTelefono = new JtextFielPersonalizado();
		tfTelefono.setBounds(88, 211, 145, 20);
		getPanelFormulario().add(tfTelefono);

		tfFechaNascimiento = new JFormattedTextField(UtilidadesFecha.getFormato());
		tfFechaNascimiento.setBounds(145, 293, 88, 20);
		getPanelFormulario().add(tfFechaNascimiento);

		tfSexo = new JtextFielPersonalizado();
		tfSexo.setBounds(88, 253, 145, 20);
		getPanelFormulario().add(tfSexo);

		JLabel label = new JLabel("*");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(378, 11, 23, 14);
		getPanelFormulario().add(label);

		JLabel label_1 = new JLabel("*");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(378, 51, 23, 14);
		getPanelFormulario().add(label_1);

		JLabel label_2 = new JLabel("*");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(237, 172, 23, 14);
		getPanelFormulario().add(label_2);

		JLabel label_3 = new JLabel("*");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(237, 214, 23, 14);
		getPanelFormulario().add(label_3);

		JLabel label_4 = new JLabel("*");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(237, 296, 23, 14);
		getPanelFormulario().add(label_4);

		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos aqui:");
		lblIngreseLosDatos.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblIngreseLosDatos.setBounds(124, 76, 191, 20);
		getContentPane().add(lblIngreseLosDatos);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 107, 380, 13);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(25, 499, 380, 13);
		getContentPane().add(separator_1);

		JLabel label_5 = new JLabel("PowerTech");
		label_5.setForeground(Color.GREEN);
		label_5.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		label_5.setBackground(SystemColor.inactiveCaptionBorder);
		label_5.setBounds(803, 11, 72, 14);
		getContentPane().add(label_5);

		JLabel label_6 = new JLabel("System");
		label_6.setForeground(Color.GRAY);
		label_6.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		label_6.setBackground(SystemColor.inactiveCaptionBorder);
		label_6.setBounds(823, 24, 35, 14);
		getContentPane().add(label_6);

	}

	public JFormattedTextField getTfFechaNascimiento() {
		return tfFechaNascimiento;
	}

	public void setTfFechaNascimiento(JFormattedTextField tfFechaNascimiento) {
		this.tfFechaNascimiento = tfFechaNascimiento;
	}

	public JtextFielPersonalizado getTfSexo() {
		return tfSexo;
	}

	public void setTfSexo(JtextFielPersonalizado tfSexo) {
		this.tfSexo = tfSexo;
	}

	public JtextFielPersonalizado getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JtextFielPersonalizado tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JtextFielPersonalizado getTfDocumento() {
		return tfDocumento;
	}

	public void setTfDocumento(JtextFielPersonalizado tfDocumento) {
		this.tfDocumento = tfDocumento;
	}

	public JtextFielPersonalizado getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(JtextFielPersonalizado tfEmail) {
		this.tfEmail = tfEmail;
	}

	public JtextFielPersonalizado getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(JtextFielPersonalizado tfDireccion) {
		this.tfDireccion = tfDireccion;
	}

	public JtextFielPersonalizado getTfApellido() {
		return tfApellido;
	}

	public void setTfApellido(JtextFielPersonalizado tfApellido) {
		this.tfApellido = tfApellido;
	}

	public JtextFielPersonalizado getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JtextFielPersonalizado tfNombre) {
		this.tfNombre = tfNombre;
	}
}
