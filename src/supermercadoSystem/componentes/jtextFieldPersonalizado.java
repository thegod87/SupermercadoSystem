package supermercadoSystem.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class jtextFieldPersonalizado extends JTextField {

	public jtextFieldPersonalizado() {
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
