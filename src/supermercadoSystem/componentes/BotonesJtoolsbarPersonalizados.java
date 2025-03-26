package supermercadoSystem.componentes;

import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.lowagie.text.Font;

@SuppressWarnings("serial")
public class BotonesJtoolsbarPersonalizados extends JButton {

	public BotonesJtoolsbarPersonalizados() {
		setSize(07, 70);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setFocusPainted(false);
		setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
		setMargin(new Insets(5, 22, 5, 22));
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
