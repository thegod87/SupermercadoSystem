package supermercadoSystem.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JtextFielPersonalizado extends JTextField {

	public JtextFielPersonalizado() {
		setFont(new Font("Tahoma", Font.BOLD, 11));
		setDisabledTextColor(Color.GRAY);
	}
	
	public void soloNumerosEnteros() {
		addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			public void KeyReleased(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar()))e.consume();
			}
		});
	}
	

}
