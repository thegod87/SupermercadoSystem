package supermercadoSystem.vista;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import power.tech.componentes.jDialogoGenerico;
import power.tech.controlador.FuncionarioController;
import power.tech.utilidades.UtilidadesFecha;
import java.awt.Font;
import javax.swing.JLabel;
import power.tech.componentes.JtextFielPersonalizado;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class FuncionarioVentana extends jDialogoGenerico {

	private JFormattedTextField tfFechaNascimiento;
	private JtextFielPersonalizado tfSexo;
	private JtextFielPersonalizado tfTelefono;
	private JtextFielPersonalizado tfDocumento;
	private JtextFielPersonalizado tfEmail;
	private JtextFielPersonalizado tfDireccion;
	private JtextFielPersonalizado tfApellido;
	private JtextFielPersonalizado tfNombre;
	private JCheckBox chEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionarioVentana dialog = new FuncionarioVentana();
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
		new FuncionarioController(this);
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({})
	public FuncionarioVentana() {
		getToolBar_2().setVisible(false);
		getLblTitulo().setIconTextGap(10);
		getLblTitulo()
				.setIcon(new ImageIcon(FuncionarioVentana.class.getResource("/power/tech/img/registroclientes.png")));
		getBtnSalir().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnGuardar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnCancelar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnEliminar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnModificar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getBtnNuevo().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getScrollPane().setViewportBorder(new LineBorder(Color.GRAY));
		setTitle("Registro de Funcionarios");
		getBtnEliminar().setLocation(226, 575);
		getBtnCancelar().setLocation(310, 519);
		getBtnGuardar().setLocation(467, 519);
		getBtnSalir().setLocation(678, 519);
		getPanelFormulario().setBounds(14, 127, 411, 361);
		getLblTitulo().setFont(new Font("Times New Roman", Font.BOLD, 34));
		getLblTitulo().setBounds(239, 21, 430, 37);
		getLblTitulo().setText("Registro de Funcionarios");
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

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBounds(39, 329, 41, 14);
		getPanelFormulario().add(lblEstado);

		chEstado = new JCheckBox("Activo");
		chEstado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chEstado.setBounds(90, 325, 97, 23);
		getPanelFormulario().add(chEstado);

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
		label_2.setBounds(238, 172, 22, 14);
		getPanelFormulario().add(label_2);

		JLabel label_3 = new JLabel("*");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(238, 214, 22, 14);
		getPanelFormulario().add(label_3);

		JLabel label_5 = new JLabel("*");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(238, 296, 23, 14);
		getPanelFormulario().add(label_5);

		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos aqui:");
		lblIngreseLosDatos.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblIngreseLosDatos.setBounds(124, 76, 191, 20);
		getContentPane().add(lblIngreseLosDatos);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 499, 381, 14);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(30, 106, 381, 14);
		getContentPane().add(separator_1);

		JLabel label_4 = new JLabel("PowerTech");
		label_4.setForeground(Color.GREEN);
		label_4.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		label_4.setBackground(SystemColor.inactiveCaptionBorder);
		label_4.setBounds(803, 11, 72, 14);
		getContentPane().add(label_4);

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

	public JCheckBox getChEstado() {
		return chEstado;
	}

	public void setChEstado(JCheckBox chEstado) {
		this.chEstado = chEstado;
	}

}
