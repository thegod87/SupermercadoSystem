package supermercadoSystem.vista;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import supermercadoSystem.componentes.jDialogGenerico;
import supermercadoSystem.componentes.jtextFieldPersonalizado;
import supermercadoSystem.controlador.CategoriaController;
import java.awt.Font;

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
		getLblTitulo().setFont(new Font("Tahoma", Font.PLAIN, 28));
		getLblBuscar().setBounds(435, 80, 57, 14);
		getTfBuscador().setBounds(486, 78, 389, 20);
		getLblTitulo().setBounds(287, 26, 314, 41);
		setTitle("Categorias");
		getLblTitulo().setText("Registros de Categorias");
		getPanelFormulario().setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdigo.setBounds(12, 39, 86, 16);
		getPanelFormulario().add(lblCdigo);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(12, 132, 86, 16);
		getPanelFormulario().add(lblDescripcin);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(12, 216, 86, 16);
		getPanelFormulario().add(lblEstado);
		
		tfId = new jtextFieldPersonalizado();
		tfId.setEditable(false);
		tfId.setBounds(110, 36, 86, 22);
		getPanelFormulario().add(tfId);
		tfId.setColumns(10);
		
		tfDescripcion = new jtextFieldPersonalizado();
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(110, 131, 278, 22);
		getPanelFormulario().add(tfDescripcion);
		
		cbEstado = new JCheckBox("Activo");
		cbEstado.setBounds(110, 212, 113, 25);
		getPanelFormulario().add(cbEstado);
		
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
