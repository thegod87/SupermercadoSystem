package supermercadoSystem.app;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import supermercadoSystem.componentes.BotonesPrincipales;
import supermercadoSystem.dao.ClienteDao;
import supermercadoSystem.dao.FuncionarioDao;
import supermercadoSystem.vista.ClienteVentana;
import supermercadoSystem.vista.FuncionarioVentana;
import supermercadoSystem.componentes.jPanelPantallaPrincipal;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Cursor;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

@SuppressWarnings("serial")
public class PantallaPrincipal extends JFrame {

	private jPanelPantallaPrincipal contentPane;
	private JMenuBar menuBar;
	private JMenu mnRegistros;
	private JMenu mnListados;
	private JMenu mnInformes;
	private JMenu mnUtilidades;
	private JMenu mnMovimientos;
	private JMenuItem mntmFuncionarios;
	private JMenuItem mntmClientes;
	private JMenuItem mntmHabitaciones;
	private JMenuItem mntmReservas_1;
	private JMenuItem mntmPago;
	private JMenuItem mntmClientesL;
	private JMenuItem mntmHabitacionesL;
	private JMenuItem mntmFuncionarioL;
	private JMenuItem mntmReservas;
	private JMenuItem mntmPagos;
	private JMenuItem mntmInicializacionDeDatos;
	private JMenuItem mntmSoporteTecnico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(new Dimension(1080, 720));
		setExtendedState(MAXIMIZED_BOTH);

		setTitle("SupermercadoSystem");

		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 80, 0, 0));
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuBar.setIgnoreRepaint(true);
		menuBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		setJMenuBar(menuBar);

		mnRegistros = new JMenu("Registros");
		mnRegistros.setForeground(Color.BLACK);
		mnRegistros.setMargin(new Insets(0, 80, 0, 0));
		mnRegistros.setIconTextGap(40);
		mnRegistros.setPreferredSize(new Dimension(272, 25));
		mnRegistros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnRegistros.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/supermercadoSystem/img/registros.png")));
		mnRegistros.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnRegistros);

		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
		mntmClientes.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmClientes.setMargin(new Insets(0, 65, 0, 0));
		mntmClientes.setPreferredSize(new Dimension(269, 25));
		mntmClientes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmClientes.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaCliente();
			}
		});
		mnRegistros.add(mntmClientes);

		mntmHabitaciones = new JMenuItem("Habitaciones");
		mntmHabitaciones.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmHabitaciones.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
		mntmHabitaciones.setMargin(new Insets(0, 65, 0, 0));
		mntmHabitaciones.setPreferredSize(new Dimension(269, 25));
		mntmHabitaciones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmHabitaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmHabitaciones.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmHabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaHabitacion();
			}
		});
		mnRegistros.add(mntmHabitaciones);

		mntmFuncionarios = new JMenuItem("Funcionarios");
		mntmFuncionarios.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmFuncionarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_MASK));
		mntmFuncionarios.setMargin(new Insets(0, 65, 0, 0));
		mntmFuncionarios.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmFuncionarios.setPreferredSize(new Dimension(269, 25));
		mntmFuncionarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmFuncionarios.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFuncionario();
			}
		});
		mnRegistros.add(mntmFuncionarios);

		mnMovimientos = new JMenu("Movimientos");
		mnMovimientos.setForeground(Color.BLACK);
		mnMovimientos.setIconTextGap(40);
		mnMovimientos.setMargin(new Insets(0, 80, 0, 0));
		mnMovimientos.setPreferredSize(new Dimension(272, 22));
		mnMovimientos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnMovimientos.setHorizontalAlignment(SwingConstants.TRAILING);
		mnMovimientos.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/supermercadoSystem/img/movimientos.png")));
		mnMovimientos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnMovimientos);

		mntmReservas_1 = new JMenuItem("Reservas");
		mntmReservas_1.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmReservas_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mnMovimientos.add(mntmReservas_1);
		mntmReservas_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmReservas_1.setPreferredSize(new Dimension(269, 25));
		mntmReservas_1.setMargin(new Insets(0, 65, 0, 0));
		mntmReservas_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmReservas_1.setFocusPainted(true);
		mntmReservas_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmReservas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaReserva();
			}
		});

		mntmPago = new JMenuItem("Pago");
		mntmPago.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmPago.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmPago.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_MASK));
		mntmPago.setMargin(new Insets(0, 80, 0, 0));
		mntmPago.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmPago.setPreferredSize(new Dimension(269, 25));
		mntmPago.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnMovimientos.add(mntmPago);
		mntmPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaPago();
			}
		});

		mnListados = new JMenu("Listados");
		mnListados.setForeground(Color.BLACK);
		mnListados.setIconTextGap(40);
		mnListados.setMargin(new Insets(0, 80, 0, 0));
		mnListados.setPreferredSize(new Dimension(272, 22));
		mnListados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnListados.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/supermercadoSystem/img/listados.png")));
		mnListados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnListados);

		mntmClientesL = new JMenuItem("Clientes");
		mntmClientesL.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmClientesL.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmClientesL.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, InputEvent.CTRL_MASK));
		mntmClientesL.setMargin(new Insets(0, 65, 0, 0));
		mntmClientesL.setPreferredSize(new Dimension(269, 25));
		mntmClientesL.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmClientesL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnListados.add(mntmClientesL);
		mntmClientesL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaListadoCliente();
			}
		});

		mntmHabitacionesL = new JMenuItem("Habitaciones");
		mntmHabitacionesL.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmHabitacionesL.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmHabitacionesL.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, InputEvent.CTRL_MASK));
		mntmHabitacionesL.setMargin(new Insets(0, 65, 0, 0));
		mntmHabitacionesL.setPreferredSize(new Dimension(269, 25));
		mntmHabitacionesL.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmHabitacionesL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnListados.add(mntmHabitacionesL);
		mntmHabitacionesL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaListadoHabitacion();
			}
		});

		mntmFuncionarioL = new JMenuItem("Funcionario");
		mntmFuncionarioL.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmFuncionarioL.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmFuncionarioL.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, InputEvent.CTRL_MASK));
		mntmFuncionarioL.setMargin(new Insets(0, 65, 0, 0));
		mntmFuncionarioL.setPreferredSize(new Dimension(269, 25));
		mntmFuncionarioL.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmFuncionarioL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnListados.add(mntmFuncionarioL);

		mntmFuncionarioL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaListadoFuncionario();
			}
		});

		mnInformes = new JMenu("Informes");
		mnInformes.setForeground(Color.BLACK);
		mnInformes.setIconTextGap(45);
		mnInformes.setMargin(new Insets(0, 80, 0, 0));
		mnInformes.setPreferredSize(new Dimension(272, 30));
		mnInformes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInformes.setHorizontalAlignment(SwingConstants.TRAILING);
		mnInformes.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/supermercadoSystem/img/informes.png")));
		mnInformes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnInformes);

		mntmReservas = new JMenuItem("Reservas");
		mntmReservas.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmReservas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmReservas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, InputEvent.CTRL_MASK));
		mntmReservas.setMargin(new Insets(0, 75, 0, 0));
		mntmReservas.setPreferredSize(new Dimension(269, 25));
		mntmReservas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmReservas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInformes.add(mntmReservas);
		mntmReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaInformeReserva();
			}
		});

		mntmPagos = new JMenuItem("Pagos");
		mntmPagos.setHorizontalTextPosition(SwingConstants.LEADING);
		mntmPagos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmPagos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.CTRL_MASK));
		mntmPagos.setMargin(new Insets(0, 75, 0, 0));
		mntmPagos.setPreferredSize(new Dimension(269, 25));
		mntmPagos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmPagos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInformes.add(mntmPagos);
		mntmPagos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaInformePago();
			}
		});

		mnUtilidades = new JMenu("Utilidades");
		mnUtilidades.setForeground(Color.BLACK);
		mnUtilidades.setIconTextGap(45);
		mnUtilidades.setMargin(new Insets(0, 80, 0, 0));
		mnUtilidades.setPreferredSize(new Dimension(272, 22));
		mnUtilidades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnUtilidades.setHorizontalAlignment(SwingConstants.TRAILING);
		mnUtilidades.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/supermercadoSystem/img/utilidades.png")));
		mnUtilidades.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnUtilidades);

		mntmInicializacionDeDatos = new JMenuItem("Inicializacion de datos");
		mntmInicializacionDeDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializarBaseDatos();
			}
		});
		mntmInicializacionDeDatos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmInicializacionDeDatos.setMargin(new Insets(0, 35, 0, 0));
		mntmInicializacionDeDatos
				.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmInicializacionDeDatos.setPreferredSize(new Dimension(269, 25));
		mntmInicializacionDeDatos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnUtilidades.add(mntmInicializacionDeDatos);

		mntmSoporteTecnico = new JMenuItem("Soporte Tecnico");
		mntmSoporteTecnico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSoporteTecnico.setPreferredSize(new Dimension(269, 25));
		mntmSoporteTecnico.setMargin(new Insets(0, 35, 0, 0));
		mntmSoporteTecnico.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmSoporteTecnico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mnUtilidades.add(mntmSoporteTecnico);

		contentPane = new jPanelPantallaPrincipal();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BotonesPrincipales btnsprncplsPagos = new BotonesPrincipales();
		btnsprncplsPagos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsprncplsPagos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaPago();
			}
		});
		btnsprncplsPagos.setBounds(20, 287, 120, 93);
		btnsprncplsPagos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnsprncplsPagos.setText("Pagos");
		contentPane.add(btnsprncplsPagos);

		JButton btnsprncplsSalir = new JButton();
		btnsprncplsSalir
				.setIcon(new ImageIcon(PantallaPrincipal.class.getClass().getResource("/supermercadoSystem/img/cerrar.png")));
		btnsprncplsSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsprncplsSalir.setSize(130, 70);
		btnsprncplsSalir.setForeground(Color.BLACK);
		btnsprncplsSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnsprncplsSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnsprncplsSalir.setBorderPainted(false);
		btnsprncplsSalir.setBackground(new Color(240, 240, 240));
		btnsprncplsSalir.setOpaque(false);
		btnsprncplsSalir.setFocusable(false);
		btnsprncplsSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnsprncplsSalir.setBounds(1223, 548, 120, 93);
		btnsprncplsSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnsprncplsSalir.setText("Salir ");
		contentPane.add(btnsprncplsSalir);

		BotonesPrincipales btnsprncplsReservas = new BotonesPrincipales();
		btnsprncplsReservas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsprncplsReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaReserva();
			}
		});
		btnsprncplsReservas.setBounds(20, 432, 120, 93);
		btnsprncplsReservas.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnsprncplsReservas.setText("Reservas");
		contentPane.add(btnsprncplsReservas);

		BotonesPrincipales btnsprncplsClientes = new BotonesPrincipales();
		btnsprncplsClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsprncplsClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaCliente();
			}
		});
		btnsprncplsClientes.setBounds(20, 139, 120, 93);
		btnsprncplsClientes.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnsprncplsClientes.setText("Clientes");
		contentPane.add(btnsprncplsClientes);
	}

	private void abrirVentanaCliente() {
		ClienteVentana ventana = new ClienteVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirVentanaFuncionario() {
		FuncionarioVentana ventana = new FuncionarioVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirVentanaHabitacion() {
		ProductoVentana ventana = new ProductoVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirVentanaReserva() {
		VentaVentana ventana = new VentaVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirVentanaPago() {
		PagoVentana ventana = new PagoVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirVentanaListadoCliente() {
		ListadoClienteVentana ventana = new ListadoClienteVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirVentanaListadoFuncionario() {
		ListadoFuncionarioVentana ventana = new ListadoFuncionarioVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirVentanaListadoHabitacion() {
		ListadoProductoVentana ventana = new ListadoProductoVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirVentanaInformeReserva() {
		InformeVentaVentana ventana = new InformeVentaVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirVentanaInformePago() {
		InformePagoVentana ventana = new InformePagoVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void inicializarBaseDatos() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Deseas inicializar la Base de Datos?");
		if (respuesta == JOptionPane.YES_OPTION) {

			ClienteDao clienteDao = new ClienteDao();
			clienteDao.inicializarTabla("tb_cliente");
			clienteDao.commit();

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarioDao.inicializarTabla("tb_funcionario");
			funcionarioDao.commit();

			HabitacionDao habitacionDao = new HabitacionDao();
			habitacionDao.inicializarTabla("tb_habitacion");
			habitacionDao.commit();

			PagoDao pagoDao = new PagoDao();
			pagoDao.inicializarTabla("tb_pago");
			pagoDao.commit();

			Reserva_detalleDao reserva_detalleDao = new Reserva_detalleDao();
			reserva_detalleDao.inicializarTabla("tb_reserva_detalles");
			reserva_detalleDao.commit();

			ReservaDao reservaDao = new ReservaDao();
			reservaDao.inicializarTabla("tb_reserva");
			reservaDao.commit();
		}
	}

}
