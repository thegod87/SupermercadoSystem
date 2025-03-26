package supermercadoSystem.utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.MaskFormatter;

public class UtilidadesFecha {
	private static MaskFormatter formatter;
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	public static MaskFormatter getFormato() {
		try {
			formatter = new MaskFormatter("##/##/####");
			formatter.setPlaceholderCharacter('_');
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatter;
	}

	public static Date stringAFecha(String b) {
		DATE_FORMAT.setLenient(false);
		try {
			return DATE_FORMAT.parse(b);
		} catch (Exception e) {
			return null;
		}
	}

	public static String fechaAString(Date date) {
		return DATE_FORMAT.format(date);
	}

}
