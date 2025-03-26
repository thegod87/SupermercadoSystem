package supermercadoSystem.componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class jPanelSoporteTecnico extends JPanel {
	public jPanelSoporteTecnico() {
	}

//	URL url = getClass().getResource("/power/tech/img/powertech.png/");
	
	URL url = jPanelSoporteTecnico.class.getClass().getResource("/power/tech/img/soportetecnico.png");
	Image image = new ImageIcon(url).getImage();

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paintComponent(g);
	}

}
