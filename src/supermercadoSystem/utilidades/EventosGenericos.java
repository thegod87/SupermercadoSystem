package supermercadoSystem.utilidades;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;
import supermercadoSystem.componentes.JtextFielPersonalizado;

public class EventosGenericos {

	public static void estadosJtexField(Component component, boolean estado) {
		if (component instanceof JtextFielPersonalizado) {
			JtextFielPersonalizado jtextFieldPersonalizado = ((JtextFielPersonalizado) component);
			jtextFieldPersonalizado.setEnabled(estado);
		} else {
			if (component instanceof Container) {
				for (Component c : ((Container) component).getComponents()) {
					estadosJtexField(c, estado);
				}
			}
		}
	}

	public static void limpiarJtexField(Component component) {
		if (component instanceof JtextFielPersonalizado) {
			JtextFielPersonalizado jtextFieldPersonalizado = ((JtextFielPersonalizado) component);
			jtextFieldPersonalizado.setText("");
			;
		} else {
			if (component instanceof Container) {
				for (Component c : ((Container) component).getComponents()) {
					limpiarJtexField(c);
				}
			}
		}
	}

	public static void moverConEnter(KeyEvent e, Component component) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER)
			component.requestFocus();
	}

}
