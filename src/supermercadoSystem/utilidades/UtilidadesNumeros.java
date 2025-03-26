package supermercadoSystem.utilidades;

import java.text.NumberFormat;
import java.util.Locale;

public class UtilidadesNumeros {

	private static NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);

	public static String integerAString(Integer i) {
		return nf.format(i);
	}

	public static Integer stringAInteger(String string) {
		try {
			return nf.parse(string).intValue();
		} catch (Exception e) {
			return null;
		}
	}

	public static String doubleAString(Double d) {
		return nf.format(d);
	}

	public static Double stringADouble(String double1) {
		try {
			return nf.parse(double1).doubleValue();
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer doubleAInteger(double d) {
		return (int) d;
	}

}
