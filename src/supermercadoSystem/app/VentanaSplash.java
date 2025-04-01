package supermercadoSystem.app;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import supermercadoSystem.app.PantallaPrincipal;
import supermercadoSystem.vista.ClienteVentana;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VentanaSplash extends JFrame {

	private JPanel contentPane;
	private static JProgressBar progressBar = new JProgressBar();
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		VentanaSplash ventanasplash = new VentanaSplash();
		ventanasplash.setVisible(true);
		cargar();
		ventanasplash.dispose();
		abrirCliente();

	}

	/**
	 * Create the frame.
	 */
	public VentanaSplash() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		progressBar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.GREEN);
		setLocationRelativeTo(null);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaSplash.class.getResource("/power/tech/img/splashh.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(progressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 474, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)));
		contentPane.setLayout(gl_contentPane);
	}

	public static void cargar() {
		for (int i = 0; i <= 100; i++) {
			try {
				Thread.sleep(30);
				progressBar.setValue(i);
				if (i == 100) {
					abrirPantallaPrincipal();

				}
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "No fue posible acceder al sistema" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void abrirCliente() {
		ClienteVentana clienteVentana = new ClienteVentana();
		clienteVentana.setUpControlador();
	}

	public static void abrirPantallaPrincipal() {
		PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
		pantallaPrincipal.setVisible(true);
	}
}
