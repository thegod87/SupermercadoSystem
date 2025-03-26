package supermercadoSystem.componentes;

import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BotonesPrincipales extends JButton {

	public BotonesPrincipales() {
		setSize(130, 70);
		setForeground(Color.BLACK);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setBorderPainted(false);
		setBackground(new Color(240, 240, 240));
		setOpaque(false);
		setFocusable(false);
	}

	public void setText(String string) {
		setIcon(string);
		super.setText(string);
	}

	public void setIcon(String nombreIcono) {
		try {
			URL url = BotonesPrincipales.class.getResource("/power/tech/img/" + nombreIcono.toLowerCase() + ".png");
			setIcon(new ImageIcon(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
