package supermercadoSystem.vista;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import supermercadoSystem.componentes.jDialogGenerico;
import supermercadoSystem.componentes.jtextFieldPersonalizado;
import supermercadoSystem.controlador.CategoriaController;

public class CategoriaVentana extends jDialogGenerico {

	private final JPanel contentPanel = new JPanel();
	private jtextFieldPersonalizado tfId;
	private jtextFieldPersonalizado tfDescripcion;
	private JCheckBox cbEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CategoriaVentana dialog = new CategoriaVentana();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUpController();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUpController(){
		new CategoriaController(this);
	}

	/**
	 * Create the dialog.
	 */
	public CategoriaVentana() {
		setTitle("Categorias");
		getLblTitulo().setText("Registros de Categorias");
		getjPanelFormulario().setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdigo.setBounds(12, 39, 86, 16);
		getjPanelFormulario().add(lblCdigo);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(12, 132, 86, 16);
		getjPanelFormulario().add(lblDescripcin);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(12, 216, 86, 16);
		getjPanelFormulario().add(lblEstado);
		
		tfId = new JtextFieldPersonalizado();
		tfId.setEditable(false);
		tfId.setBounds(110, 36, 86, 22);
		getjPanelFormulario().add(tfId);
		tfId.setColumns(10);
		
		tfDescripcion = new JtextFieldPersonalizado();
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(110, 131, 278, 22);
		getjPanelFormulario().add(tfDescripcion);
		
		cbEstado = new JCheckBox("Activo");
		cbEstado.setBounds(110, 212, 113, 25);
		getjPanelFormulario().add(cbEstado);
		
	}

	public JtextFieldPersonalizado getTfId() {
		return tfId;
	}

	public void setTfId(JtextFieldPersonalizado tfId) {
		this.tfId = tfId;
	}

	public JtextFieldPersonalizado getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(JtextFieldPersonalizado tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	public JCheckBox getCbEstado() {
		return cbEstado;
	}

	public void setCbEstado(JCheckBox cbEstado) {
		this.cbEstado = cbEstado;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
}
